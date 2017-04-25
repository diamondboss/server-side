package com.diamondboss.util.pojo;

import java.util.Date;

public class UserInfoPojo {
    private Long id;

    private String name;

    private String phoneNumber;

    private String IDnumber;

    private String positiveURL;

    private String oppositeURL;

    private Boolean effective;

    private Date createTime;

    private Date updateTime;

    public UserInfoPojo(){}

    public UserInfoPojo(String name, String phoneNumber, String IDnumber, String positiveURL, String oppositeURL) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.IDnumber = IDnumber;
        this.positiveURL = positiveURL;
        this.oppositeURL = oppositeURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public String getPositiveURL() {
        return positiveURL;
    }

    public void setPositiveURL(String positiveURL) {
        this.positiveURL = positiveURL;
    }

    public String getOppositeURL() {
        return oppositeURL;
    }

    public void setOppositeURL(String oppositeURL) {
        this.oppositeURL = oppositeURL;
    }

    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
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