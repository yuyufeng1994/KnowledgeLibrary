package com.lib.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lib.dto.ClickInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class CountDaoTest {
	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private CountDao countDao;
	private final static Logger LOG = LoggerFactory.getLogger(CountDaoTest.class);

	@Test
	public void testClickHotFiles()throws Exception{
		List<ClickInfo> list = countDao.getHotFiles();
		System.out.println(list);
	}
	@Test
	public void testGetCountUserFiles(){
		Long l = countDao.getCountUserFiles(2016001L);
		System.out.println(l);
	}
}
