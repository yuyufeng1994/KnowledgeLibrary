package com.lib.service.user;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lib.entity.UserInfo;
import com.lib.utils.PagedResult;
import com.lib.utils.StringValueUtil;

import base.BaseTest;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

public class SpiderTest extends BaseTest{
	
	
	
	
	@Resource
	private UserService userService;
	
	
	@Resource
	private FileInfoService fileInfoService;
	
	@Test
	public void test() throws Exception{
		
		OOSpider.create(Site.me().setCharset("utf-8").setSleepTime(1000), new Pipeline(fileInfoService), WebMagicVo.class)
		.addUrl("http://baike.baidu.com/view/16168.htm").thread(5)
		.run();
	}
	
	@Test
	public void testCheckUserByEmail() throws Exception{
		UserInfo user = new UserInfo();
		user.setUserEmail("aa@qq.com");
		user.setUserPassword("123456789");
		userService.checkUserByEmail(user );
	}
	/*
	@Test
	public void pageUserByUserName(){
		PagedResult<UserInfo> pagedResult = userService.queryByPage("loveling", null, 10);
		List<UserInfo> list = pagedResult.getDataList();
		System.out.println(list.get(0));
		
	}
	*/
}
