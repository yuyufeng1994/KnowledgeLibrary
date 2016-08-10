package com.lib.service.admin;


import java.util.List;

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
}

