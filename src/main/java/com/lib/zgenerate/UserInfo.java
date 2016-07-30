package com.lib.zgenerate;

public class UserInfo {
    private Long userId;

    private String userName;

    private String userPassword;

    private Integer userType;

    private String userEmail;

    private String userPhoto;

    public UserInfo(Long userId, String userName, String userPassword, Integer userType, String userEmail, String userPhoto) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
        this.userEmail = userEmail;
        this.userPhoto = userPhoto;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Integer getUserType() {
        return userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhoto() {
        return userPhoto;
    }
}