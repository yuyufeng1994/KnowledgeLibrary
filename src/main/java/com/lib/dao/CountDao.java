package com.lib.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lib.dto.ClassesClickInfo;
import com.lib.dto.ClickInfo;

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
}
