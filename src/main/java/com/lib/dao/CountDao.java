package com.lib.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lib.dto.ActiveUserInfo;
import com.lib.dto.ClassesClickInfo;
import com.lib.dto.ClickInfo;
import com.lib.dto.FileScoreInfo;

/**
 * 统计信息的dao
 * 
 * @author Yu Yufeng
 *
 */
public interface CountDao {

	/**
	 * 查询今天的浏览量
	 * 
	 * @param date
	 * @param date2
	 * @return
	 */
	Long getTodaysUpload(@Param("date1") Date date, @Param("date2") Date date2);

	/**
	 * 我的文件数量
	 * 
	 * @param userId
	 * @return
	 */
	Long getCountUserFiles(Long userId);

	/**
	 * 我收藏的文件数量
	 * 
	 * @param userId
	 * @return
	 */
	Long getCountForkFiles(Long userId);

	/**
	 * 可用文档数量
	 * 
	 * @param userId
	 * @return
	 */
	Long getCountPublicFiles(Long userId);
	/**
	 * 获取热门文档
	 * @return
	 */
	List<ClickInfo> getHotFiles();
	
	/**
	 * 获取热门分类
	 * @return
	 */
	List<ClassesClickInfo> getHotClasses(@Param("time") Date time);
	
	/**
	 * 获取热门分类中的被收藏的文档数
	 * @return
	 */
	Long getForkFileTimes(@Param("time") Date time,@Param("classId") Long classId);
	
	/**
	 * 获取热门分类中的文件的点击量
	 * @return
	 */
	Long getClickTimes(@Param("time") Date time,@Param("classId") Long classId);
	
	/**
	 * 获取活跃用户
	 * @param time
	 * @return
	 */
	List<ActiveUserInfo> getActiveUsers(@Param("time") Date time);
	
	/**
	 * 获取活跃用户的收藏量
	 * @param time
	 * @param userId
	 * @return
	 */
	Long getUserForkFileTimes(@Param("time") Date time,@Param("userId") Long userId);
	/**
	 * 获取用户点击量
	 * @param time
	 * @param userId
	 * @return
	 */
	Long getUserClickTimes(@Param("time") Date time,@Param("userId") Long userId);
	
	/**
	 * 根据时间段取得上传文件数
	 * @param time
	 * @return
	 */
	Long getUploadTimesByTime(@Param("time1") Date time1,@Param("time2") Date time2);
	
	/**
	 * 根据时间段获得网站点击率
	 * @param time1
	 * @param time2
	 * @return
	 */
	Long getClickTimesByTime(@Param("time1") Date time1,@Param("time2") Date time2);
	/**
	 * 获取用户和文件的评分信息
	 * @return
	 */
	List<FileScoreInfo> getFileScoreList();
	
	/**
	 * 插入用户文件评分信息
	 * @param fileScore
	 * @return
	 */
	int insertFileScore(FileScoreInfo fileScore);
	
	/**
	 *  更新用户文件评分信息
	 * @param fileScore
	 * @return
	 */
	int updateFileScore(FileScoreInfo fileScore);
	
	/**
	 * 根据用户id和文件id获得评分信息表
	 * @return
	 */
	FileScoreInfo queryFileScoreByUserAndFile(@Param("userId") Long userId,@Param("fileId") Long fileId);
}
