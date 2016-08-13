package com.lib.dto;

import java.util.Date;

/**
 * @author Loveling
 *
 */
public class ClickInfo {
	private Long clickId;
	private Long userId;
	private Long fileId;
	private String fileUUID;
	private Date clickTime;
	private Long fileClickTimes;
	private String fileName;
	public ClickInfo() {
		super();
	}



	public ClickInfo(Long clickId, Long userId, Long fileId, String fileUUID, Date clickTime, Long fileClickTimes,
			String fileName) {
		super();
		this.clickId = clickId;
		this.userId = userId;
		this.fileId = fileId;
		this.fileUUID = fileUUID;
		this.clickTime = clickTime;
		this.fileClickTimes = fileClickTimes;
		this.fileName = fileName;
	}



	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileClickTimes() {
		return fileClickTimes;
	}

	public void setFileClickTimes(Long fileClickTimes) {
		this.fileClickTimes = fileClickTimes;
	}

	public Long getClickId() {
		return clickId;
	}

	public void setClickId(Long clickId) {
		this.clickId = clickId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Date getClickTime() {
		return clickTime;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}

	public String getFileUUID() {
		return fileUUID;
	}

	public void setFileUUID(String fileUUID) {
		this.fileUUID = fileUUID;
	}

	@Override
	public String toString() {
		return "ClickInfo [clickId=" + clickId + ", userId=" + userId + ", fileId=" + fileId + ", fileUUID=" + fileUUID
				+ ", clickTime=" + clickTime + ", fileClickTimes=" + fileClickTimes + ", fileName=" + fileName + "]";
	}

}
