package com.lib.dto;

import java.util.Date;

public class FileScoreInfo {
	private Long userId;
	private Long fileId;
	private Long score;
	private Date time;
	public FileScoreInfo(Long userId, Long fileId, Long score, Date time) {
		super();
		this.userId = userId;
		this.fileId = fileId;
		this.score = score;
		this.time = time;
	}
	public FileScoreInfo() {
		super();
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
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "FileScoreInfo [userId=" + userId + ", fileId=" + fileId + ", score=" + score + ", time=" + time + "]";
	}
	
}
