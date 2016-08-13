package com.lib.web.user.main;

import java.io.File;
import java.io.IOException;
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
import com.lib.dao.UserInfoDao;
import com.lib.dto.FileInfoVO;
import com.lib.dto.FileNew;
import com.lib.dto.JsonResult;
import com.lib.dto.SerResult;
import com.lib.entity.FileInfo;
import com.lib.entity.RelationInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.LuceneService;
import com.lib.utils.HtmlToWord;
import com.lib.utils.LuceneSearchUtil;
import com.lib.utils.StringValueUtil;

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

	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private LuceneService searchService;

	@Autowired
	private LuceneService lservice;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/file-content-search", method = RequestMethod.POST)
	public @ResponseBody JsonResult searchFileContent(String searchInfo) {
		JsonResult<List<SerResult>> jr = null;
		List<SerResult> list = lservice.getParagraph(searchInfo);
		if (list.size() == 0) {
			jr = new JsonResult<List<SerResult>>(false, "没有找到相关内容");
		} else {
			jr = new JsonResult<List<SerResult>>(true, list);
		}
		return jr;
	}

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
		// System.out.println(path);
		// 判断用户有没有建文件夹
		File dir = new File(Const.ROOT_PATH + "users/" + user.getUserId() + "/files/");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			jr = new JsonResult(true, uuid);

			HtmlToWord.HtmlToPdf(fn.getContent(), path);

			File file = new File(path);
			FileInfo fi = new FileInfo();
			fileName = fn.getName();
			fi.setFileName(fileName);
			fi.setFileSize(file.length());
			fi.setFileExt("pdf");
			fi.setFileBrief("");
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
					try {
						fileInfoService.translateFile(uuid);
					} catch (IOException e) {
						LOG.error(uuid + "文件处理失败");
					}
				};
			}.start();

		} catch (Exception e) {
			e.printStackTrace();
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
		FileInfo file = fileInfoService.getFileInfoByUuid(fileInfo.getFileUuid());
		if (res == 0) {
			jr = new JsonResult(false, "修改失败");
			return jr;
		} else if (res != 0 && fileInfo.getFileState() == null) {
			jr = new JsonResult(true, "修改成功");
			return jr;
		} else if (res != 0 && fileInfo.getFileState() == 5) {
			// 全文检索创立索引
			try {
				String fileText = LuceneSearchUtil.judge(file.getFileId());
				// System.out.println(fileText);
				searchService.deleteFileIndex(file);
				searchService.addFileIndex(file, userInfoDao.queryById(file.getFileUserId()).getUserName(), fileText);
			} catch (Exception e) {

			}
		} else {
			searchService.deleteFileIndex(file);
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
	public @ResponseBody JsonResult<Integer> addRelations(@RequestBody JSONObject json, HttpSession session) {
		// System.out.println(obj);
		// JSONObject json = JSONObject.fromObject(obj);
		Long mainFileId = json.getLong("mainFileId");
		List<String> listStr = (List<String>) json.get("list");
		List<Long> list = new ArrayList<Long>();

		for (String l : listStr) {
			if (mainFileId != Long.valueOf(l) && !mainFileId.equals(l)) {
				list.add(Long.valueOf(l));
			}
		}

		int res = fileInfoService.addRelations(mainFileId, list);
		JsonResult<Integer> jr = null;
		jr = new JsonResult<Integer>(true, res);
		return jr;
	}

	/**
	 * 自动关联
	 * 
	 * @param mainFileId
	 * @return
	 */
	@RequestMapping(value = "/auto-relation/{uuid}", method = RequestMethod.POST)
	public @ResponseBody JsonResult<Integer> autoRelations(@PathVariable("uuid") String uuid) {
		JsonResult<Integer> jr = null;
		if(uuid!=null){
			int res = fileInfoService.autoRelation(uuid);
			 jr = new JsonResult<Integer>(true, res);
		}else{
			 jr = new JsonResult<Integer>(false, 0);
		}
		
		
		return jr;
	}

	@RequestMapping(value = "/get-relations/{mainFileId}", method = RequestMethod.POST)
	public @ResponseBody JsonResult<List<RelationInfo>> getRelations(@PathVariable("mainFileId") Long mainFileId) {

		List<RelationInfo> res = fileInfoService.getRelations(mainFileId);
		JsonResult<List<RelationInfo>> jr = null;
		jr = new JsonResult<List<RelationInfo>>(true, res);
		return jr;
	}

	@RequestMapping(value = "/del-relations/{mainFileId}/{relationFileId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonResult<Integer> delRelations(@PathVariable("mainFileId") Long mainFileId,
			@PathVariable("relationFileId") Long relationFileId) {
		int res = fileInfoService.delRelations(mainFileId, relationFileId);
		JsonResult<Integer> jr = null;
		jr = new JsonResult<Integer>(true, res);
		return jr;
	}

}
