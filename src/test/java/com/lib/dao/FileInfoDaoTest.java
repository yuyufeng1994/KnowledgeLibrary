package com.lib.dao;

import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.lib.dto.FileInfoVO;
import com.lib.entity.FileInfo;
import com.lib.utils.StringValueUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class FileInfoDaoTest {

	@Resource
	private FileInfoDao fileInfoDao;

	@Test
	public void testInsert() throws Exception {
		FileInfo fi = new FileInfo();
		fi.setFileName("文件名");
		fi.setFileSize(1024l);
		fi.setFileExt("ppt");
		fi.setFileBrief("这是简介");
		fi.setFileUserId(2016001l);
		fi.setFileUuid(StringValueUtil.getUUID());
		fi.setFilePath("das/dasd");
		fi.setFileState(3);
		fi.setFileClassId(1l);

		fileInfoDao.insert(fi);
	}

	@Test
	public void testSelectByPrimaryKey() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKey() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByPrimaryKey() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFilesByUserId() throws Exception {
		// 分页
		PageHelper.startPage(1, 5, "file_id desc");
		List<FileInfoVO> list = fileInfoDao.getFilesByUserId(2016001l, "");
		for (FileInfoVO f : list) {
			System.out.println(f);
		}
	}

	@Test
	public void testGetFilesByState() throws Exception {
		// 分页
		PageHelper.startPage(1, 5, "file_id asc");
		List<FileInfo> list = fileInfoDao.getFilesByState(3);
		for (FileInfo f : list) {
			System.out.println(f);
		}
	}

	@Test
	public void testRootPath() throws Exception {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/jdbc.properties");
		prop.load(in);
		System.out.println(prop.getProperty("root_path"));
	}
}
