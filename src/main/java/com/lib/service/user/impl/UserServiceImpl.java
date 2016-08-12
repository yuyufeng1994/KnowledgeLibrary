package com.lib.service.user.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hankcs.hanlp.algoritm.VectorDistance;
import com.lib.dao.UserInfoDao;
import com.lib.dto.FileInfoVO;
import com.lib.entity.UserInfo;
import com.lib.exception.user.UserException;
import com.lib.exception.user.UserNoActiveException;
import com.lib.exception.user.UserNullAccountException;
import com.lib.exception.user.UserPasswordWrongException;
import com.lib.service.user.UserService;
import com.lib.utils.BeanUtil;
import com.lib.utils.PagedResult;
import com.lib.utils.StringValueUtil;

@Service
public class UserServiceImpl implements UserService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public void checkUserByEmail(UserInfo user) throws UserException {
		UserInfo record = userInfoDao.queryByEmail(user.getUserEmail());
		if (record == null) {
			throw new UserNullAccountException("用户不存在");
		} else if (!record.getUserPassword().equals(StringValueUtil.getMD5(user.getUserPassword()))) {
			LOG.warn(record.getUserId() + "登录密码错误");
			throw new UserPasswordWrongException("用户密码错误");
		}else if(record.getUserType() == 2){
			throw new UserNoActiveException("用户未激活");
		}
	}

	@Override
	public boolean checkByEmail(String userEmail) {
		UserInfo record = userInfoDao.queryByEmail(userEmail);
		if (record == null) {
			return true;
		}
		return false;
	}

	@Override
	public UserInfo getUserById(long l) {

		return userInfoDao.queryById(l);
	}

	@Override
	public UserInfo getBasicUserInfo(Long userId) {
		UserInfo user = new UserInfo();
		UserInfo record = userInfoDao.queryById(userId);
		user.setUserName(record.getUserName());
		user.setUserEmail(record.getUserEmail());
		user.setUserPhoto(record.getUserPhoto());
		user.setUserType(record.getUserType());
		return user;
	}

	@Override
	public UserInfo getBasicUserInfoByEmail(String userEmail) {
		UserInfo user = new UserInfo();
		UserInfo record = userInfoDao.queryByEmail(userEmail);
		user.setUserName(record.getUserName());
		user.setUserEmail(record.getUserEmail());
		user.setUserPhoto(record.getUserPhoto());
		user.setUserType(record.getUserType());
		user.setUserId(record.getUserId());
		return user;
	}

	/**
	 * 获取所有user信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserAllInfo(Long userId) {
		UserInfo user = new UserInfo();
		UserInfo record = userInfoDao.queryById(userId);
		user.setUserName(record.getUserName());
		user.setUserEmail(record.getUserEmail());
		user.setUserPhoto(record.getUserPhoto());
		user.setUserType(record.getUserType());
		user.setUserPassword(record.getUserPassword());
		user.setUserId(record.getUserId());
		return user;
	}

	@Override
	public void updateUser(UserInfo user) {
		userInfoDao.updateUserInfo(user);
	}

	@Override
	public void updateUserPwd(UserInfo user) {
		userInfoDao.updateUserPwd(user);
	}

	@Override
	public PagedResult<UserInfo> queryByPage(String userName, Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize); // startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		return BeanUtil.toPagedResult(userInfoDao.selectAllUserByUserName(userName));
	}

	@Override
	public boolean deleteUserByUserId(Long userId) {
		return userInfoDao.deleteUserById(userId);
	}

	@Override
	public void updateUserType(UserInfo user) {
		userInfoDao.updateUserEmail(user);
	}

}
