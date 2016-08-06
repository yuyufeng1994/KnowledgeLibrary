package com.lib.web.user.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

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

import com.lib.dto.FileInfoVO;
import com.lib.dto.JsonResult;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.UserRegisterService;
import com.lib.service.user.UserService;
import com.lib.utils.StringValueUtil;

/**
 * 主要页面跳转
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
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
	public String login(Model model, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		model.addAttribute("user", user);
		model.addAttribute("date", new Date());
		return "user/userinfo";
	}

	/**
	 * 跳转到账户设置
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user-account", method = RequestMethod.GET)
	public String account(Model model, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		model.addAttribute("user", user);
		model.addAttribute("date", new Date());
		return "user/account";
	}

	/**
	 * 修改个人信息
	 * 
	 * @return
	 * @return
	 */
	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	public @ResponseBody JsonResult<UserInfo> updateUserInfo(UserInfo user, HttpSession session) {
		userService.updateUser(user);
		UserInfo updateUser = userService.getUserById(user.getUserId());
		session.setAttribute(Const.SESSION_USER, updateUser);
		JsonResult<UserInfo> jr = null;
		jr = new JsonResult<UserInfo>(true, updateUser);
		System.out.println(updateUser);
		return jr;
	}

	/**
	 * 修改邮箱地址
	 * 
	 * @param action
	 * @param request
	 * @param userId
	 * @param userEmail
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/update-userEmail", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult<StringBuffer> updateUserEmail(String action, HttpServletRequest request, Long userId,
			String userEmail, HttpSession session) throws Exception {
		StringBuffer result = new StringBuffer();
		System.out.println(userEmail);
		if ("register".equals(action)) {
			UserInfo user = userService.getUserById(userId);
			UserInfo u = null;
			try{
			 u = userService.getBasicUserInfoByEmail(userEmail);
			}catch(Exception e){
				
			}
			if (u != null) {
				result.append("该邮箱地址已被注册");
			} else {
				if (user.getUserEmail().equals(userEmail)) {
					System.out.println("请修改邮箱地址再更换邮箱");
					result.append("请修改邮箱地址再更换邮箱");
				} else {
					user.setUserEmail(userEmail);
					user.setUserType(2);
					// 更新邮箱
					urService.updateEmail(user);
					result.append("发送邮箱成功");
					;
				}
			}
		} else if ("activate".equals(action)) {
			// 激活
			String validateCode = request.getParameter("validateCode");// 激活码
			try {
				urService.processActivate(userEmail, validateCode);// 调用激活方法
				// return "register/activate-success";
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
			}

		}
		JsonResult<StringBuffer> jr = null;
		jr = new JsonResult<StringBuffer>(true, result);
		UserInfo updateUser = userService.getBasicUserInfoByEmail(userEmail);
		session.setAttribute(Const.SESSION_USER, updateUser);
		return jr;
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @param confirmPassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update-password", method = { RequestMethod.POST, RequestMethod.GET })
	public JsonResult<StringBuffer> updateUserPwd(HttpSession session, Long userId, String oldPassword,
			String newPassword, String confirmPassword) {
		UserInfo user = userService.getUserAllInfo(userId);
		StringBuffer result = new StringBuffer();
		System.out.println(StringValueUtil.getMD5(oldPassword) + " " + user.getUserPassword());
		System.out.println(user);
		// 匹配标识符必须由字母、数字、下划线组成，且开头和结尾不能有下划线,且中间的字符至少1个不能超过5个
		String regex = "(^[a-z0-9A-Z])[a-z0-9A-Z_]{1,5}([a-z0-9-A-Z])";
		boolean flg = Pattern.matches(regex, newPassword);
		if (StringValueUtil.getMD5(oldPassword).equals(user.getUserPassword())) {
			if (flg) {
				if (newPassword.equals(confirmPassword)) {
					user.setUserPassword(StringValueUtil.getMD5(confirmPassword));
					userService.updateUserPwd(user);
					result.append("修改成功");
					session.setAttribute(Const.SESSION_USER, user);
				} else {
					result.append("两次输入密码不一致");
				}
			} else {
				result.append("密码设置不符合规则");
			}
		} else {
			result.append("密码不正确");
		}
		System.out.println(result);
		JsonResult<StringBuffer> jr = null;
		jr = new JsonResult<StringBuffer>(true, result);
		return jr;
	}

	/**
	 * 上传修改用户头像
	 * 
	 * @param files
	 * @param session
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update-img/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public String uploadUserPhoto(@RequestParam("file") CommonsMultipartFile[] files, HttpSession session,
			@PathVariable("userId") Long userId) throws Exception {
		// Boolean compressState = (Boolean)
		// session.getAttribute(Const.SESSION_IS_COMPRESSING);
		// UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		System.out.println(userId);
		UserInfo user = userService.getUserAllInfo(userId);
		String userPhotoName = UUID.randomUUID().toString();
		FileUtils.writeByteArrayToFile(
				new File(Const.ROOT_PATH + "users/" + user.getUserId() + "/photo/" + userPhotoName + ".png"),
				files[0].getBytes());
		File oldFile = new File(
				Const.ROOT_PATH + "users/" + user.getUserId() + "/photo/" + user.getUserPhoto() + ".png");
		user.setUserPhoto(userPhotoName);
		userService.updateUser(user);
		oldFile.delete();
		System.out.println(userPhotoName);
		session.setAttribute(Const.SESSION_USER, user);
		// FileInfoVO file = fileInfoService.getFileInfoByUuid(uuid);
		// FileUtils.writeByteArrayToFile(new File(Const.ROOT_PATH +
		// file.getFilePath()+".png"), files[0].getBytes());
		return "success";
	}

	/**
	 * 加载头像
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/photo/{userPhoto}", method = RequestMethod.GET)
	public String thumbnail(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@PathVariable("userPhoto") String userPhoto) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		String path = Const.ROOT_PATH + "users/" + user.getUserId() + "/photo/" + userPhoto + ".png";
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
}
