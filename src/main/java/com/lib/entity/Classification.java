package com.lib.entity;

/**
 * 分类
 * 
 * @author Yu Yufeng
 *
 */
public class Classification {
	private Long classificationId;

	private String classificationName;

	private String classificationPicture;

	private Long parentId;

	private String classificationBrief;
	
	private String parentPath;

	public Classification() {
		super();
	}

	public Classification(Long classificationId, String classificationName, String classificationPicture, Long parentId,
			String classificationBrief) {
		this.classificationId = classificationId;
		this.classificationName = classificationName;
		this.classificationPicture = classificationPicture;
		this.parentId = parentId;
		this.classificationBrief = classificationBrief;
	}

	public Long getClassificationId() {
		return classificationId;
	}

	public String getClassificationName() {
		return classificationName;
	}

	public String getClassificationPicture() {
		return classificationPicture;
	}

	public Long getParentId() {
		return parentId;
	}

	public String getClassificationBrief() {
		return classificationBrief;
	}

	public void setClassificationId(Long classificationId) {
		this.classificationId = classificationId;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	public void setClassificationPicture(String classificationPicture) {
		this.classificationPicture = classificationPicture;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setClassificationBrief(String classificationBrief) {
		this.classificationBrief = classificationBrief;
	}
	
	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	@Override
	public String toString() {
		return "Classification [classificationId=" + classificationId + ", classificationName=" + classificationName
				+ ", classificationPicture=" + classificationPicture + ", parentId=" + parentId
				+ ", classificationBrief=" + classificationBrief + ", parentPath=" + parentPath + "]";
	}

}