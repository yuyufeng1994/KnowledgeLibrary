package com.lib.web.user.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.FileInfoVO;
import com.lib.dto.JsonResult;
import com.lib.entity.Classification;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileManageService;

/**
 * 无需登录的Controller
 * 
 * @author Yu Yufeng
 *
 */
@Controller
public class CommonController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileManageService fileManageService;

	/**
	 * 得到常用文件流
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/thumbnail/{uuid}", method = RequestMethod.GET)
	public String thumbnail(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@PathVariable("uuid") String uuid) {
		String path = Const.ROOT_PATH + "thumbnail/" + uuid + ".png";
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
	 * 根据文件地址下载文件主要用于Uedior
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	// ueditor/jsp/upload/image/20160731/1469934781425082135.jpg
	@RequestMapping(value = "/ueditor/jsp/upload/{type}/{day}/{name}", method = RequestMethod.GET)
	public String download(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRequestURI();
		path = path.substring(5);
		path = Const.ROOT_PATH + path;
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

	/*
	 * 得到一层子分类
	 * 
	 * @param model
	 * 
	 * @return
	 */
	@RequestMapping(value = "/child-file-class/{fileClassId}", method = RequestMethod.GET)
	public @ResponseBody JsonResult<List<Classification>> getChildFileClass(
			@PathVariable("fileClassId") Long fileClassId) {
		JsonResult<List<Classification>> jr = null;
		List<Classification> list = fileManageService.getClassificationByParentId(fileClassId);
		Classification c = fileManageService.getClassificationById(fileClassId);
		list.add(0, c);
		jr = new JsonResult<List<Classification>>(true, list);
		return jr;
	}

	/*
	 * 得到所有层子分类
	 * 
	 * @param model
	 * 
	 * @return
	 */
	@RequestMapping(value = "/child-file-class-all/{fileClassId}", method = RequestMethod.GET)
	public @ResponseBody JsonResult<List<Classification>> getAllChildFileClass(
			@PathVariable("fileClassId") Long fileClassId) {
		JsonResult<List<Classification>> jr = null;
		List<Classification> list = fileManageService.getAllChildClassesById(fileClassId);
		jr = new JsonResult<List<Classification>>(true, list);
		return jr;
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String noPage(Model model, HttpServletRequest request) {
		model.addAttribute("message", "页面不存在！");
		return "message";
	}
	
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public String error(Model model, HttpServletRequest request) {
		model.addAttribute("message", "发生错误！");
		return "message";
	}

}
