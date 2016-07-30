package com.lib.service.user;

import com.lib.entity.FileInfo;

public interface FileInfoService {
	/**
	 * 插入一个新的文件信息到数据库
	 * @param fileInfo
	 * @return
	 */
	int insertFile(FileInfo fileInfo);
}
