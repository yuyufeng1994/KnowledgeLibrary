package com.lib.web.user.main;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lib.dto.FileInfoVO;
import com.lib.dto.JsonResult;
import com.lib.entity.Classification;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.CountService;
import com.lib.service.user.FileManageService;
import com.lib.service.user.UserService;

/**
 * 主要页面跳转
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class MainController {
	@Autowired
	private FileManageService fileManageService;
	@Autowired
	private UserService userService;
	@Autowired
	private CountService countService;

	/**
	 * 获取今日的录入文件数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "/count-today", method = RequestMethod.GET)
	public @ResponseBody JsonResult<Long> getTodaysUpload() {
		Long count = countService.getTodaysUpload();
		JsonResult<Long> jr = new JsonResult<Long>(true, count);
		return jr;
	}

	/**
	 * 获取用户上传的资源数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "/count-userfiles", method = RequestMethod.GET)
	public @ResponseBody JsonResult<Long> getCountUserFiles(HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		Long count = countService.getCountUserFiles(user.getUserId());
		JsonResult<Long> jr = new JsonResult<Long>(true, count);
		return jr;
	}

	/**
	 * 获取用户可用资源数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "/count-publicfiles", method = RequestMethod.GET)
	public @ResponseBody JsonResult<Long> getCountPublicFiles(HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		Long count = countService.getCountPublicFiles(user.getUserId());
		JsonResult<Long> jr = new JsonResult<Long>(true, count);
		return jr;
	}

	/**
	 * 获取用户的收藏数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "/count-forkfiles", method = RequestMethod.GET)
	public @ResponseBody JsonResult<Long> getCountForkFiles(HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		Long count = countService.getCountForkFiles(user.getUserId());
		JsonResult<Long> jr = new JsonResult<Long>(true, count);
		return jr;
	}

	/**
	 * 跳转到主页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("date", new Date());
		return "main/index";
	}

	@RequestMapping(value = "/public/{fileClassId}/{pageNo}", method = RequestMethod.GET)
	public String publicResource(Model model, @PathVariable("fileClassId") Long fileClassId,
			@PathVariable("pageNo") Integer pageNo) {
		List<Classification> list = fileManageService.getClassificationByParentId(fileClassId);
		// 得到所有父节点链表
		List<Classification> plist = fileManageService.getFatherClassesById(fileClassId);
		Classification c = fileManageService.getClassificationById(fileClassId);
		// list.add(0, c);
		PageInfo<FileInfoVO> page = fileManageService.getAllChildFiles(pageNo, fileClassId);
		model.addAttribute("classi", c);
		model.addAttribute("list", list);
		model.addAttribute("plist", plist);

		model.addAttribute("page", page);

		return "main/public";
	}

}
