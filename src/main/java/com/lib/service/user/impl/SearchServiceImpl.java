package com.lib.service.user.impl;

import java.util.List;

import com.lib.dto.FileInfoVO;
import com.lib.entity.FileInfo;
import com.lib.service.user.SearchService;
import com.lib.utils.LuceneIndexUtil;

public class SearchServiceImpl implements SearchService {

	@Override
	public List<FileInfoVO> search(FileInfo fileInfo) {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public List<String> getSummary(FileInfo fileInfo, Long size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getRelation(List<String> fileKeyWords) {
		// TODO Auto-generated method stub
		return null;
	}
	
		

}
