package com.lib.web.user.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.druid.support.logging.Log;
import com.lib.dto.FileInfoVO;
import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.utils.JudgeUtils;
import com.lib.utils.StringValueUtil;

/**
 * 专门处理文件的Controller
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class FileManagerController {
	@Autowired
	private FileInfoService fileInfoService;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 跳转到上传页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Model model, HttpSession session) {
		session.removeAttribute(Const.SESSION_UPLOADS);
		List<String> list = new ArrayList<>();
		session.setAttribute(Const.SESSION_UPLOADS, list);
		return "file/upload";
	}

	/**
	 * 跳转到新建页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newfile", method = RequestMethod.GET)
	public String newfile(Model model) {

		return "file/newfile";
	}

	/**
	 * 跳转上传完成页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload-complete", method = RequestMethod.GET)
	public String uploadFinish(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")

		List<String> uploadfiles = (List<String>) session.getAttribute(Const.SESSION_UPLOADS);
		if (uploadfiles != null) {
			List<FileInfoVO> list = new ArrayList<>();
			for (String u : uploadfiles) {
				FileInfoVO e = fileInfoService.getFileInfoByUuid(u);
				list.add(e);
			}

			model.addAttribute("files", list);
		}

		return "file/uploadfiles";
	}

	/**
	 * 初始化解压状态
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/re-compress", method = RequestMethod.POST)
	public @ResponseBody String ReCompressState(HttpSession session) {
		session.setAttribute(Const.SESSION_IS_COMPRESSING, false);
		return "success";
	}

	/**
	 * 是否解压文件
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tog-compress", method = RequestMethod.POST)
	public @ResponseBody String ifCompress(HttpSession session) {
		Boolean state = (Boolean) session.getAttribute(Const.SESSION_IS_COMPRESSING);
		if (state == null) {
			session.setAttribute(Const.SESSION_IS_COMPRESSING, true);
		} else {
			if (state == true) {
				session.setAttribute(Const.SESSION_IS_COMPRESSING, false);
			} else {
				session.setAttribute(Const.SESSION_IS_COMPRESSING, true);
			}

		}
		return "success";
	}

	/**
	 * 上传文件（可上传多个，这里只上传一个，所以取数组第一个）
	 * 
	 * @param files
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload-file", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(@RequestParam("file") CommonsMultipartFile[] files, HttpSession session) throws Exception {
		@SuppressWarnings("unchecked")
		List<String> uploadfiles = (List<String>) session.getAttribute(Const.SESSION_UPLOADS);
		if (uploadfiles == null) {
			uploadfiles = new ArrayList<>();
			session.setAttribute(Const.SESSION_UPLOADS, uploadfiles);
		}

		Boolean compressState = (Boolean) session.getAttribute(Const.SESSION_IS_COMPRESSING);
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		String uuid = StringValueUtil.getUUID();
		String fileName = files[0].getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

		String userFilePath = "users/" + user.getUserId() + "/files/";
		String filePath = Const.ROOT_PATH + userFilePath + uuid + "." + ext;

		// 解压文件
		if (JudgeUtils.isCompressFile(ext)) {
			if (compressState != null && compressState == true) {
				String tempPath = Const.ROOT_PATH + "temp/";
				File dir = new File(tempPath);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				FileUtils.writeByteArrayToFile(new File(tempPath + uuid + "." + ext), files[0].getBytes());
				List<String> filesUuid = fileInfoService.compressFile(tempPath + uuid + "." + ext, user);
				for (String fuuid : filesUuid) {
					uploadfiles.add(fuuid);
					// 处理文件
					new Thread() {
						public void run() {
							try {
								fileInfoService.translateFile(fuuid);
							} catch (IOException e) {
								LOG.error(fuuid + "文件处理失败");
							}
						};
					}.start();
				}

				return "success";
			}
		}

		// 保存刚刚上传的文件

		uploadfiles.add(uuid);

		try {
			FileUtils.writeByteArrayToFile(new File(filePath), files[0].getBytes());

			FileInfo fi = new FileInfo();
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			fi.setFileName(fileName);
			fi.setFileSize(files[0].getSize());
			fi.setFileExt(ext);
			fi.setFileBrief("无");
			fi.setFileUserId(user.getUserId());
			fi.setFileUuid(uuid);
			fi.setFilePath(userFilePath + uuid);
			fi.setFileState(2);
			fi.setFileClassId(1l);

			fileInfoService.insertFile(fi);

			// 处理文件
			new Thread() {
				public void run() {
					try {
						fileInfoService.translateFile(uuid);
					} catch (IOException e) {
						LOG.error(uuid + "文件处理失败");
					}
				};
			}.start();
		} catch (Exception e) {

		} finally {

		}
		return "success";
	}

	/**
	 * 文件下载
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/download/{uuid}/{ext}", method = RequestMethod.GET)
	public String download(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@PathVariable("uuid") String uuid, @PathVariable("ext") String ext) {
		FileInfo fileInfo = fileInfoService.getFileInfoByUuid(uuid);
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		String path = Const.ROOT_PATH + fileInfo.getFilePath() + "." + ext;

		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		String fileAllName = fileInfo.getFileName() + "." + fileInfo.getFileExt();
		try {
			fileAllName = new String(fileAllName.getBytes("UTF-8"), "iso-8859-1");
		} catch (UnsupportedEncodingException e1) {
		}
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileAllName);
		try {
			InputStream inputStream = new FileInputStream(path);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			LOG.error("文件没有找到" + path);
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 文件流
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/thumbnail/{uuid}/{ext}", method = RequestMethod.GET)
	public String thumbnail(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@PathVariable("uuid") String uuid, @PathVariable("ext") String ext) {
		FileInfoVO fileInfo = fileInfoService.getFileInfoByUuid(uuid);
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		
		String path = Const.ROOT_PATH + fileInfo.getFilePath() + "." + ext;

		try {
			InputStream inputStream = new FileInputStream(path);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			LOG.error("文件没有找到" + path);
		} catch (IOException e) {
		}
		return null;
	}

}
