package com.lib.dao;

import org.apache.ibatis.annotations.Param;

import com.lib.entity.UserInfo;
/**
 * 用户dao操作
 * @author 
 *
 */
public interface UserInfoDao {
	/**
	 * 根据帐号查询一个用户的信息
	 * 
	 * @param id
	 * @return
	 */
	UserInfo queryById(@Param("userInfoId") long userInfoId);
	/**
	 * 根据Email查询一个用户的信息
	 * 
	 * @param id
	 * @return
	 */
	UserInfo queryByEmail(@Param("userEmail") String userEmail);
	
	/**
	 * 插入一条未激活用户记录
	 * @param user
	 * @return
	 */
	int insertUserNoStatus(UserInfo user);
	/**
	 * 将用户从未激活状态转换为更新状态
	 * @param user
	 * @return
	 */
	int updateUserNoStatus(UserInfo user);
}
