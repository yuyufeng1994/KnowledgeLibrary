package com.lib.service.user;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lib.dao.FileInfoDao;
import com.lib.enums.Const;

import base.BaseTest;

public class FileInfoServiceTest extends BaseTest {

	@Autowired
	private FileInfoService fileInfoService;

	@Autowired
	private FileInfoDao fileInfoDao;

	@Test
	public void testInsertFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompressFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartTransfor() {
		fail("Not yet implemented");
	}

	@Test
	public void testTranslateFile() throws Exception {
		Const.loadRootPath();
		new Thread() {
			public void run() {
			};
		}.start();
		fileInfoService.translateFile("ea333229-5c9e-4987-9ade-4dfda42d2362");
	}

}
