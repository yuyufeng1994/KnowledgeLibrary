package com.lib.dao;

import com.lib.entity.FileInfo;

/**
 * 文件dao操作
 * @author 
 *
 */
public interface FileInfoDao {
	/**
	 * 插入一条文件信息
	 * @param record
	 * @return
	 */
	int insert(FileInfo record);
	
	/**
	 * 根据主键查询一个文件信息
	 * @param fileId
	 * @return
	 */
	FileInfo selectByPrimaryKey(Long fileId);
	
	/**
	 * 更新一个文件信息
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(FileInfo record);
	
	/**
	 * 删除一个文件信息
	 * @param fileId
	 * @return
	 */
	int deleteByPrimaryKey(Long fileId);
	
}
