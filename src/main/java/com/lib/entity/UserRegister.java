package com.lib.entity;

import java.beans.Transient;
import java.util.Calendar;
import java.util.Date;

public class UserRegister {
	private Long registerId;
	private Long userId;
	private String validateCode;
	private Date registerTime;
	
	public UserRegister() {
		super();
	}

	public UserRegister(Long registerId, Long userId, String validateCode, Date registerTime) {
		super();
		this.registerId = registerId;
		this.userId = userId;
		this.validateCode = validateCode;
		this.registerTime = registerTime;
	}

	public Long getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "UserRegister [registerId=" + registerId + ", userId=" + userId + ", validateCode=" + validateCode
				+ ", registerTime=" + registerTime + "]";
	}
	  @Transient  
	    public Date getLastActivateTime() {  
	        Calendar cl = Calendar.getInstance();  
	        cl.setTime(registerTime);  
	        cl.add(Calendar.DATE , 2);  
	          
	        return cl.getTime();  
	    }  
	
}
