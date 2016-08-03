package com.lib.service.user;

import com.lib.entity.UserInfo;
import com.lib.exception.user.UserException;
import com.lib.exception.user.UserNullAccountException;
import com.lib.exception.user.UserPasswordWrongException;

public interface UserService {
	/**
	 * 邮箱登录验证用户密码是否正确
	 * 
	 * @param user
	 * @return
	 */
	void checkUserByEmail(UserInfo user) throws UserException, UserPasswordWrongException, UserNullAccountException;

	/**
	 * 根据id得到用户
	 * @param l
	 * @return
	 */
	UserInfo getUserById(long l);

	/**
	 * 
	 * @param 根据id得到用户基本信息
	 * @return
	 */
	UserInfo getBasicUserInfo(Long userId);

	/**
	 * 
	 * @param 根据email得到用户基本信息
	 * @return
	 */
	UserInfo getBasicUserInfoByEmail(String userEmail);
	/**
	 * 判断email是否已经被注册
	 * @param email
	 * @return
	 */
	public boolean checkByEmail(String email) ;

}
