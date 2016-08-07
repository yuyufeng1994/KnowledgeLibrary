package com.lib.service.user.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;
import com.lib.dao.ClassificationDao;
import com.lib.dto.LuceneSearchVo;
import com.lib.entity.FileInfo;
import com.lib.service.user.LuceneService;
import com.lib.utils.LuceneIndexUtil;
import com.lib.utils.LuceneSearchUtil;

@Service
public class LuceneServiceImpl implements LuceneService {
	@Autowired
	private ClassificationDao classDao;
	@Override
	public PageInfo<LuceneSearchVo> search(FileInfo fileInfo,int pageNo,boolean flag) {
		
		String parentPath=classDao.findFatherPathById(fileInfo.getFileClassId());
		String[] parentIds=null;
		List<Long>  classIds=new ArrayList<Long>();
		if(parentPath!=null){
			parentIds=parentPath.split("\\.");
		}
		classIds.add(fileInfo.getFileClassId());
		for(int i=0;i<parentIds.length;i++)
		{  
			classIds.add(Long.valueOf(parentIds[i]));
		}
		List<LuceneSearchVo> list=LuceneSearchUtil.indexFileSearch(fileInfo, pageNo,10,classIds, flag);
		//System.out.println("list:"+list)
		PageInfo<LuceneSearchVo> pageInfo = new PageInfo<LuceneSearchVo>(list);
		//System.out.println(pageInfo);
		return pageInfo;
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
