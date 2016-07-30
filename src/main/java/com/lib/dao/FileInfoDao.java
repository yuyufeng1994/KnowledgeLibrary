package com.lib.dao;

import java.util.List;

import com.lib.entity.FileInfo;

/**
 * 文件dao操作
 * 
 * @author
 *
 */
public interface FileInfoDao {
	/**
	 * 插入一条文件信息
	 * 
	 * @param record
	 * @return
	 */
	int insert(FileInfo record);

	/**
	 * 根据主键查询一个文件信息
	 * 
	 * @param fileId
	 * @return
	 */
	FileInfo selectByPrimaryKey(Long fileId);

	/**
	 * 更新一个文件信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(FileInfo record);

	/**
	 * 删除一个文件信息
	 * 
	 * @param fileId
	 * @return
	 */
	int deleteByPrimaryKey(Long fileId);

	/**
	 * 得到为处理过的文件
	 * 
	 * @param i
	 * @return
	 */
	List<FileInfo> getFilesByState(Integer fileState);

	/**
	 * 通过uuid得到一个文件
	 * @param uuid
	 * @return
	 */
	FileInfo getFileInfoByUuid(String fileUuid);

}
