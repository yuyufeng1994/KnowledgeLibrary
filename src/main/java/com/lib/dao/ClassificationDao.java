package com.lib.dao;
/**
 * 分类dao操作
 * @author 
 *
 */
public interface ClassificationDao {
	
	/**
	 * 添加分类
	 * @param parent_id
	 * @return
	 */
	int insert(Long parent_id);
	
	/**
	 * 删除分类
	 * @param classification_id
	 * @return
	 */
	int delete(Long classification_id);
	
	/**
	 * 修改分类
	 * @param classification_id
	 * @return
	 */
	int modify(Long classification_id);
	
	
}
