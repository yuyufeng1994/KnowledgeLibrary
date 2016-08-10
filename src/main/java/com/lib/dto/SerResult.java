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
	private String fileUuid;

	public SerResult(String content, Long fileId, String fileName, String fileUuid) {
		super();
		this.content = content;
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileUuid = fileUuid;
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

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
	
	

}
