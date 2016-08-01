package com.lib.dto;

/**
 * 新建文档
 * 
 * @author Yu Yufeng
 *
 */
public class FileNew {
	private String name;
	private String content;//
	private String brief;
	private Long classId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "FileNew [name=" + name + ", content=" + content + ", brief=" + brief + ", classId=" + classId + "]";
	}

}
