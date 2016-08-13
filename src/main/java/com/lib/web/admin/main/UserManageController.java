package com.lib.web.admin.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.lib.utils.StringValueUtil;

@Controller
@RequestMapping("/admin")
public class UserManageController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	/**
	 * 分页以及查询
	 * @param pageNumber
	 * @param pageSize
	 * @param searcher
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user-list/{pageNumber}", method ={ RequestMethod.POST,RequestMethod.GET})
	public JsonResult<PagedResult<UserInfo>> getUserList(@PathVariable("pageNumber") Integer pageNumber, Integer pageSize, String searcher) {
		JsonResult<PagedResult<UserInfo>> jsonResult = null;
		try {
			// String searchText = new StringBuilder("%").append(searcher).append("%").toString();
			PagedResult<UserInfo> pagedResult = userService.queryByPage(searcher, pageNumber, pageSize);
			jsonResult = new JsonResult<PagedResult<UserInfo>>(true, pagedResult);
		} catch (Exception e) {
			jsonResult = new  JsonResult<PagedResult<UserInfo>>(false, e.getMessage());
		}
		return jsonResult;
	}
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/remove-user", method ={ RequestMethod.POST,RequestMethod.GET})
	public JsonResult removeUser(Long userId){
		JsonResult jsonResult = null;
		boolean flag = userService.deleteUserByUserId(userId);
		if(flag){
			jsonResult = new JsonResult(true, "success");
			jsonResult.setData("success");
		}
		else
			jsonResult = new JsonResult(false, "error");
		return jsonResult;
	}
	@ResponseBody
	@RequestMapping(value = "/remove-userList", method ={ RequestMethod.POST,RequestMethod.GET})
	public JsonResult removeUserList(String userList){
		JsonResult jsonResult = null;
		try{
		String users[] = userList.split("A");
		List<Long>list = new ArrayList<Long>();
		int i = 0;
		if("on".equals(users[0])){
			i = 1;
		}
		for(;i<users.length;i++){
			list.add( Long.parseLong(users[i]));
		}
		for(i = 0;i<list.size();i++){
			userService.deleteUserByUserId(list.get(i));
		}
			jsonResult = new JsonResult(true, "success");
			jsonResult.setData("success");
		}catch (Exception e) {
			jsonResult = new JsonResult(false, "error");
		}
		return jsonResult;
	}
	/**
	 * 初始化密码
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/refresh-userpwd", method ={ RequestMethod.POST,RequestMethod.GET})
	public JsonResult refreshUserPassword(Long userId){
		JsonResult jsonResult = null;
		try{
			UserInfo user = userService.getUserAllInfo(userId);
			user.setUserPassword(StringValueUtil.getMD5("12345"));
			 userService.updateUserPwd(user);
			jsonResult = new JsonResult(true, "success");
			jsonResult.setData("success");
		}catch(Exception e){
			jsonResult = new JsonResult(false, "error");
		}
		return jsonResult;
	}
	
	@ResponseBody
	@RequestMapping(value = "/change-usertype", method ={ RequestMethod.POST,RequestMethod.GET})
	public JsonResult changeUserType(Long userId,Integer userType){
		JsonResult jsonResult = null;
		try{
			UserInfo user = userService.getUserAllInfo(userId);
			user.setUserType(userType);
			 userService.updateUserType(user);
			jsonResult = new JsonResult(true, "success");
			jsonResult.setData("success");
		}catch(Exception e){
			jsonResult = new JsonResult(false, "error");
		}
		return jsonResult;
	}
}
