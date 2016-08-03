package com.lib.entity;

import java.util.Date;

public class ForkInfo {
	private Long forkId;
	private Long docId;
	private Long fileId;
	private Date forkCreateTime;
	private String forkNote;
	public Long getForkId() {
		return forkId;
	}
	public void setForkId(Long forkId) {
		this.forkId = forkId;
	}
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public Date getForkCreateTime() {
		return forkCreateTime;
	}
	public void setForkCreateTime(Date forkCreateTime) {
		this.forkCreateTime = forkCreateTime;
	}
	public String getForkNote() {
		return forkNote;
	}
	public void setForkNote(String forkNote) {
		this.forkNote = forkNote;
	}
	@Override
	public String toString() {
		return "ForkInfo [forkId=" + forkId + ", docId=" + docId + ", fileId=" + fileId + ", forkCreateTime="
				+ forkCreateTime + ", forkNote=" + forkNote + "]";
	}
	
}
