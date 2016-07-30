package com.lib.entity;

/**
 * 用户信息
 * 
 * @author Yu Yufeng
 *
 */
public class UserInfo {
	private Long userId;
	private String userName;
	private String userPassword;
	private Integer userType;
	private String userEmail;
	private String userPhoto;

	public UserInfo() {
		super();
	}

	public UserInfo(Long userId, String userName, String userPassword, Integer userType, String userEmail,
			String userPhoto) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = userType;
		this.userEmail = userEmail;
		this.userPhoto = userPhoto;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public Integer getUserType() {
		return userType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userType=" + userType + ", userEmail=" + userEmail + ", userPhoto=" + userPhoto + "]";
	}

}