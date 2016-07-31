package com.lib.service.user;

public interface UserRegisterService {
		/**
		 * 处理注册 
		 * @param email
		 */
		public void processregister(String userName,String userPassword,String email);
		/**
		 * 处理激活 
		 * @param email
		 * @param validateCode
		 * @throws Exception
		 */
		public void processActivate(String email , String validateCode)throws Exception;
}
