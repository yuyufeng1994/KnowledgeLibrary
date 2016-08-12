package com.lib.exception.user;

public class UserNoActiveException extends UserException {

	/**
	 * 未激活
	 */
	private static final long serialVersionUID = 1L;

	public UserNoActiveException(String message) {
		super(message);
	}

	public UserNoActiveException(String message, Throwable cause) {
		super(message, cause);
	}

}
