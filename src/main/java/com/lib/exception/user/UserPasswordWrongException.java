package com.lib.exception.user;

public class UserPasswordWrongException extends UserException {

	/**
	 * 密码错误异常
	 */
	private static final long serialVersionUID = 1L;

	public UserPasswordWrongException(String message) {
		super(message);
	}

	public UserPasswordWrongException(String message, Throwable cause) {
		super(message, cause);
	}

}
