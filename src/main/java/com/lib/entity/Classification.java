package com.lib.entity;
/**
 * 文件分类
 * @author Yu Yufeng
 *
 */
public class Classification {
	private Long classificationId;

	private String classificationName;

	private String classificationPicture;

	private Long fatherId;

	private String classificationBrief;

	public Classification() {
		super();
	}

	public Classification(Long classificationId, String classificationName, String classificationPicture, Long fatherId,
			String classificationBrief) {
		this.classificationId = classificationId;
		this.classificationName = classificationName;
		this.classificationPicture = classificationPicture;
		this.fatherId = fatherId;
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

	public Long getFatherId() {
		return fatherId;
	}

	public String getClassificationBrief() {
		return classificationBrief;
	}

	@Override
	public String toString() {
		return "Classification [classificationId=" + classificationId + ", classificationName=" + classificationName
				+ ", classificationPicture=" + classificationPicture + ", fatherId=" + fatherId
				+ ", classificationBrief=" + classificationBrief + "]";
	}

}