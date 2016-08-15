package com.lib.web.admin.main;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String index() {
		LOG.info("主页info:" + new Date());
		LOG.debug("主页bug:" + new Date());
		LOG.debug("输出debug级别的日志.....");
		LOG.info("输出info级别的日志.....");
		LOG.error("输出error级别的日志.....");
		return "admin/index";
	}

}
