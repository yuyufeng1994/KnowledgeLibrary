package com.lib.service.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lib.dto.FileInfoVO;
import com.lib.entity.FileInfo;
import com.lib.service.user.LuceneService;
import com.lib.utils.LuceneIndexUtil;
import com.lib.utils.LuceneSearchUtil;

@Service
public class LuceneServiceImpl implements LuceneService {

	@Override
	public List<FileInfoVO> search(FileInfo fileInfo) {
		Integer pageNo=0;
		List<Long> fileClassId=null;
		boolean flag=true;
		List<Map<String, String>> page = LuceneSearchUtil.indexFileSearch(fileInfo, pageNo, fileClassId, flag);
		return null;
	}

	@Override
	public void addFileIndex(FileInfo fileInfo) {
		LuceneIndexUtil.addFileIndex(fileInfo);
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
