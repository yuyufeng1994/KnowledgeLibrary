package com.lib.dto;

import java.util.List;

public class LuceneSearchVo extends FileInfoVO{
	
	
	private String fileText;
	private List<String> fileKeyWords;
	public String getFileText() {
		return fileText;
	}
	public void setFileText(String fileText) {
		this.fileText = fileText;
	}
	public List<String> getFileKeyWords() {
		return fileKeyWords;
	}
	public void setFileKeyWords(List<String> fileKeyWords) {
		this.fileKeyWords = fileKeyWords;
	}
	@Override
	public String toString() {
		return super.toString()+"LuceneSearchVo [fileText=" + fileText + ", fileKeyWords=" + fileKeyWords + "]";
	}
	

}
