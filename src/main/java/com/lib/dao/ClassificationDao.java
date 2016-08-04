package com.lib.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lib.entity.Classification;

/**
 * 分类dao操作
 * 
 * @author zcq
 *
 */
public interface ClassificationDao {

	/**
	 * 添加分类
	 * 
	 * @param classificationName
	 * @param parentId
	 * @return
	 */
	void insert(@Param("classificationName") String classificationName, @Param("parentId") Long parentId,
			@Param("parentPath") String parentPath);

	/**
	 * 删除分类
	 * 
	 * @param classificationId
	 * @return
	 */
	void delete(Long classificationId);

	/**
	 * 修改分类
	 * 
	 * @param classificationId
	 * @return
	 */
	void modify(@Param("classificationName") String classificationName,
			@Param("classificationId") Long classificationId);

	/**
	 * 查找分类
	 * 
	 * @param classificationId
	 * @return
	 */
	Classification findById(Long classificationId);

	/**
	 * 查看父类路径
	 * 
	 * @param classificationId
	 * @return
	 */
	String findFatherPathById(Long classificationId);

	/**
	 * 查找一层的孩子节点
	 * 
	 * @param classificationId
	 * @return
	 */
	List<Classification> findOneChildById(Long classificationId);

	/**
	 * 查找全部的孩子节点
	 * 
	 */
	List<Classification> findAllChildById(String classificationId);

	/**
	 * 查找所有的兄弟节点
	 * 
	 * @param classificationId
	 * @return
	 */
	List<Classification> findAllBotherById(Long classificationId);

	/**
	 * 查找所有的父节点
	 * 
	 * @param classificationId
	 * @return
	 */
	List<Classification> findAllfatherById(Long classificationId);

}
