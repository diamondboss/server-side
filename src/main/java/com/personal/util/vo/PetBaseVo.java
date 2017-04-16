package com.personal.util.vo;

import java.util.ArrayList;
import java.util.List;

import com.personal.util.pojo.PetBasePojo;

public class PetBaseVo {

	private Long id;

    private Integer petId;

    private String kind;

    private String birthday;

    private String hobby;

    private String varietyAdvantage;

    private String varietyDefect;

    private String photoUrl;

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

    private PetBaseVo pojoToVo(PetBasePojo pojo){
    	
    	if(pojo == null){
    		return null;
    	}
    	PetBaseVo vo = new PetBaseVo();
    	vo.setBirthday(pojo.getBirthday());
    	vo.setHobby(pojo.getHobby());
    	vo.setId(pojo.getId());
    	vo.setKind(pojo.getKind());
    	vo.setPetId(pojo.getPetId());
    	vo.setPhotoUrl(pojo.getPhotoUrl());
    	vo.setVarietyAdvantage(pojo.getVarietyAdvantage());
    	vo.setVarietyDefect(pojo.getVarietyDefect());
		return vo;
    	
    }
    
    public List<PetBaseVo> getPetBaseVo(List<PetBasePojo> pojoList){
    	
    	List<PetBaseVo> resultList = new ArrayList<PetBaseVo>(pojoList.size());
    	
    	for(PetBasePojo i : pojoList){
    		resultList.add(pojoToVo(i));
    	}
    	
    	return resultList;
    }
}
