package com.lib.service.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lib.entity.Classification;

public interface ClassificationService {
	
	/**
	 * 添加分类
	 * @param classificationName
	 * @param parentId
	 * @return
	 */
	void insert(String classificationName,Long parentId);
	
	/**
	 * 删除分类
	 * @param classificationId
	 * @return
	 */
	void delete(Long classificationId);
	
	/**
	 * 修改分类
	 * @param classificationId
	 * @return
	 */
	void modify(String classificationName,Long classificationId);
	
	/**
	 * 查找分类
	 * @param classificationId
	 * @return
	 */
	Classification findById(Long classificationId);
	
	/**
	 * 查看父类路径
	 * @param classificationId
	 * @return
	 */
	String findFatherPathById(Long classificationId);
	
	/**
	 * 查找一层的孩子节点
	 * @param classificationId
	 * @return
	 */
	List<Classification> findOneChildById(Long classificationId);

	/**
	 * 查找所有的孩子节点
	 * @param classificationId
	 * @return
	 */
	Map<String,List<Classification>> findAllChildById(String classificationId);
	/**
	 * 查找所有的孩子节点
	 * @param classificationId
	 * @return
	 */
	List<Classification> findAllChildById1(String classificationId);
	/**
	 * 查找所有的兄弟节点
	 * @param classificationId
	 * @return
	 */
	List<Classification> findAllBotherById(Long classificationId);
	
	
	/**
	 * 查找所有的父节点
	 * @param classificationId
	 * @return
	 */
	List<Classification> findAllfatherById(Long classificationId);
	
}
