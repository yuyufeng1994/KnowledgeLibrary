package com.lib.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.FileInfoDao;
import com.lib.entity.FileInfo;
import com.lib.service.user.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	private FileInfoDao fileinfoDao;

	@Override
	public int insertFile(FileInfo fileInfo) {

		return fileinfoDao.insert(fileInfo);
	}

}
