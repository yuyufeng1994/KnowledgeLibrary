package com.lib.service.user;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.lib.dao.FileInfoDao;
import com.lib.dto.FileInfoVO;
import com.lib.entity.Classification;

import base.BaseTest;

public class FileManageServiceTest extends BaseTest {
	@Autowired
	private FileInfoService fileInfoService;

	@Autowired
	private FileManageService fileManageService;

	@Autowired
	private FileInfoDao fileInfoDao;

	@Test
	public void testGetAllChildClassesById() {
		Date d1 = new Date();
		List<Classification> list = fileManageService.getAllChildClassesById(1l);
		Date d2 = new Date();
		for (Classification c : list) {
			System.out.println(c);
		}
		System.out.println(d2.getTime() - d1.getTime());
	}
	@Test
	public void testGetAllChildFiles() throws Exception{
		PageInfo<FileInfoVO> page= fileManageService.getAllChildFiles(1, 2l);
		for(FileInfoVO f:page.getList()){
			System.out.println(f);
		}
	}
	

	@Test
	public void testGetFileInfoPageByUserId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClassificationByParentId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClassificationById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFatherClassesById() {
		fail("Not yet implemented");
	}
	
}
