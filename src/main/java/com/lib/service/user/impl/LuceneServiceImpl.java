package com.lib.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lib.dao.ClassificationDao;
import com.lib.dao.FileInfoDao;
import com.lib.dao.UserInfoDao;
import com.lib.dto.LuceneSearchVo;
import com.lib.dto.PageVo;
import com.lib.dto.SerResult;
import com.lib.entity.Classification;
import com.lib.entity.FileInfo;
import com.lib.service.user.LuceneService;
import com.lib.utils.LuceneIndexUtil;
import com.lib.utils.LuceneSearchUtil;

@Service
public class LuceneServiceImpl implements LuceneService {
	@Autowired
	private ClassificationDao classDao;
	@Autowired
	private   FileInfoDao  fileInfoDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Override
	public PageVo<LuceneSearchVo> search(FileInfo fileInfo,String keyWord,Date endTime,int pageNo,Integer flag) {
		
		List<Long>  classIds=new ArrayList<Long>();
		
		if(fileInfo.getFileClassId()!=1L)
		{    
			List<Classification> childIds = classDao.findAllChildById("%"+fileInfo.getFileClassId()+"%");
			if (childIds != null) {
				for (int i = 0; i < childIds.size(); i++) {
					classIds.add(childIds.get(i).getClassificationId());
				}
			}
			classIds.add(fileInfo.getFileClassId());
		}else
		{
			classIds=null;
		}
		List<LuceneSearchVo> list=LuceneSearchUtil.indexFileSearch(fileInfo,keyWord,endTime,pageNo,10,classIds, flag);
		PageVo<LuceneSearchVo> page=new PageVo<LuceneSearchVo>();
		page.setPageNum(pageNo);
		page.setData(list);
		page.setPageSize(10);
		page.setRowCount(LuceneSearchUtil.totalPage());
		List<Integer> navigatepageNums=new ArrayList<Integer>();
		int index=(pageNo-6)>0?(pageNo-6):1;
		for(int i=index,j=6;i<pageNo&&j>=1;i++,j--)
		{	
			navigatepageNums.add(i);
		}
		
		for(int i=pageNo,j=6;i<=page.getTotalPage()&&j>=1;i++,j--)
		{
			navigatepageNums.add(i);
		}
		page.setNavigatepageNums(navigatepageNums);
		//page.setNavigatepageNums();
		page.setRowCount(LuceneSearchUtil.totalPage());
		
		return page;
	}

	@Override
	public void addFileIndex(FileInfo fileInfo,String fileUserName,String fileText) {
		LuceneIndexUtil.addFileIndex(fileInfo,fileUserName,fileText);
	}

	@Override
	public void deleteFileIndex(FileInfo fileInfo) {
		LuceneIndexUtil.deteleFileIndex(fileInfo);
	}

	@Override
	public List<String> getKeyWord(FileInfo fileInfo, Long size) {
		// TODO Auto-generated method stub
		return LuceneSearchUtil.extractKeyword(fileInfo.getFileId(),size);
	}

	@Override
	public String getSummary(FileInfo fileInfo, Long size) {
		// TODO Auto-generated method stub
		return LuceneSearchUtil.extractSummary(fileInfo.getFileId(),size);
	}

	@Override
	public List<Long> getRelation(String fileName) {
		// TODO Auto-generated method stub
		return LuceneSearchUtil.extractRelation(fileName);
	}

	@Override
	public List<SerResult> getParagraph(String keyWord,Long size) {
		// TODO Auto-generated method stub
		return LuceneSearchUtil.extractParagrap(keyWord,size);
	}

	

	@Override
	public void deleteAllIndex() {
		// TODO Auto-generated method stub
		List<FileInfo> lists=fileInfoDao.getFilesByState(5);
		for(FileInfo list:lists)
		{
			LuceneIndexUtil.deteleFileIndex(list);
		}
	}

	@Override
	public void addAllIndex() {
		// TODO Auto-generated method stub
		List<FileInfo> lists=fileInfoDao.getFilesByState(5);
		for(FileInfo list:lists)
		{
			String fileText = LuceneSearchUtil.judge(list.getFileId());
			System.out.println(fileText);
			LuceneIndexUtil.addFileIndex(list,userInfoDao.queryById(list.getFileUserId()).getUserName(),fileText);
		}
		
	}

	
	

}
