package com.lib.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lib.dao.ClassificationDao;
import com.lib.dto.LuceneSearchVo;
import com.lib.dto.PageVo;
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
		
		String parentPath=classDao.findFatherPathById(fileInfo.getFileClassId());
		String[] parentIds=null;
		List<Long>  classIds=new ArrayList<Long>();
		if(parentPath!=null){
			parentIds=parentPath.split("\\.");
			for(int i=0;i<parentIds.length;i++)
			{  
				classIds.add(Long.valueOf(parentIds[i]));
			}
		}
		classIds.add(fileInfo.getFileClassId());
		List<LuceneSearchVo> list=LuceneSearchUtil.indexFileSearch(fileInfo,keyWord,endTime,pageNo,10,classIds, flag);
		PageVo<LuceneSearchVo> page=new PageVo<LuceneSearchVo>();
		page.setPageNum(pageNo);
		page.setData(list);
		page.setPageSize(10);
		page.setNavigatepageNums();
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
	public List<String> getParagraph(Long fileId, String keyWord) {
		// TODO Auto-generated method stub
		return LuceneSearchUtil.extractParagrap(fileId, keyWord);
	}
	
	

}
