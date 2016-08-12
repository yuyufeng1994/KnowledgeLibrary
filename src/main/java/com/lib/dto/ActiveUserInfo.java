package com.lib.dto;

public class ActiveUserInfo {
	private Long userId;
	private String userName;
	private Long uploadFileTimes;
	private Long forkFileTimes;
	private Long clickFileTimes;
	private Long active;
	public ActiveUserInfo(Long userId, String userName, Long uploadFileTimes, Long forkFileTimes, Long clickFileTimes,
			Long active) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.uploadFileTimes = uploadFileTimes;
		this.forkFileTimes = forkFileTimes;
		this.clickFileTimes = clickFileTimes;
		this.active = active;
	}
	public ActiveUserInfo() {
		super();
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUploadFileTimes() {
		return uploadFileTimes;
	}
	public void setUploadFileTimes(Long uploadFileTimes) {
		this.uploadFileTimes = uploadFileTimes;
	}
	public Long getForkFileTimes() {
		return forkFileTimes;
	}
	public void setForkFileTimes(Long forkFileTimes) {
		this.forkFileTimes = forkFileTimes;
	}
	public Long getClickFileTimes() {
		return clickFileTimes;
	}
	public void setClickFileTimes(Long clickFileTimes) {
		this.clickFileTimes = clickFileTimes;
	}
	public Long getActive() {
		return active;
	}
	public void setActive(Long active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "ActiveUserInfo [userId=" + userId + ", userName=" + userName + ", uploadFileTimes=" + uploadFileTimes
				+ ", forkFileTimes=" + forkFileTimes + ", clickFileTimes=" + clickFileTimes + ", active=" + active
				+ "]";
	}
}
