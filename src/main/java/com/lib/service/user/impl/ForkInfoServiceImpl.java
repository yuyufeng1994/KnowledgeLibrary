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
		return forkInfoDao.insert(forkInfo);
	}

	@Override
	public int delete(Long forkId) {
		// TODO Auto-generated method stub
		return forkInfoDao.delete(forkId);
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
	public PageInfo<ForkFileInfoVo> getFileForkInfoPageByUserId(int pageNo,Long userId,Long docId,String userName,String search) {
		PageHelper.startPage(pageNo, Const.COMMON_PAGE_SIZE);
		List<ForkFileInfoVo> forkFileInfos=null;
		if(search!=null)
		{	
			if(docId==-1){
				forkFileInfos = forkInfoDao.findByFileName("%"+search+"%", userId);
			}
			else
			{
				forkFileInfos = forkInfoDao.findByFileNameAndDocId("%"+search+"%", userId,docId);
			}
			
		}
		else{
			if (docId == -1) {
				forkFileInfos = forkInfoDao.findAll(userId);
			} else {

				forkFileInfos = forkInfoDao.findAllByDocId(docId, userId);
			}
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
		List<ForkFileInfoVo> forkFileInfos=forkInfoDao.findByFileName("%"+fileName+"%", docUserId);
		
		PageInfo<ForkFileInfoVo> page = new PageInfo<ForkFileInfoVo>(forkFileInfos);
		return page;
	}

	@Override
	public ForkInfo findByFileId(Long fileId, Long docUserId) {
		
		return forkInfoDao.findByFileId(fileId, docUserId);
	}
	
}
