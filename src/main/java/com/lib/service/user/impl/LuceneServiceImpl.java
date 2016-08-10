package com.lib.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lib.dao.ClassificationDao;
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
	@Override
	public PageVo<LuceneSearchVo> search(FileInfo fileInfo,String keyWord,Date endTime,int pageNo,Integer flag) {
		
		List<Classification> childIds=classDao.findAllChildById(fileInfo.getFileClassId()+"");
		List<Long>  classIds=new ArrayList<Long>();
		if(childIds!=null){
			for(int i=0;i<childIds.size();i++)
			{
				classIds.add(childIds.get(i).getClassificationId());
			}
		}
		classIds.add(fileInfo.getFileClassId());
		List<LuceneSearchVo> list=LuceneSearchUtil.indexFileSearch(fileInfo,keyWord,endTime,pageNo,10,classIds, flag);
		PageVo<LuceneSearchVo> page=new PageVo<LuceneSearchVo>();
		page.setPageNum(pageNo);
		page.setData(list);
		page.setPageSize(10);
		List<Integer> navigatepageNums=new ArrayList<Integer>();
		for(int i=pageNo,j=3;i>=1&&j>=1;i--,j--)
		{
			int index=pageNo-j;
			if(index>0)
			navigatepageNums.add(index);
			
		}
		
		for(int i=pageNo,j=3;i<=LuceneSearchUtil.totalPage()&&j>=1;i++,j--)
		{
			navigatepageNums.add(i);
		}
		page.setNavigatepageNums(navigatepageNums);
		//page.setNavigatepageNums();
		page.setRowCount(LuceneSearchUtil.totalPage());
		
		return page;
	}

	@Override
	public void addFileIndex(FileInfo fileInfo,String fileUserName) {
		LuceneIndexUtil.addFileIndex(fileInfo,fileUserName);
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
	public List<String> getSummary(FileInfo fileInfo, Long size) {
		// TODO Auto-generated method stub
		return LuceneSearchUtil.extractSummary(fileInfo.getFileId(),size);
	}

	@Override
	public List<Long> getRelation(List<String> fileKeyWords) {
		// TODO Auto-generated method stub
		return LuceneSearchUtil.extractRelation(fileKeyWords);
	}

	@Override
	public List<SerResult> getParagraph(String keyWord) {
		// TODO Auto-generated method stub
		return LuceneSearchUtil.extractParagrap(keyWord);
	}
	
	

}
