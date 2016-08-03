package com.lib.entity;

import java.util.Date;

public class DocInfo {
	private Long docId;
	private Long docUserId;
	private String docName;
	private String docBrief;
	private Date docCreateTime;
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public Long getDocUserId() {
		return docUserId;
	}
	public void setDocUserId(Long docUserId) {
		this.docUserId = docUserId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocBrief() {
		return docBrief;
	}
	public void setDocBrief(String docBrief) {
		this.docBrief = docBrief;
	}
	public Date getDocCreateTime() {
		return docCreateTime;
	}
	public void setDocCreateTime(Date docCreateTime) {
		this.docCreateTime = docCreateTime;
	}
	@Override
	public String toString() {
		return "DocInfo [docId=" + docId + ", docUserId=" + docUserId + ", docName=" + docName + ", docBrief="
				+ docBrief + ", docCreateTime=" + docCreateTime + "]";
	}
	
}
