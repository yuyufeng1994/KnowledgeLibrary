package com.lib.service.user.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.DocInfoDao;
import com.lib.entity.DocInfo;
import com.lib.service.user.DocInfoService;

@Service
public class DocInfoServiceImpl implements DocInfoService {
	
	@Autowired
	private DocInfoDao docInfoDao;
	
	@Override
	public int insert(String docName, Long docUserId) {
		// TODO Auto-generated method stub
		docInfoDao.insert(docName, docUserId);
		return 0;
	}

	@Override
	public int delete(Long docId) {
		// TODO Auto-generated method stub
		docInfoDao.delete(docId);
		return 0;
	}

	@Override
	public DocInfo findById(Long docId) {
		// TODO Auto-generated method stub
		return docInfoDao.findById(docId);
	}

	@Override
	public List<DocInfo> findAllByUserId(Long docUserId) {
		// TODO Auto-generated method stub
		return docInfoDao.findAllByUserId(docUserId);
	
	}

}
