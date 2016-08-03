package com.lib.web.user.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonFactory;
import com.lib.dto.FileInfoVO;
import com.lib.dto.FileNew;
import com.lib.dto.JsonResult;
import com.lib.entity.FileInfo;
import com.lib.entity.RelationInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.utils.HtmlToWord;
import com.lib.utils.StringValueUtil;
import com.sun.tools.extcheck.Main;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * 新建文件的Controller
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class FileNewController {
	@Autowired
	private FileInfoService fileInfoService;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/edit/{uuid}", method = RequestMethod.GET)
	public String editUI(Model model, @PathVariable("uuid") String uuid, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		FileInfoVO fileInfo = fileInfoService.getFileInfoByUuid(uuid);
		model.addAttribute("fileInfo", fileInfo);
		return "file/edit";
	}

	@RequestMapping(value = "/newfile/complete", method = RequestMethod.POST)
	public @ResponseBody JsonResult newFileComplete(String fileName, String content, HttpSession session) {
		FileNew fn = (FileNew) session.getAttribute(Const.SESSION_NEW_FILE);
		JsonResult jr = new JsonResult(true, "未知");
		if (fn == null) {
			jr = new JsonResult(false, "请先编辑保存");
			return jr;
		}
		String uuid = StringValueUtil.getUUID();
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		String path = Const.ROOT_PATH + "users/" + user.getUserId() + "/files/" + uuid + ".pdf";
		try {
			jr = new JsonResult(true, uuid);

			HtmlToWord.HtmlToPdf(fn.getContent(), path);

			File file = new File(path);
			FileInfo fi = new FileInfo();
			fileName = fn.getName();
			fi.setFileName(fileName);
			fi.setFileSize(file.length());
			fi.setFileExt("pdf");
			fi.setFileBrief("无");
			fi.setFileUserId(user.getUserId());
			fi.setFileUuid(uuid);
			fi.setFilePath("users/" + user.getUserId() + "/files/" + uuid);
			fi.setFileState(2);
			fi.setFileClassId(1l);
			fileInfoService.insertFile(fi);
			session.removeAttribute(Const.SESSION_NEW_FILE);

			// 处理文件
			new Thread() {
				public void run() {
					fileInfoService.translateFile(uuid);
				};
			}.start();

		} catch (Exception e) {
			jr = new JsonResult(false, "转化失败");
		}
		return jr;
	}

	/**
	 * 新建文档保存
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newfile/save", method = RequestMethod.POST)
	public @ResponseBody JsonResult newFileSave(String fileName, String content, HttpSession session) {
		JsonResult jr = new JsonResult(true, "暂存成功");
		if (null == fileName || fileName.equals("")) {
			fileName = "未知名" + new Date();
		}

		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		FileNew fn = (FileNew) session.getAttribute(Const.SESSION_NEW_FILE);
		if (null == fn) {
			fn = new FileNew();
		}
		fn.setName(fileName);
		fn.setContent(content);

		session.setAttribute(Const.SESSION_NEW_FILE, fn);
		return jr;
	}

	@RequestMapping(value = "/file-edit-submit", method = RequestMethod.POST)
	public @ResponseBody JsonResult fileEditSave(FileInfo fileInfo, HttpSession session, Model model) {
		JsonResult jr = null;
		int res = fileInfoService.saveBaseFileInfoByUuid(fileInfo);
		if (res == 0) {
			jr = new JsonResult(false, "修改失败");
			return jr;
		}
		jr = new JsonResult(false, "修改成功");
		return jr;
	}

	@RequestMapping(value = "/file-search", method = RequestMethod.POST)
	public @ResponseBody JsonResult<List<FileInfo>> searchByNameOrId(String searchInfo, HttpSession session,
			Integer pageNo) {
		if (pageNo == null) {
			pageNo = 1;
		}
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult<List<FileInfo>> jr = null;
		List<FileInfo> list = fileInfoService.searchFileInfoByNameOrId(searchInfo, user.getUserId(), pageNo);
		jr = new JsonResult<List<FileInfo>>(true, list);
		return jr;
	}

	@RequestMapping(value = "/add-relations", method = RequestMethod.POST)
	public @ResponseBody JsonResult<Integer> addRelations(@RequestBody JSONObject json,
			HttpSession session) {
		// System.out.println(obj);
		// JSONObject json = JSONObject.fromObject(obj);
		Long mainFileId = json.getLong("mainFileId");
		List<String> listStr = (List<String>) json.get("list");
		List<Long> list = new ArrayList<Long>();

		for (String l : listStr) {
			list.add(Long.valueOf(l));
		}

		int res = fileInfoService.addRelations(mainFileId, list);
		JsonResult<Integer> jr = null;
		jr = new JsonResult<Integer>(true, res);
		return jr;
	}
	
	@RequestMapping(value = "/get-relations/{mainFileId}", method = RequestMethod.POST)
	public @ResponseBody JsonResult<List<RelationInfo>> getRelations(@PathVariable("mainFileId")Long mainFileId) {

		List<RelationInfo> res = fileInfoService.getRelations(mainFileId);
		JsonResult<List<RelationInfo>> jr = null;
		jr = new JsonResult<List<RelationInfo>>(true, res);
		return jr;
	}

	

}
