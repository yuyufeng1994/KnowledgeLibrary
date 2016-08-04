package com.lib.zgenerate;

public class Classification {
    private Long classificationId;

    private String classificationName;

    private String classificationPicture;

    private Long parentId;

    private String parentPath;

    private String classificationBrief;

    public Classification(Long classificationId, String classificationName, String classificationPicture, Long parentId, String parentPath, String classificationBrief) {
        this.classificationId = classificationId;
        this.classificationName = classificationName;
        this.classificationPicture = classificationPicture;
        this.parentId = parentId;
        this.parentPath = parentPath;
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

    public String getParentPath() {
        return parentPath;
    }

    public String getClassificationBrief() {
        return classificationBrief;
    }
}