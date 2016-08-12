package com.lib.web.user.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

import com.github.pagehelper.PageInfo;
import com.lib.dto.FileInfoVO;
import com.lib.dto.ForkFileInfoVo;
import com.lib.dto.JsonResult;
import com.lib.entity.Classification;
import com.lib.entity.DocInfo;
import com.lib.entity.FileInfo;
import com.lib.entity.ForkInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.admin.ClassificationService;
import com.lib.service.user.DocInfoService;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.ForkInfoService;
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
public class FileContentController {
	@Autowired
	private FileInfoService fileInfoService;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 文件夹操作service
	@Autowired
	private DocInfoService docInfoService;

	// 收藏操作service
	@Autowired
	private ForkInfoService forkInfoService;
	// 收藏操作service
	@Autowired
	private ClassificationService classificationService ;

	/**
	 * 更新缩略图
	 * 
	 * @param files
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update-thumb/{uuid}", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(@RequestParam("file") CommonsMultipartFile[] files, HttpSession session,
			@PathVariable("uuid") String uuid) throws Exception {
		Boolean compressState = (Boolean) session.getAttribute(Const.SESSION_IS_COMPRESSING);
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		FileInfoVO file = fileInfoService.getFileInfoByUuid(uuid);
		FileUtils.writeByteArrayToFile(new File(Const.ROOT_PATH + file.getFilePath()+".png"), files[0].getBytes());

		return "success";
	}

	/**
	 * 跳转到文件预览主体
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/file/{uuid}", method = RequestMethod.GET)
	public String upload(Model model, @PathVariable("uuid") String uuid, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		FileInfoVO fileInfo = fileInfoService.getFileInfoByUuid(uuid);
		model.addAttribute("fileInfo", fileInfo);
		fileInfoService.addClick(user.getUserId(),fileInfo.getFileId());
		return "file/content";
	}

	/**
	 * 查找用户全部收藏夹
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/findAllByUserId", method = RequestMethod.POST)
	public @ResponseBody JsonResult findAllByUserId(HttpSession session) {

		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult jr = new JsonResult(true, "暂存成功");
		List<DocInfo> docInfos = docInfoService.findAllByUserId(Long.valueOf(user.getUserId()));
		if (docInfos.size() == 0) {
			docInfoService.insert("常用收藏", user.getUserId());
			docInfos = docInfoService.findAllByUserId(Long.valueOf(user.getUserId()));
		}
		jr.setData(docInfos);
		return jr;
	}

	/**
	 * 添加收藏夹
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/insertDoc", method = RequestMethod.POST)
	public @ResponseBody JsonResult insertDoc(String docName, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult jr = new JsonResult(true, "添加成功");
		List<DocInfo> docInfos = docInfoService.findAllByUserId(user.getUserId());
		for (DocInfo d : docInfos) {
			if (d.getDocName().equals(docName)) {

				jr.setError("添加失败,收藏夹名重复");
				jr.setSuccess(false);
				return jr;
			}

		}
		docInfoService.insert(docName, user.getUserId());
		return jr;
	}

	/**
	 * 删除收藏夹
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteDoc", method = RequestMethod.POST)
	public @ResponseBody JsonResult deleteDoc(Long docId, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult jr = new JsonResult(true, "删除成功");
		docInfoService.delete(docId);
		return jr;
	}

	/**
	 * 添加一个收藏
	 * 
	 * @param forkInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/insertFork", method = RequestMethod.POST)
	public @ResponseBody JsonResult insertFork(ForkInfo forkInfo, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult jr = new JsonResult(true, "收藏成功");
		forkInfoService.insert(forkInfo);
		Long forkId = forkInfoService.findByFileId(forkInfo.getFileId(), user.getUserId()).getForkId();
		jr.setData(forkId);
		return jr;
	}

	/**
	 * 判断是否收藏过
	 * 
	 * @param forkInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/judgeFork", method = RequestMethod.POST)
	public @ResponseBody JsonResult judgeFork(ForkInfo forkInfo, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult jr = new JsonResult(true, "未收藏");
		List<ForkInfo> forkInfos = forkInfoService.findByDocId(user.getUserId());

		for (ForkInfo f : forkInfos) {

			if (f.getFileId().equals(forkInfo.getFileId())) {
				jr.setError("收藏失败,你已经收藏过该文件!");
				jr.setSuccess(false);
				jr.setData(f.getForkId());

			}

		}
		return jr;
	}

	/**
	 * 修改一个收藏
	 * 
	 * @param forkInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/modifyFork", method = RequestMethod.POST)
	public @ResponseBody JsonResult modifyFork(ForkInfo forkInfo, HttpSession session) {
		JsonResult jr = new JsonResult(true, "修改成功");
		forkInfoService.modify(forkInfo);
		return jr;
	}

	/**
	 * 删除一个收藏
	 * 
	 * @param forkInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteFork", method = RequestMethod.POST)
	public @ResponseBody JsonResult deleteFork(Long forkId, HttpSession session) {
		JsonResult jr = new JsonResult(true, "删除成功");
		forkInfoService.delete(forkId);
		return jr;
	}

	/**
	 * 获取一层子节点
	 * @param forkId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getClass", method = RequestMethod.POST)
	public @ResponseBody JsonResult getClass(Long classId) {
		JsonResult jr = new JsonResult(true, "获取成功");
		List<Classification> vo=classificationService.findOneChildById(classId);
		jr.setData(vo);
		return jr;
	}
}
