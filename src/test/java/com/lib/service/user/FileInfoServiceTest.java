package com.lib.service.user;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lib.dao.FileInfoDao;
import com.lib.entity.FileInfo;
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
	public void testSearchFileInfoByNameOrId() {
		List<FileInfo> list = fileInfoService.searchFileInfoByNameOrId("310", 2016001l);
		for (FileInfo f : list) {
			System.out.println(f);
		}
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
