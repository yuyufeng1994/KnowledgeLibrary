package com.lib.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lib.entity.UserInfo;
import com.lib.entity.UserRegister;
import com.lib.utils.StringValueUtil;

import base.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class UserRegisterDaoTest {
	@Resource
	private UserRegisterDao userRegisterDao;
	private final static Logger LOG = LoggerFactory.getLogger(UserRegisterDaoTest.class);

	@Test
	public void testInsertNoStatus(){
		UserRegister ur = new UserRegister();
		ur.setUserId(1L);
		ur.setValidateCode("111");
		ur.setRegisterTime(new Date());
		userRegisterDao.insertNoStatus(ur);
	}
	@Test
	public void testupdateNoStatus(){
		UserRegister ur = userRegisterDao.queryById(2016003L);
		System.out.println(ur.getRegisterId());
		ur.setValidateCode("124");
		ur.setRegisterTime(new Date());
		userRegisterDao.updateNoStatus(ur);
	}
	@Test
	public void testQueryById(){
		UserRegister ur = userRegisterDao.queryById(2016003L);
		System.out.println(ur);
	}
}
