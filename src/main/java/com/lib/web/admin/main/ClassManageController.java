package com.lib.web.admin.main;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.JsonResult;
import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;

/**
 * 后台登录
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/admin")
public class ClassManageController {
	/**
	 * 分类管理主页
	 * @return
	 */
	@RequestMapping(value = "/class-manage-ui", method = RequestMethod.GET)
	public String ManageUI() {
		
		return "admin/class-manage";
	}


}
