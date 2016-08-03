package com.lib.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lib.dao.FileInfoDao;
import com.lib.dao.ForkInfoDao;
import com.lib.dto.FileInfoVO;
import com.lib.dto.ForkFileInfoVo;
import com.lib.entity.FileInfo;
import com.lib.entity.ForkInfo;
import com.lib.enums.Const;
import com.lib.service.user.ForkInfoService;

@Service
public class ForkInfoServiceImpl implements ForkInfoService {
	
	@Autowired
	private ForkInfoDao forkInfoDao;
	
	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Override
	public int insert(ForkInfo forkInfo) {
		// TODO Auto-generated method stub
		forkInfoDao.insert(forkInfo);
		return 0;
	}

	@Override
	public int delete(Long forkId) {
		// TODO Auto-generated method stub
		forkInfoDao.delete(forkId);
		return 0;
	}

	@Override
	public List<ForkInfo> findByDocId(Long docId) {
		// TODO Auto-generated method stub
		
		return forkInfoDao.findByDocId(docId);
	}
	
    
	@Override
	public List<ForkFileInfoVo> findAll(Long docUserId) {
		// TODO Auto-generated method stub
		return forkInfoDao.findAll(docUserId);
	}

	@Override
	public List<ForkFileInfoVo> findAllByDocId(Long docId, Long docUserId) {
		// TODO Auto-generated method stub
		return forkInfoDao.findAllByDocId(docId, docUserId);
	}

	@Override
	public ForkInfo findByForkId(Long forkId) {
		
		return forkInfoDao.findByForkId(forkId);
	}
	
	@Override
	public PageInfo<ForkFileInfoVo> getFileForkInfoPageByUserId(int pageNo,Long userId,Long docId,String userName) {
		PageHelper.startPage(pageNo, Const.COMMON_PAGE_SIZE);
		List<ForkFileInfoVo> forkFileInfos=null;
		if(docId==0)
		{
		forkFileInfos=forkInfoDao.findAll(userId);
		}else{
			
			forkFileInfos=forkInfoDao.findAllByDocId(docId, userId);
		}
	
		for(ForkFileInfoVo f:forkFileInfos)
		{	
			FileInfo fileInfo=fileInfoDao.getFileInfoByFileId(f.getFileId());
			f.setFileName(fileInfo.getFileName());
			f.setFileUuId(fileInfo.getFileUuid());
			f.setUserName(userName);
		}
		PageInfo<ForkFileInfoVo> page = new PageInfo<ForkFileInfoVo>(forkFileInfos);
		return page;
	}

	@Override
	public int modify(ForkInfo forkInfo) {
		
		return forkInfoDao.modify(forkInfo);
	}

	@Override
	public PageInfo<ForkFileInfoVo> findByFileName(int pageNo,String fileName, Long docUserId) {
		PageHelper.startPage(pageNo, Const.COMMON_PAGE_SIZE);
		List<ForkFileInfoVo> forkFileInfos=forkInfoDao.findByFileName(fileName, docUserId);
		System.out.println(forkFileInfos);
		PageInfo<ForkFileInfoVo> page = new PageInfo<ForkFileInfoVo>(forkFileInfos);
		return page;
	}
	
}
