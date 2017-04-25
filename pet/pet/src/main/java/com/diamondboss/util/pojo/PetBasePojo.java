package com.diamondboss.util.pojo;

import java.util.Date;

public class PetBasePojo {
    private Long id;

    private Integer petId;

    private String kind;

    private String birthday;

    private String hobby;

    private String varietyAdvantage;

    private String varietyDefect;

    private String photoUrl;

    private Boolean effective;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getVarietyAdvantage() {
        return varietyAdvantage;
    }

    public void setVarietyAdvantage(String varietyAdvantage) {
        this.varietyAdvantage = varietyAdvantage == null ? null : varietyAdvantage.trim();
    }

    public String getVarietyDefect() {
        return varietyDefect;
    }

    public void setVarietyDefect(String varietyDefect) {
        this.varietyDefect = varietyDefect == null ? null : varietyDefect.trim();
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
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