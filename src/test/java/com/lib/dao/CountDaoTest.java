package com.lib.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lib.dto.ActiveUserInfo;
import com.lib.dto.ClassesClickInfo;
import com.lib.dto.ClickInfo;
import com.sun.star.util.DateTime;


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
	public void testClickHotClasses()throws Exception{
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -1); //设置为前1月
		dBefore = calendar.getTime(); //得到前1月的时间

		List<ClassesClickInfo> list = countDao.getHotClasses(dBefore);
		System.out.println(list);
	}
	@Test
	public void testGetCountUserFiles(){
		Long l = countDao.getCountUserFiles(2016001L);
		System.out.println(l);
	}
	
	@Test
	public void getForkFileTimesTest(){
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -1); //设置为前1月
		dBefore = calendar.getTime(); //得到前1月的时间

		Long l = countDao.getForkFileTimes(dBefore,1l);
		System.out.println(l);
	}
	@Test
	public void getClickTimesTest(){
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -1); //设置为前1月
		dBefore = calendar.getTime(); //得到前1月的时间

		Long l = countDao.getClickTimes(dBefore,1l);
		System.out.println(l);
	}
	@Test
	public void getActiveUsersTest(){
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -1); //设置为前1月
		dBefore = calendar.getTime(); //得到前1月的时间
		List<ActiveUserInfo> activeUserInfos = countDao.getActiveUsers(dBefore);
		System.out.println(activeUserInfos);
	}
	@Test
	public void getUserForkFileTimes(){
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -1); //设置为前1月
		dBefore = calendar.getTime(); //得到前1月的时间
//		List<ActiveUserInfo> activeUserInfos = countDao.getActiveUsers(dBefore);
		System.out.println(1+""+countDao.getUserForkFileTimes(dBefore, 2016001l));
	}
	
	@Test
	public void getUserClickTimes(){
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -1); //设置为前1月
		dBefore = calendar.getTime(); //得到前1月的时间
//		List<ActiveUserInfo> activeUserInfos = countDao.getActiveUsers(dBefore);
		System.out.println(countDao.getUserClickTimes(dBefore, 2016001l));
	}
	@Test
	public void getUploadTimesByTimeTest(){
		Date now = new Date(); //当前时间
		Date before = null;
		List<Long> list = new ArrayList<Long>();
		for(int i = 1;i<=30;i++){
		Calendar   cal   =   Calendar.getInstance();
		 cal.add(Calendar.DATE,   -i);
		 before = cal.getTime();
		 now.setHours(0);
		 before.setHours(0);
		 System.out.println("before:"+before.getTime()+"now"+now.toLocaleString());
		 Long l = countDao.getUploadTimesByTime(before, now);
		 list.add(l);
		 now = before;
		}
		System.out.println(list);
	}
}
