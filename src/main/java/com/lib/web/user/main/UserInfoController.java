package com.lib.web.user.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.JsonResult;
import com.lib.entity.UserInfo;
import com.lib.entity.UserRegister;
import com.lib.enums.Const;
import com.lib.service.user.UserRegisterService;
import com.lib.service.user.UserService;

/**
 * 主要页面跳转
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRegisterService urService;
	/**
	 * 跳转到个人中心
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	public String login(Model model,HttpSession session) {
		UserInfo user = (UserInfo)session.getAttribute(Const.SESSION_USER);
		model.addAttribute("user",user);
		model.addAttribute("date", new Date());
		return "user/userinfo";
	}
	/**
	 * 跳转到账户设置
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user-account", method = RequestMethod.GET)
	public String account(Model model,HttpSession session) {
		UserInfo user = (UserInfo)session.getAttribute(Const.SESSION_USER);
		model.addAttribute("user",user);
		model.addAttribute("date", new Date());
		return "user/account";
	}
	/**
	 * 修改个人信息
	 * @return 
	 * @return
	 */
	@RequestMapping(value="/update-user", method = RequestMethod.POST)
	public @ResponseBody JsonResult<UserInfo> updateUserInfo(UserInfo user,HttpSession session){
		userService.updateUser(user);
		UserInfo updateUser = userService.getUserById(user.getUserId());
		session.setAttribute(Const.SESSION_USER, updateUser);
		JsonResult<UserInfo> jr = null;
		jr = new JsonResult<UserInfo>(true, updateUser);
		System.out.println(updateUser);
		return jr;
	}
	@RequestMapping(value="/update-userEmail", method = { RequestMethod.GET, RequestMethod.POST })
	public  String updateUserEmail(String action,HttpServletRequest request,Long userId,String userEmail,HttpSession session) throws Exception{
		if ("register".equals(action)) {
			UserInfo user =  userService.getUserById(userId);
			user.setUserEmail(userEmail);
			user.setUserType(2);
			// 更新邮箱
			urService.updateEmail(user);
			return "register/register-success";
		} else if ("activate".equals(action)) {
			System.out.println(request.getLocalAddr());
			// 激活
			String validateCode = request.getParameter("validateCode");// 激活码
			try {
				urService.processActivate(userEmail, validateCode);// 调用激活方法
				return "register/activate-success";
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				return "message";
			}
			
		}
		UserInfo updateUser = userService.getBasicUserInfoByEmail(userEmail);
		session.setAttribute(Const.SESSION_USER, updateUser);
		return "redirect:login";
	}
	
	
}
