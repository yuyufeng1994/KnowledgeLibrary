package com.lib.web.admin.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.JsonResult;
import com.lib.entity.UserInfo;
import com.lib.service.user.UserService;
import com.lib.utils.PagedResult;

@Controller
@RequestMapping("/admin")
public class UserManageController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/user-list/{pageNumber}", method ={ RequestMethod.POST,RequestMethod.GET})
	public JsonResult<PagedResult<UserInfo>> getUserList(@PathVariable("pageNumber") Integer pageNumber, Integer pageSize, String searcher) {
		JsonResult<PagedResult<UserInfo>> jsonResult = null;
		try {
			System.out.println(pageNumber);
			// String searchText = new StringBuilder("%").append(searcher).append("%").toString();
			PagedResult<UserInfo> pagedResult = userService.queryByPage(searcher, pageNumber, pageSize);
			jsonResult = new JsonResult<PagedResult<UserInfo>>(true, pagedResult);
			System.out.println(pagedResult.getDataList());
		} catch (Exception e) {
			jsonResult = new  JsonResult<PagedResult<UserInfo>>(false, e.getMessage());
		}
		return jsonResult;
	}
}
