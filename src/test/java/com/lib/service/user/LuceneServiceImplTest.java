package com.lib.service.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.lib.dao.FileInfoDao;
import com.lib.entity.FileInfo;

import base.BaseTest;


public class LuceneServiceImplTest extends BaseTest {
	
	@Autowired
	LuceneService lucene;
	@Autowired
	FileInfoDao fileInfoDao;
	@Test
	public void testSearch() {
		
		FileInfo fileInfo=new FileInfo();
		fileInfo=fileInfoDao.getFileInfoByFileId(203L);
		fileInfo.setFileName("新建");
		System.out.println("test"+lucene.search(fileInfo, 1, 0));
		
	}

}
