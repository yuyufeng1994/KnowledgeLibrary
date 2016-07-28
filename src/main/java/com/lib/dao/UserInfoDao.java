package com.lib.dao;

import org.apache.ibatis.annotations.Param;

import com.lib.entity.UserInfo;

public interface UserInfoDao {
	/**
	 * 根据帐号查询一个用户的信息
	 * 
	 * @param id
	 * @return
	 */
	UserInfo queryById(@Param("userInfoId") long userInfoId);

}
