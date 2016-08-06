package com.lib.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

/**
 * 统计信息的dao
 * @author Yu Yufeng
 *
 */
public interface CountDao {

	Long getTodaysUpload(@Param("date1") Date date,@Param("date2") Date date2);

	Long getCountUserFiles(Long userId);

	Long getCountForkFiles(Long userId);

	Long getCountPublicFiles(Long userId);

}
