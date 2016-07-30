package com.lib.zgenerate;

import java.util.Date;

public class FileInfo {
    private Long fileId;

    private String fileName;

    private Long fileSize;

    private String fileExt;

    private Long fileUserId;

    private String fileUuid;

    private String filePath;

    private Integer fileState;

    private Long fileClassId;

    private Date fileCreateTime;

    private String fileBrief;

    public FileInfo(Long fileId, String fileName, Long fileSize, String fileExt, Long fileUserId, String fileUuid, String filePath, Integer fileState, Long fileClassId, Date fileCreateTime, String fileBrief) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileExt = fileExt;
        this.fileUserId = fileUserId;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileState = fileState;
        this.fileClassId = fileClassId;
        this.fileCreateTime = fileCreateTime;
        this.fileBrief = fileBrief;
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

    public Integer getFileState() {
        return fileState;
    }

    public Long getFileClassId() {
        return fileClassId;
    }

    public Date getFileCreateTime() {
        return fileCreateTime;
    }

    public String getFileBrief() {
        return fileBrief;
    }
}