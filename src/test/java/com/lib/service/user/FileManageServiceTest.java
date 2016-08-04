package com.lib.service.user;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lib.dao.FileInfoDao;
import com.lib.entity.Classification;

import base.BaseTest;

public class FileManageServiceTest  extends BaseTest{
	@Autowired
	private FileInfoService fileInfoService;
	
	@Autowired
	private FileManageService fileManageService;

	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Test
	public void testGetAllChildClassesById() {
		List<Classification> list = fileManageService.getAllChildClassesById(1l);
		for(Classification c:list){
			System.out.println();
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
