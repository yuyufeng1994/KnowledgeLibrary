package com.lib.zgenerate;

public class Classification {
    private Long classificationId;

    private String classificationName;

    private String classificationPicture;

    private Long fatherId;

    private String classificationBrief;

    public Classification(Long classificationId, String classificationName, String classificationPicture, Long fatherId, String classificationBrief) {
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
}