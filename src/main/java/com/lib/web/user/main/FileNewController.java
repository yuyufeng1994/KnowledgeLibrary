package com.lib.web.user.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import com.lib.dto.FileNew;
import com.lib.dto.JsonResult;
import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.utils.HtmlToWord;
import com.lib.utils.JudgeUtils;
import com.lib.utils.StringValueUtil;

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
		String path = Const.ROOT_PATH + user.getUserId() + "/files/" + uuid + ".pdf";
		try {
			jr = new JsonResult(true, uuid);

			HtmlToWord.HtmlToPdf(fn.getContent(), path);

			File file = new File(path);
			FileInfo fi = new FileInfo();
			fileName = fn.getName();
			fi.setFileName(fileName);
			fi.setFileSize(file.length());
			fi.setFileExt("doc");
			fi.setFileBrief("无");
			fi.setFileUserId(user.getUserId());
			fi.setFileUuid(uuid);
			fi.setFilePath(user.getUserId() + "/files/" + uuid);
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
			fileName = "新建文档";
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

}
