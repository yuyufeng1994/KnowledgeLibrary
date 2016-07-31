package com.lib.service.user;

import com.github.pagehelper.PageInfo;
import com.lib.dto.FileInfoVO;

/**
 * 用户管理
 * 
 * @author Yu Yufeng
 *
 */
public interface FileManageService {
	/**
	 * 得到某一用户上传的文件分页
	 * 
	 * @param userId
	 * @return
	 */
	PageInfo<FileInfoVO> getFileInfoPageByUserId(int pageNo, Long userId, String order);

}
