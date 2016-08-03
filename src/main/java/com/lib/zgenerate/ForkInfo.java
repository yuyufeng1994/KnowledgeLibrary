package com.lib.zgenerate;

import java.util.Date;

public class ForkInfo {
    private Long forkId;

    private Long docId;

    private Long fileId;

    private Date forkCreateTime;

    private String forkNote;

    public ForkInfo(Long forkId, Long docId, Long fileId, Date forkCreateTime, String forkNote) {
        this.forkId = forkId;
        this.docId = docId;
        this.fileId = fileId;
        this.forkCreateTime = forkCreateTime;
        this.forkNote = forkNote;
    }

    public Long getForkId() {
        return forkId;
    }

    public Long getDocId() {
        return docId;
    }

    public Long getFileId() {
        return fileId;
    }

    public Date getForkCreateTime() {
        return forkCreateTime;
    }

    public String getForkNote() {
        return forkNote;
    }
}