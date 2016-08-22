package com.lib.zddData;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lib.dao.ClassificationDao;
import com.lib.dao.FileInfoDao;
import com.lib.dao.UserInfoDao;
import com.lib.entity.Classification;
import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.admin.CountService;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.FileManageService;
import com.lib.service.user.UserService;
import com.lib.utils.StringValueUtil;

import base.BaseTest;

public class FileInfoServiceTest extends BaseTest {

	@Autowired
	private FileInfoService fileInfoService;

	@Autowired
	private FileManageService fileManageService;

	@Autowired
	private ClassificationDao cdao;

	@Autowired
	private UserInfoDao userDao;

	@Autowired
	private FileInfoDao fileInfoDao;

	/**
	 * 插入用户
	 */
	@Test
	public void testInsertUser() {
		/**
		 * 插入一百个用户
		 */
		UserInfo user = new UserInfo();
		for (int i = 100; i < 120; i++) {
			user.setUserEmail(i + "@sok.com");
			user.setUserName("测试用户：" + i);
			user.setUserType(1);
			user.setUserPassword(StringValueUtil.getMD5("12345"));
			int res = userDao.insertSelective(user);
			System.out.println(res + " " + i);
		}

	}

	@Test // 获得一个随机的用户
	public void testGetUser() {
		List<UserInfo> ulist = userDao.select();
		UserInfo user = null;
		Random ran = new Random();
		int ui = 0;
		for (int i = 0; i < 30; i++) {
			ui = ran.nextInt(ulist.size());
			user = ulist.get(ui);
			System.out.println(user);
		}
	}

	@Test // 获得一个随机的分类
	public void testGetClass() {
		List<Classification> classes = fileManageService.getAllChildClassesById(1l);
		Classification ci = null;
		Random ran = new Random();
		int cix = 0;
		for (int i = 0; i < 10; i++) {
			cix = ran.nextInt(classes.size());
			ci = classes.get(cix);
			System.out.println(ci);
		}
	}

	@Test // 插入文件
	public void testInsertFile() {
		insertFile(100000);
	}

	public void insertFile(int len) {
		List<Classification> classes = fileManageService.getAllChildClassesById(1l);
		Classification ci = null;
		Random ran = new Random();
		int cix = 0;

		List<UserInfo> ulist = userDao.select();
		UserInfo user = null;
		Random ran2 = new Random();
		int ui = 0;

		FileInfo fi = null;
		for (int i = 0; i < len; i++) {

			cix = ran.nextInt(classes.size());
			ci = classes.get(cix);

			ui = ran2.nextInt(ulist.size());
			user = ulist.get(ui);

			fi = new FileInfo();
			fi.setFileName("测试文件" + new Date().toString());
			fi.setFileSize(822l);
			fi.setFileExt("txt");
			fi.setFileBrief("这是测试文件" + new Date().toString());
			fi.setFileUserId(user.getUserId());
			fi.setFileUuid("e1057cd2-d696-435c-bc43-4f210ec5df94");
			File f = new File("users/" + user.getUserId() + "/files/");
			if (!f.exists()) {
				f.mkdirs();
			}
			fi.setFilePath("users/2016001/files/7c313233-99c5-41c8-b6d3-2d2d991601b7");
			fi.setFileState(5);
			fi.setFileClassId(ci.getClassificationId());

			int res = fileInfoDao.insert(fi);
			System.out.println(res + " " + fi.getFileName());
		}

	}

	@Test
	public void testGetFileByUuid() {
		fileInfoService.getFileInfoByUuid("e1057cd2-d696-435c-bc43-4f210ec5df94");
	}

	@Test
	public void testSearchFileInfoByNameOrId() {

	}

	@Test
	public void testRun() throws Exception {
		new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + " " + i);
				}
			};
		}.start();
		new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + " " + i);
				}
			};
		}.start();
	}

	public static void main(String[] args) {

	}

}
