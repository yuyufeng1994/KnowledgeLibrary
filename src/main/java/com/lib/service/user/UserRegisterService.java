package com.lib.service.user;

import com.lib.entity.UserInfo;

public interface UserRegisterService {
		/**
		 * 处理注册 
		 * @param email
		 */
		public void processregister(String userName,String userPassword,String email,String url);
		/**
		 * 处理激活 
		 * @param email
		 * @param validateCode
		 * @throws Exception
		 */
		public void processActivate(String email , String validateCode)throws Exception;
		
		/**
		 * 更新email账号
		 * @param user
		 */
		public void updateEmail(UserInfo user);
}
