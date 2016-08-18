package com.lib.web.admin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lib.dao.ClassificationDao;
import com.lib.dao.UserInfoDao;
import com.lib.entity.Classification;
import com.lib.entity.UserInfo;
import com.lib.service.admin.ClassificationService;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.FileManageService;
import com.lib.utils.StringValueUtil;

/**
 * 后台登录
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/init")
public class InitController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserInfoDao userDao;
	@Autowired
	private FileInfoService fileService;
	@Autowired
	private FileManageService fileManageService;
	@Autowired
	private ClassificationDao classDao;
	
	@RequestMapping(value = "/start/{validation}", method = RequestMethod.GET)
	public String start(Model model, @PathVariable("validation") String validation) {
		if (null != validation && !"".equals(validation)) {
			
			//加入超级管理员用户
			UserInfo user = new UserInfo();
			user.setUserEmail("admin@sok.com");
			user.setUserType(0);
			user.setUserName("admin");
			user.setUserPassword(StringValueUtil.getMD5("12345"));
			userDao.insertSelective(user);
			Classification c = new Classification();
			c.setClassificationId(1l);
			c.setClassificationName("所有分类");
			c.setClassificationPicture("all");
			c.setClassificationBrief("所有分类的最高父节点");
			//加入初始化分类
			classDao.insertByc(c);
			
			model.addAttribute("message", "初始化成功！<br>（管理员帐号：admin@sok.com 密码：12345 ）");
		} else {
			model.addAttribute("message", "初始化失败！");
		}

		return "message";
	}

}
