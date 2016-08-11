package com.lib.dto;

public class ClassesClickInfo {
	private Long classId;
	private String className;
	private Long classClickTimes;
	private Long uploadFileTimes;
	private Long forkFileTimes;
	private Long score;
	
	public ClassesClickInfo() {
		super();
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getClassClickTimes() {
		return classClickTimes;
	}

	public void setClassClickTimes(Long classClickTimes) {
		this.classClickTimes = classClickTimes;
	}

	public Long getUploadFileTimes() {
		return uploadFileTimes;
	}

	public void setUploadFileTimes(Long uploadFileTimes) {
		this.uploadFileTimes = uploadFileTimes;
	}

	public Long getForkFileTimes() {
		return forkFileTimes;
	}

	public void setForkFileTimes(Long forkFileTimes) {
		this.forkFileTimes = forkFileTimes;
	}
	
	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ClassesClickInfo [classId=" + classId + ", className=" + className + ", classClickTimes="
				+ classClickTimes + ", uploadFileTimes=" + uploadFileTimes + ", forkFileTimes=" + forkFileTimes
				+ ", score=" + score + "]";
	}
	
}
