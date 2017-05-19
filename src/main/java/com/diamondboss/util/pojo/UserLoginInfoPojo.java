package com.diamondboss.util.pojo;

import java.util.Date;

public class UserLoginInfoPojo {
    private Long id;

    private String phoneNumber;

    private Long userId;

    private Long petId;

    private Boolean effective;
    
    private Integer loginCount;
    
    private String userType;

	private Date createTime;

    private Date updateTime;

    public UserLoginInfoPojo(){}

    public UserLoginInfoPojo(String phoneNumber, Long userId, Long petId, Boolean effective,
    		Integer loginCount, String userType) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.petId = petId;
        this.effective = effective;
        this.loginCount = loginCount;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
    }
    
    public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}