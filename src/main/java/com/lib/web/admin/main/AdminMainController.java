package com.lib.web.admin.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lib.entity.UserInfo;
import com.lib.enums.Const;

/**
 * 后台登录
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminMainController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpSession session,HttpServletRequest req) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		LOG.info(user.getUserEmail()+"进入管理员界面,ip:"+req.getLocalAddr());
		return "admin/index";
	}

}
