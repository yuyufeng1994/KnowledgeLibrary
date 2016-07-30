package com.lib.web.user.main;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
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

	/**
	 * 跳转到上传页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Model model) {

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
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		String uuid = StringValueUtil.getUUID();
		String fileName = files[0].getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		String userFilePath = user.getUserId() + "/files/";
		String filePath = Const.ROOT_PATH + userFilePath + uuid + "." + ext;

		try {
			FileUtils.writeByteArrayToFile(new File(filePath), files[0].getBytes());
			FileInfo fi = new FileInfo();
			fi.setFileName(fileName);
			fi.setFileSize(files[0].getSize());

			fi.setFileExt(ext);
			fi.setFileBrief("这是简介");
			fi.setFileUserId(2016001l);
			fi.setFileUuid(uuid);
			fi.setFilePath(filePath);
			fi.setFileState(2);
			fi.setFileClassId(1l);
			
			fileInfoService.insertFile(fi);
		} catch (Exception e) {

		}
		return "success";
	}
	/**
	 * 下载文件
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/download-file/{id}/{ext}", method =
	 * RequestMethod.GET) public String download(@PathVariable("id") Long
	 * id, @PathVariable("ext") String ext, HttpServletRequest request,
	 * HttpServletResponse response) { Doc doc = docService.getDoc(id); if (doc
	 * == null) { return null; } // System.out.println(doc); if
	 * (ext.equals("pdf")) { response.setContentType("application/pdf");
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=WebReport.pdf"); } else {
	 * response.setContentType("multipart/form-data");
	 * response.setHeader("Content-Disposition", "attachment;fileName=" +
	 * doc.getDocName() + "." + ext); } try { InputStream inputStream = new
	 * FileInputStream(Const.IO_DOC_PATH + doc.getDocPath() + "." + ext);
	 * OutputStream os = response.getOutputStream(); byte[] b = new byte[2048];
	 * int length; while ((length = inputStream.read(b)) > 0) { os.write(b, 0,
	 * length); } // 这里主要关闭。 os.close(); inputStream.close(); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e)
	 * { e.printStackTrace(); } return null; }
	 */
}
