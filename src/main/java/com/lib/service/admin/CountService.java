package com.lib.service.admin;


import java.util.List;

import com.lib.dto.ClassesClickInfo;
import com.lib.dto.ClickInfo;


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
}

