package com.lib.zgenerate;

import java.util.Date;

public class RelationInfo extends RelationInfoKey {
    private Date relationCreateTime;

    public RelationInfo(Long mainFileId, Long relationFileId, Date relationCreateTime) {
        super(mainFileId, relationFileId);
        this.relationCreateTime = relationCreateTime;
    }

    public Date getRelationCreateTime() {
        return relationCreateTime;
    }
}