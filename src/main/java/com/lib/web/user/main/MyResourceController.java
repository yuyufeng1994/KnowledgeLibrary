package com.lib.web.user.main;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	/**
	 * 跳转到我的资源
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/myfiles", method = RequestMethod.GET)
	public String myFiles(Model model) {
		model.addAttribute("date", new Date());
		return "file/myfiles";
	}
	
	/**
	 * 跳转到我的资源
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/myforks", method = RequestMethod.GET)
	public String myForks(Model model) {
		model.addAttribute("date", new Date());
		return "file/myforks";
	}

}
