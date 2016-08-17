package com.lib.entity;

import java.util.Date;

public class MessageInfo {
	private Long msgId;
	private String msgTitle;
	private String msgContent;
	private Long userId;
	private boolean isRead;
	private Date msgTime;
	public MessageInfo(Long msgId, String msgTitle, String msgContent, Long userId, boolean isRead, Date msgTime) {
		super();
		this.msgId = msgId;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.userId = userId;
		this.isRead = isRead;
		this.msgTime = msgTime;
	}
	public MessageInfo() {
		super();
	}
	public Long getMsgId() {
		return msgId;
	}
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public boolean getIsRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public Date getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}
	@Override
	public String toString() {
		return "MessageInfo [msgId=" + msgId + ", msgTitle=" + msgTitle + ", msgContent=" + msgContent + ", userId="
				+ userId + ", isRead=" + isRead + ", msgTime=" + msgTime + "]";
	}
	
	
}
