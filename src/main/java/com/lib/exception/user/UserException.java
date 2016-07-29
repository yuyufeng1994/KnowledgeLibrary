package com.lib.exception.user;

/**
 * 用户信息相关异常
 * 
 * @author Yu Yufeng
 *
 */
public class UserException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}
}
