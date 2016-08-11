
package com.lib.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lib.dto.FileInfoVO;
import com.lib.entity.UserInfo;

/**
 * 用户dao操作
 * 
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
	 * 
	 * @param user
	 * @return
	 */
	int insertUserNoStatus(UserInfo user);

	/**
	 * 根据id修改未激活用户信息
	 * 
	 * @param user
	 * @return
	 */
	int updateUserNoStatus(UserInfo user);

	/**
	 * 根据id修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	int updateUserInfo(UserInfo user);

	/**
	 * 更换Email
	 * 
	 * @param user
	 * @return
	 */
	int updateUserEmail(UserInfo user);

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	int updateUserPwd(UserInfo user);

	/**
	 * 根据搜索关键字查询
	 * 
	 * @param userName
	 * @return
	 */
	List<UserInfo> selectAllUserByUserName(@Param("searchName") String searchName);

	/**
	 * 根据id删除用户
	 * 
	 * @param userId
	 * @return
	 */
	boolean deleteUserById(@Param("userId") Long userId);

}
