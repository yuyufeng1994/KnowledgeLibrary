package com.lib.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lib.dto.FileInfoVO;
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
	 * 
	 * @param uuid
	 * @return
	 */
	FileInfoVO getFileInfoByUuid(String fileUuid);
	
	/**
	 * 通过fileId得到一个文件
	 * 
	 * @param uuid
	 * @return
	 */
	FileInfo getFileInfoByFileId(Long fileId);

	/**
	 * 设置文件的状态
	 * 
	 * @param uuid
	 * @param i
	 */
	void setFileStateByUuid(@Param("fileUuid") String uuid, @Param("fileState") Integer i);

	/**
	 * 修改文件后缀名
	 * 
	 * @param fileId
	 * @param fileExe
	 */
	void modifyFileExeById(@Param("fileId") Long fileId, @Param("fileExt") String fileExt);

	/**
	 * 得到为处理过的文件
	 * 
	 * @param i
	 * @return
	 */
	List<FileInfoVO> getFilesByUserId(Long userId);

	/**
	 * 修改文件的基本信息
	 * @param fileInfo
	 * @return
	 */
	int updateByUuid(FileInfo fileInfo);

}
