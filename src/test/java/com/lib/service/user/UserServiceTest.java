package com.lib.service.user;


import javax.annotation.Resource;

import org.junit.Test;

import com.lib.entity.UserInfo;
import com.lib.utils.StringValueUtil;

import base.BaseTest;

public class UserServiceTest extends BaseTest{
	
	@Resource
	private UserService userService;
	@Test
	public void test() throws Exception{
		String str="12345";
		System.out.println(StringValueUtil.getMD5(str));
	}
	
	@Test
	public void testCheckUserByEmail() throws Exception{
		UserInfo user = new UserInfo();
		user.setUserEmail("aa@qq.com");
		user.setUserPassword("123456789");
		userService.checkUserByEmail(user );
	}

}
