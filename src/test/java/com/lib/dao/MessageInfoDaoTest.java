package com.lib.dao;

import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lib.entity.MessageInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class MessageInfoDaoTest {
	@Resource
	private MessageInfoDao msgDao;
	@Test
	public void queryMsgByUserId() {
		List<MessageInfo> list = msgDao.queryMsgByUserId(2016001L);
		System.out.println(list);
	}
	@Test
	public void countMsgByUserId() {
		Long l= msgDao.countMsgByUserId(2016001l);
		System.out.println(l);
	}
	@Test
	public void isReadMsg() {
		 msgDao.isReadMsg(2l);
	}
	@Test
	public void delete() {
		 msgDao.deleteMsgByMsgId(10l);
	}
}
