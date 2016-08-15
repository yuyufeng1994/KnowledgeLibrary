package com.lib.service.admin;


import java.text.ParseException;
import java.util.List;

import com.lib.dto.ActiveUserInfo;
import com.lib.dto.ClassesClickInfo;
import com.lib.dto.ClickInfo;
import com.lib.dto.FileScoreInfo;
import com.lib.entity.FileInfo;


/**
 * 后台数据统计
 *
 */
public interface CountService {
	/**
	 * 获取热门文件
	 * @return
	 */
	public List<ClickInfo> getHotFiles();
	
	/**
	 * 获取热门分类
	 * @return
	 */
	public List<ClassesClickInfo> getHotClass(int before);
	
	/**
	 * 获取活跃用户
	 * @return
	 */
	public List<ActiveUserInfo> getActiveUsers(int before);
	
	/**
	 * 获取一个月每天的上传文件数
	 * @return
	 */
	public List<Long> getUploadTimesByTime();
	
	/**
	 * 获取一个月内的网站点击量
	 * @return
	 */
	public List<Long> getClickTimesByTime()throws ParseException ;
	
	/**
	 * 基于用户的推荐
	 * @return
	 */
	public List<FileInfo> getFileScoreList(Long userId,int recomNum);
	
	/**
	 * 基于物品的推荐
	 * @param userId
	 * @param recomNum
	 * @return
	 */
	public List<FileInfo> getFileScoreListByItemCF(Long userId,int recomNum);
	
	/**
	 * slopone推荐
	 * @param userId
	 * @param recomNum
	 * @return
	 */
	public List<FileInfo> getFileScoreListBySlopOne(Long userId,int recomNum);
	
}

