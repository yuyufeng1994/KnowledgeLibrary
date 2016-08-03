package com.lib.zgenerate;

import java.util.Date;

public class UserRegister {
    private Long registerId;

    private Long userId;

    private String validateCode;

    private Date registerTime;

    public UserRegister(Long registerId, Long userId, String validateCode, Date registerTime) {
        this.registerId = registerId;
        this.userId = userId;
        this.validateCode = validateCode;
        this.registerTime = registerTime;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public Date getRegisterTime() {
        return registerTime;
    }
}