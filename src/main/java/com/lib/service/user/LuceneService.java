package com.lib.service.user;

import java.util.List;

import com.lib.dto.LuceneSearchVo;
import com.lib.dto.PageVo;
import com.lib.entity.FileInfo;

public interface LuceneService {
	
	/**
	 * 全文检索
	 * @param fileInfo
	 * @return
	 */
	PageVo<LuceneSearchVo> search(FileInfo fileInfo,int pageNo,Integer flag);
	
	/**
	 * 添加一个索引
	 * @param fileInfo
	 */
	void addFileIndex(FileInfo fileInfo,String fileUserName);
	
	/**
	 * 删除一个索引
	 * @param fileInfo
	 */
	void deleteFileIndex(FileInfo fileInfo);
	
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
	
	
	
	List<String> getParagraph(Long fileId,String keyWord);

	
}
