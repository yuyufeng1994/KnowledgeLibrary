package com.lib.exception.user;

public class UserNullAccountException extends UserException {

	/**
	 * 用户不存在异常
	 */
	private static final long serialVersionUID = 1L;

	public UserNullAccountException(String message) {
		super(message);
	}

	public UserNullAccountException(String message, Throwable cause) {
		super(message, cause);
	}

}
