package com.lib.service.user;

import java.io.File;
import java.util.List;

import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;

public interface FileInfoService {
	/**
	 * 插入一个新的文件信息到数据库
	 * 
	 * @param fileInfo
	 * @return
	 */
	int insertFile(FileInfo fileInfo);

	/**
	 * 解压用户上传的文件，并保存到磁盘和数据库
	 * 
	 * @param string
	 * @param user
	 * @return 解压之后的文件uuid
	 */
	List<String> compressFile(String string, UserInfo user) throws Exception;
}
