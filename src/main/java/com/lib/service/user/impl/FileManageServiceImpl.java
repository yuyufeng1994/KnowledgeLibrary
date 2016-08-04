package com.lib.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lib.dao.ClassificationDao;
import com.lib.dao.FileInfoDao;
import com.lib.dto.FileInfoVO;
import com.lib.entity.Classification;
import com.lib.enums.Const;
import com.lib.service.user.FileManageService;

@Service
public class FileManageServiceImpl implements FileManageService {
	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private ClassificationDao classDao;
	@Override
	public PageInfo<FileInfoVO> getFileInfoPageByUserId(int pageNo, Long userId, String order,String searchValue) {
		PageHelper.startPage(pageNo, Const.COMMON_PAGE_SIZE, order);
		List<FileInfoVO> list = fileInfoDao.getFilesByUserId(userId,"%"+searchValue+"%");
		PageInfo<FileInfoVO> page = new PageInfo<FileInfoVO>(list);
		return page;
	}

	@Override
	public List<Classification> getClassificationByParentId(Long parentId) {
		
		return classDao.findAllChildById(parentId);
	}

	@Override
	public Classification getClassificationById(Long fileClassId) {
		return classDao.findById(fileClassId);
	}

	@Override
	public List<Classification> getFatherClassesById(Long fileClassId) {
		Classification c = null;
		List<Classification> list = new ArrayList<>();
		c = classDao.findById(fileClassId);
		list.add(c);
		while(c.getParentId() != null){
			c = classDao.findById(c.getParentId());
			list.add(0, c);
		}
		return list;
	}

}
