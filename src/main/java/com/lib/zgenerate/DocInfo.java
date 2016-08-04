package com.lib.zgenerate;

import java.util.Date;

public class DocInfo {
    private Long docId;

    private Long docUserId;

    private String docName;

    private String docBiref;

    private Date docCreateTime;

    public DocInfo(Long docId, Long docUserId, String docName, String docBiref, Date docCreateTime) {
        this.docId = docId;
        this.docUserId = docUserId;
        this.docName = docName;
        this.docBiref = docBiref;
        this.docCreateTime = docCreateTime;
    }

    public Long getDocId() {
        return docId;
    }

    public Long getDocUserId() {
        return docUserId;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocBiref() {
        return docBiref;
    }

    public Date getDocCreateTime() {
        return docCreateTime;
    }
}