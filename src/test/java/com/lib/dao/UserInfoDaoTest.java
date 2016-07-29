package com.lib.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lib.entity.UserInfo;
import com.lib.utils.StringValueUtil;

import base.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class UserInfoDaoTest {
	@Resource
	private UserInfoDao userInfoDao;
	private final static Logger LOG = LoggerFactory.getLogger(UserInfoDaoTest.class);

	@Test
	public void testQueryById() throws Exception {
		LOG.info("logback 成功了");
		long id = 2016001l;
		UserInfo user = userInfoDao.queryById(id);
		System.out.println(user);
	}
	
	@Test
	public void testQueryByEmail() throws Exception {
		String email = "aa@qq.com";
		UserInfo user = userInfoDao.queryByEmail(email);
		System.out.println(user);
	}
	
	
	
	

}
