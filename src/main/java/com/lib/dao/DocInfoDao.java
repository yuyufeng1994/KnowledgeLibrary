package com.lib.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lib.entity.DocInfo;

/**
 * 我的文件夹dao
 * @author zcq
 *
 */
public interface DocInfoDao {
	
	/**
	 * 创建一个新的文件夹
	 * @param docName
	 * @param docUserId
	 * @return
	 */
	int insert(@Param("docName")String docName,@Param("docUserId")Long docUserId);
	
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
