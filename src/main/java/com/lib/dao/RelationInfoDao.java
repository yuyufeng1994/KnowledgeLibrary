package com.lib.dao;

import java.util.List;

import com.lib.entity.RelationInfo;

public interface RelationInfoDao {
	/**
	 * 插入一条关联
	 * 
	 * @param r
	 * @return
	 */
	int insert(RelationInfo r);

	/**
	 * 批量插入一条关联
	 * 
	 * @param r
	 * @return
	 */
	int insertList(List<RelationInfo> rs);

	/**
	 * 查询一个文件的关联文档
	 * 
	 * @param mainFileId
	 * @return
	 */
	List<RelationInfo> selectList(Long mainFileId);

	/**
	 * 删除一条关联记录
	 * 
	 * @param r
	 * @return
	 */
	int delete(RelationInfo r);

}
