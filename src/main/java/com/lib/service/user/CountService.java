package com.lib.service.user;

/**
 * 统计数据Service
 * 
 * @author Yu Yufeng
 *
 */
public interface CountService {

	public Long getTodaysUpload();

	public Long getCountUserFiles(Long userId);

	public Long getCountPublicFiles(Long userId);

	public Long getCountForkFiles(Long userId);

}
