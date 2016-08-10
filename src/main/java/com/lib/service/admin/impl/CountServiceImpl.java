package com.lib.service.admin.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.CountDao;
import com.lib.dao.FileInfoDao;
import com.lib.dto.ClickInfo;
import com.lib.entity.FileInfo;
import com.lib.service.admin.CountService;

@Service
public class CountServiceImpl implements CountService {
	@Autowired
	private CountDao countDao;
	
	@Autowired
	private FileInfoDao fileInfoDao;

	@Override
	public List<ClickInfo> getHotFiles() {
		return countDao.getHotFiles();
	}
}
