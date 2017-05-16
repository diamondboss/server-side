package com.diamondboss.util.pojo;

import java.util.Date;

public class ParterInfoPojo {
    private Long id;

    private String name;

    private String phonenumber;

    private String wxchat;
    
    private String communityId;

	private String address;

    private String idnumber;

    private Byte bodysize;

    private Byte raisecycle;

    private String raisenumber;

    private Integer effective;

    private Date createTime;

    private Date updateTime;

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
        this.name = name == null ? null : name.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getWxchat() {
        return wxchat;
    }

    public void setWxchat(String wxchat) {
        this.wxchat = wxchat == null ? null : wxchat.trim();
    }
    
    public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber == null ? null : idnumber.trim();
    }

    public Byte getBodysize() {
        return bodysize;
    }

    public void setBodysize(Byte bodysize) {
        this.bodysize = bodysize;
    }

    public Byte getRaisecycle() {
        return raisecycle;
    }

    public void setRaisecycle(Byte raisecycle) {
        this.raisecycle = raisecycle;
    }

    public String getRaisenumber() {
        return raisenumber;
    }

    public void setRaisenumber(String raisenumber) {
        this.raisenumber = raisenumber == null ? null : raisenumber.trim();
    }

    public Integer getEffective() {
        return effective;
    }

    public void setEffective(Integer effective) {
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