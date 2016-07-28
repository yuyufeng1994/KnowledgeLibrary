package com.lib.entity;

import java.util.Date;
/**
 * 文件信息
 * @author Yu Yufeng
 *
 */
public class FileInfo {
	private Long fileId;

	private String fileName;

	private Long fileSize;

	private String fileExt;

	private Long fileUserId;

	private String fileUuid;

	private String filePath;

	private Boolean fileState;

	private Long fileClassId;

	private Date fileCraeteTime;

	private String fileBrief;

	public FileInfo() {

	}

	public Long getFileId() {
		return fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public String getFileExt() {
		return fileExt;
	}

	public Long getFileUserId() {
		return fileUserId;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public String getFilePath() {
		return filePath;
	}

	public Boolean getFileState() {
		return fileState;
	}

	public Long getFileClassId() {
		return fileClassId;
	}

	public Date getFileCraeteTime() {
		return fileCraeteTime;
	}

	public String getFileBrief() {
		return fileBrief;
	}

	@Override
	public String toString() {
		return "FileInfo [fileId=" + fileId + ", fileName=" + fileName + ", fileSize=" + fileSize + ", fileExt="
				+ fileExt + ", fileUserId=" + fileUserId + ", fileUuid=" + fileUuid + ", filePath=" + filePath
				+ ", fileState=" + fileState + ", fileClassId=" + fileClassId + ", fileCraeteTime=" + fileCraeteTime
				+ ", fileBrief=" + fileBrief + "]";
	}
	
}