package com.lib.dto;

import com.lib.entity.ForkInfo;

public class ForkFileInfoVo extends ForkInfo{
	
	private String fileUuId;
	private String fileName;
	private String userName;
	private String fileExt;
	private String docName;
	
	
	public String getFileUuId() {
		return fileUuId;
	}
	public void setFileUuId(String fileUuId) {
		this.fileUuId = fileUuId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	@Override
	public String toString() {
		return "ForkFileInfoVo [fileUuId=" + fileUuId + ", fileName=" + fileName + ", userName=" + userName
				+ ", fileExt=" + fileExt + ", docName=" + docName + "]";
	}
	
	
	
	
	
 	
	

}
