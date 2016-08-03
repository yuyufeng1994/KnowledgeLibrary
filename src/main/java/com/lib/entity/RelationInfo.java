package com.lib.entity;

import java.util.Date;

/**
 * 关联信息
 * 
 * @author Yu Yufeng
 *
 */
public class RelationInfo {
	private Date relationCreateTime;
	private Long mainFileId;
	private Long relationFileId;
	private FileInfo relationFile;

	public Date getRelationCreateTime() {
		return relationCreateTime;
	}

	public void setRelationCreateTime(Date relationCreateTime) {
		this.relationCreateTime = relationCreateTime;
	}

	public Long getMainFileId() {
		return mainFileId;
	}

	public void setMainFileId(Long mainFileId) {
		this.mainFileId = mainFileId;
	}

	public Long getRelationFileId() {
		return relationFileId;
	}

	public void setRelationFileId(Long relationFileId) {
		this.relationFileId = relationFileId;
	}

	public FileInfo getRelationFile() {
		return relationFile;
	}

	public void setRelationFile(FileInfo relationFile) {
		this.relationFile = relationFile;
	}

	@Override
	public String toString() {
		return "RelationInfo [relationCreateTime=" + relationCreateTime + ", mainFileId=" + mainFileId
				+ ", relationFileId=" + relationFileId + ", relationFile=" + relationFile + "]";
	}

}
