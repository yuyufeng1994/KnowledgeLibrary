package com.lib.zgenerate;

public class RelationInfoKey {
    private Long mainFileId;

    private Long relationFileId;

    public RelationInfoKey(Long mainFileId, Long relationFileId) {
        this.mainFileId = mainFileId;
        this.relationFileId = relationFileId;
    }

    public Long getMainFileId() {
        return mainFileId;
    }

    public Long getRelationFileId() {
        return relationFileId;
    }
}