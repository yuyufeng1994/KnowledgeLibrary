package com.lib.dto;

/**
 * 智能提取的结果
 * 
 * @author Yu Yufeng
 *
 */
public class SerResult {
	private String content; // 内容
	private Long fileId; // 文章id
	private String fileName;// 文档标题

	public SerResult(String content, Long fileId, String fileName) {
		super();
		this.content = content;
		this.fileId = fileId;
		this.fileName = fileName;
	}

	public SerResult() {
		super();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "SerResult [content=" + content + ", fileId=" + fileId + ", fileName=" + fileName + "]";
	}

}
