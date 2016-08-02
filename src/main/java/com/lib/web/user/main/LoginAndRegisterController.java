package com.lib.web.user.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.JsonResult;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.exception.user.UserNullAccountException;
import com.lib.exception.user.UserPasswordWrongException;
import com.lib.service.user.UserRegisterService;
import com.lib.service.user.UserService;

/**
 * 前台登录
 * 
 * @author Yu Yufeng
 *
 */
@Controller
public class LoginAndRegisterController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRegisterService urService;

	// @Autowired
	//

	/**
	 * 跳转到登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("date", new Date());
		return "login";
	}

	/**
	 * 跳转到注册页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registerUI", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("date", new Date());
		return "register/register";
	}

	@RequestMapping(value = "/login-submit", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public @ResponseBody JsonResult loginSub(UserInfo user, HttpSession session) {
		JsonResult<String> result = null;
		try {
			userService.checkUserByEmail(user);
			result = new JsonResult(true, null);
			// 在session中保存用户基本信息
			UserInfo userBasicInfo = userService.getBasicUserInfoByEmail(user.getUserEmail());
			session.setAttribute(Const.SESSION_USER, userBasicInfo);

		} catch (UserNullAccountException e) {
			result = new JsonResult(false, "用户不存在");
		} catch (UserPasswordWrongException e2) {
			result = new JsonResult(false, "密码错误");
		}
		return result;
	}

	/**
	 * 退出
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		model.addAttribute("date", new Date());
		return "login";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody UserInfo test() {
		UserInfo user = userService.getUserById(2016001l);
		// JsonResult<UserInfo> result = new JsonResult<UserInfo>(true, user);
		return user;
	}

	@RequestMapping(value = "/illegal-view", method = RequestMethod.GET)
	public String illegalView(Model model, HttpServletRequest request) {
		model.addAttribute("error", "无权访问，请先登录！");
		return "error";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public String load(String userName, String userPassword, HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {
		String action = request.getParameter("action");
		if ("register".equals(action)) {
			// 注册
			String email = request.getParameter("email");
			urService.processregister(userName, userPassword, email);// 发邮箱激活
			model.addAttribute("text", "注册成功");
			return "register/register-success";
		} else if ("activate".equals(action)) {
			System.out.println(request.getLocalAddr());
			// 激活
			String email = request.getParameter("email");// 获取email
			String validateCode = request.getParameter("validateCode");// 激活码
			try {
				urService.processActivate(email, validateCode);// 调用激活方法
				return "register/activate-success";
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				return "error";
			}

		}
		return "register-success";
	}
}
