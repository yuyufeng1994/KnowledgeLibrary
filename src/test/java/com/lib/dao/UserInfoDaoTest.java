package com.lib.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lib.entity.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class UserInfoDaoTest {
	@Resource
	private UserInfoDao userInfoDao;
	private final static Logger LOG = LoggerFactory.getLogger(UserInfoDaoTest.class);

	@Test
	public void testGetFile() {
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile("i://1.txt", "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1;
			String line;
			rf.seek(nextend);
			int c = -1;
			int getLen = 35;
			int getIndex = 0;
			
			while (nextend > start) {
				
				c = rf.read();
				if (c == '\n' || c == '\r') {
					getIndex++;
					if(getLen<getIndex){
						break;
					}
					line = rf.readLine();
					if (line != null) {
						System.out.println(new String(line.getBytes("iso-8859-1")));
					} else {
						System.out.println(line);
					}
					nextend--;
				}
				nextend--;
				rf.seek(nextend);
				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
					// System.out.println(rf.readLine());
					System.out.println("zuihou"+new String(rf.readLine().getBytes("iso-8859-1")));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rf != null)
					rf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testLOGBACK() {
		System.out.println(LOG);//Logger[com.lib.dao.UserInfoDaoTest]
		LOG.info("info 成功了");
		LOG.debug("debug成功了");
		LOG.error("error");
		LOG.warn("warn");
		LOG.info("主页info:" + new Date());
		LOG.debug("主页bug:" + new Date());
	}

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

	@Test
	public void testInsertUserNoStatus() {
		UserInfo user = new UserInfo();
		user.setUserEmail("1231354");
		user.setUserName("gg");
		user.setUserPassword("ggg");
		user.setUserType(2);
		userInfoDao.insertUserNoStatus(user);
	}

	@Test
	public void testUpdateUserNoStatus() {
		UserInfo user = userInfoDao.queryByEmail("1231354");
		user.setUserEmail("fdf");
		user.setUserName("發");
		user.setUserPassword("飛");
		user.setUserType(2);
		userInfoDao.updateUserNoStatus(user);
	}

	@Test
	public void testSelectAllUser() {
		List<UserInfo> list = userInfoDao.selectAllUserByUserName("loveling");
		System.out.println(list);
	}

	@Test
	public void testRemoveUserById() {
		userInfoDao.deleteUserById(2016013l);
	}
}
