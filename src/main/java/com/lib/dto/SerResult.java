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

	public SerResult() {
		super();
	}

	public SerResult(String content, Long fileId) {
		super();
		this.content = content;
		this.fileId = fileId;
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

	@Override
	public String toString() {
		return "SerResult [content=" + content + ", fileId=" + fileId + "]";
	}

}
