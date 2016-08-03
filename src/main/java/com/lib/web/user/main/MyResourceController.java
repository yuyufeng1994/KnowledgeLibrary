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

import com.github.pagehelper.PageInfo;
import com.lib.dto.FileInfoVO;
import com.lib.dto.ForkFileInfoVo;
import com.lib.entity.FileInfo;
import com.lib.entity.ForkInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.FileManageService;
import com.lib.service.user.ForkInfoService;
import com.lib.service.user.UserService;

/**
 * 主要页面跳转
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class MyResourceController {
	@Autowired
	private UserService userService;

	@Autowired
	private FileManageService fileManageService;
	
	@Autowired
	private FileInfoService fileInfoService;
	//收藏操作service
	@Autowired
	private ForkInfoService forkInfoService;

	/**
	 * 跳转到我的资源
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/myfiles/{pageNo}", method = RequestMethod.GET)
	public String myFiles(Model model, @PathVariable("pageNo") Integer pageNo, HttpSession session) {
		if (pageNo == null) {
			pageNo = 1;
		}
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		PageInfo<FileInfoVO> page = fileManageService.getFileInfoPageByUserId(pageNo, user.getUserId(), "file_id desc");
		model.addAttribute("page", page);
		return "file/myfiles";
	}

	/**
	 * 跳转到我的收藏
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/myforks/{docId}/{pageNo}", method = RequestMethod.GET)
	public String myForks(Model model, @PathVariable("pageNo") Integer pageNo,@PathVariable("docId") Long docId,HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		PageInfo<ForkFileInfoVo> page = forkInfoService.getFileForkInfoPageByUserId(pageNo, user.getUserId(),docId,user.getUserName());
		model.addAttribute("page", page);
		return "file/myforks";
	}

}
