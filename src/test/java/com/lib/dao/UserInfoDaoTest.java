package com.lib.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lib.entity.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class UserInfoDaoTest {
	@Resource
	private UserInfoDao userInfoDao;

	@Test
	public void testQueryById() throws Exception {
		long id = 2016001l;
		UserInfo user = userInfoDao.queryById(id);
		System.out.println(user);

	}

}
