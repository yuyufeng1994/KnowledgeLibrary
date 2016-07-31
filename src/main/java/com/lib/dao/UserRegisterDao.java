package com.lib.dao;

import com.lib.entity.UserRegister;

public interface UserRegisterDao {
	/**
	 * 保存注册信息
	 * @param userRegister
	 * @return
	 */
	int insertNoStatus(UserRegister userRegister);
	/**
	 *   根据用户id查找
	 * @param userId
	 * @return
	 */
	UserRegister queryById(Long userId);
	/**
	 * 
	 * @param userRegister
	 * @return
	 */
	int updateNoStatus(UserRegister userRegister);
}
