package com.lib.service.user;

import java.util.List;

import com.lib.entity.DocInfo;

public interface DocInfoService {
	
	/**
	 * 创建一个新的文件夹
	 * @param docName
	 * @param docUserId
	 * @return
	 */
	int insert(String docName,Long docUserId);
	
	/**
	 * 删除一个文件夹
	 * @param docId
	 * @return
	 */
	int delete(Long docId);
	
	/**
	 * 查找一个文件夹
	 * @param docId
	 * @return
	 */
	DocInfo findById(Long docId);
	
	/**
	 * 查找用户全部文件夹
	 * @param docUserId
	 * @return
	 */
	List<DocInfo> findAllByUserId(Long docUserId);
	
	
}
