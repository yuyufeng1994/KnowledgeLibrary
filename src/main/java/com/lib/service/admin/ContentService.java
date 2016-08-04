package com.lib.service.admin;

/**
 * 容器的一些配置加载
 * 
 * @author Yu Yufeng
 *
 */
public interface ContentService {
	/**
	 * Spring装载此类时
	 */
	void init();

	/**
	 * Spring销毁此类时
	 */
	void dostory();
}
