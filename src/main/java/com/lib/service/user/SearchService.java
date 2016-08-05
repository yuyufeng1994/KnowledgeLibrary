package com.lib.service.user;

import java.util.List;

import com.lib.dto.FileInfoVO;
import com.lib.entity.FileInfo;

public interface SearchService {
	
	/**
	 * 全文检索
	 * @param fileInfo
	 * @return
	 */
	List<FileInfoVO> search(FileInfo fileInfo);
	
	/**
	 * 添加一个索引
	 * @param fileInfo
	 */
	void addIndex(FileInfo fileInfo);
	
	/**
	 * 删除一个索引
	 * @param fileInfo
	 */
	void deleteIndex(FileInfo fileInfo);
	
	/**
	 * 获取关键字(知识点)
	 * @param fileInfo
	 * @param size 
	 * @return
	 */
	List<String> getKeyWord(FileInfo fileInfo,Long size);
	
	
	/**
	 * 获取简介
	 * @param fileInfo
	 * @param size
	 * @return
	 */
	List<String> getSummary(FileInfo fileInfo,Long size);
	
	/**
	 * 获取关联文档ids
	 * @param fileKeyWords
	 * @return
	 */
	List<Long>   getRelation(List<String> fileKeyWords);
}
