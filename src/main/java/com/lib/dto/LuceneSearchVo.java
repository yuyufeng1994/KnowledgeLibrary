package com.lib.dto;

public class LuceneSearchVo extends FileInfoVO{
	
	
	private String fileText;
	private String fileKeyWords;
	public String getFileText() {
		return fileText;
	}
	public void setFileText(String fileText) {
		this.fileText = fileText;
	}
	public String getFileKeyWords() {
		return fileKeyWords;
	}
	public void setFileKeyWords(String fileKeyWords) {
		this.fileKeyWords = fileKeyWords;
	}
	@Override
	public String toString() {
		return super.toString()+"LuceneSearchVo [fileText=" + fileText + ", fileKeyWords=" + fileKeyWords + "]";
	}
	

}
