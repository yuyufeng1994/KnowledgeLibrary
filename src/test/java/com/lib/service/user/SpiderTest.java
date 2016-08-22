package com.lib.service.user;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lib.dao.ClassificationDao;
import com.lib.dao.FileInfoDao;
import com.lib.entity.Classification;
import com.lib.entity.FileInfo;

import base.BaseTest;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

public class SpiderTest extends BaseTest{
	
	
	
	
	@Resource
	private UserService userService;
	
	@Resource
	private ClassificationDao classificationDao;
	
	@Resource
	private FileInfoService fileInfoService;
	
	@Resource
	private FileInfoDao fileInfoDao;
	
	@Test
	public void test() throws Exception{
		
		OOSpider.create(Site.me().setCharset("utf-8").setSleepTime(1000), new Pipeline(fileInfoService), WebMagicVo.class)
		.addUrl("http://www.yiibai.com/article/4788.html").thread(5)
		.run();
	}
	
	@Test
	public void testCheckUserByEmail() throws Exception{
		List<Long> list=new ArrayList<Long>();
		list.add(29L);
		list.add(30L);
		list.add(29L);
		list.add(32L);
		list.add(33L);
		list.add(34L);
		list.add(35L);
		list.add(36L);
		list.add(37L);
		list.add(38L);
		list.add(40L);
		list.add(41L);
		list.add(42L);
		list.add(43L);
		list.add(50L);
		list.add(51L);
		for(FileInfo vo:fileInfoDao.getFilesByState(5))
		{
			int x=((int)(Math.random()*15));
			vo.setFileClassId(list.get(x));
			fileInfoDao.updateByUuid(vo);
		}
		
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
