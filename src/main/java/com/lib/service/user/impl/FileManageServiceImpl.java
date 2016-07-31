package com.lib.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lib.dao.FileInfoDao;
import com.lib.dto.FileInfoVO;
import com.lib.enums.Const;
import com.lib.service.user.FileManageService;

@Service
public class FileManageServiceImpl implements FileManageService {
	@Autowired
	private FileInfoDao fileInfoDao;

	@Override
	public PageInfo<FileInfoVO> getFileInfoPageByUserId(int pageNo, Long userId, String order) {
		PageHelper.startPage(pageNo, Const.COMMON_PAGE_SIZE, order);
		List<FileInfoVO> list = fileInfoDao.getFilesByUserId(userId);
		PageInfo<FileInfoVO> page = new PageInfo<FileInfoVO>(list);
		return page;
	}

}
