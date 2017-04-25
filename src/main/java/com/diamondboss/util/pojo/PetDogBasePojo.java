package com.diamondboss.util.pojo;

import java.util.Date;

public class PetDogBasePojo {
	
	/**
	 * 主键id
	 */
    private Long id;

    /**
     * 犬类品种
     */
    private String dogBreeds;

    /**
     * 中文学名
     */
    private String chineseName;

    /**
     * 别名
     */
    private String alias;

    /**
     * 祖先
     */
    private String ancestors;

    /**
     * 分布区域
     */
    private String distributionArea;

    /**
     * 原产地
     */
    private String countryOfOrigin;

    /**
     * 体型
     */
    private String shape;

    /**
     * 用途
     */
    private String purpose;

    /**
     * 分组
     */
    private String grouping;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 寿命
     */
    private String life;

    /**
     * 粘人程度
     */
    private Byte clingy;

    /**
     * 喜叫程度
     */
    private Byte call;

    /**
     * 友善程度
     */
    private Byte friendliness;

    /**
     * 掉毛程度
     */
    private Byte hairfalling;

    /**
     * 美容程度
     */
    private Byte cosmetology;

    /**
     * 体味程度
     */
    private Byte bodyOdour;

    /**
     * 口水程度
     */
    private Byte saliva;

    /**
     * 可训练
     */
    private Byte trainable;

    /**
     * 活跃程度
     */
    private Byte vitality;

    /**
     * 城市适应能力
     */
    private Byte adaptiveFaculty;

    /**
     * 耐寒程度
     */
    private Byte coldResistant;

    /**
     * 耐热程度
     */
    private Byte heatResisting;

    /**
     * 运动程度
     */
    private Byte kinetism;

    /**
     * 是否展示:1-展示,0-不展示
     */
    private Boolean effective;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 主键id
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 犬类品种
     * @return
     */
    public String getDogBreeds() {
        return dogBreeds;
    }

    /**
     * 犬类品种
     * @param dogBreeds
     */
    public void setDogBreeds(String dogBreeds) {
        this.dogBreeds = dogBreeds == null ? null : dogBreeds.trim();
    }

    /**
     * 中文学名
     * @return
     */
    public String getChineseName() {
        return chineseName;
    }

    /**
     * 中文学名
     * @param chineseName
     */
    public void setChineseName(String chineseName) {
        this.chineseName = chineseName == null ? null : chineseName.trim();
    }

    /**
     * 别名
     * @return
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 别名
     * @param alias
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     * 祖先
     * @return
     */
    public String getAncestors() {
        return ancestors;
    }

    /**
     * 祖先
     * @param ancestors
     */
    public void setAncestors(String ancestors) {
        this.ancestors = ancestors == null ? null : ancestors.trim();
    }

    /**
     * 分布区域
     * @return
     */
    public String getDistributionArea() {
        return distributionArea;
    }

    /**
     * 分部区域
     * @param distributionArea
     */
    public void setDistributionArea(String distributionArea) {
        this.distributionArea = distributionArea == null ? null : distributionArea.trim();
    }

    /**
     * 原产地
     * @return
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * 原产地
     * @param countryOfOrigin
     */
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin == null ? null : countryOfOrigin.trim();
    }

    /**
     * 体型
     * @return
     */
    public String getShape() {
        return shape;
    }

    /**
     * 体型
     * @param shape
     */
    public void setShape(String shape) {
        this.shape = shape == null ? null : shape.trim();
    }

    /**
     * 用途
     * @return
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * 用途
     * @param purpose
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    /**
     * 分组
     * @return
     */
    public String getGrouping() {
        return grouping;
    }

    /**
     * 分组
     * @param grouping
     */
    public void setGrouping(String grouping) {
        this.grouping = grouping == null ? null : grouping.trim();
    }

    /**
     * 身高
     * @return
     */
    public String getHeight() {
        return height;
    }

    /**
     * 身高
     * @param height
     */
    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    /**
     * 体重
     * @return
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 体重
     * @param weight
     */
    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    /**
     * 寿命
     * @return
     */
    public String getLife() {
        return life;
    }

    /**
     * 寿命
     * @param life
     */
    public void setLife(String life) {
        this.life = life == null ? null : life.trim();
    }

    /**
     * 粘人程度
     * @return
     */
    public Byte getClingy() {
        return clingy;
    }

    /**
     * 粘人程度
     * @param clingy
     */
    public void setClingy(Byte clingy) {
        this.clingy = clingy;
    }

    /**
     * 喜叫程度
     * @return
     */
    public Byte getCall() {
        return call;
    }

    /**
     * 喜叫程度
     * @param call
     */
    public void setCall(Byte call) {
        this.call = call;
    }

    /**
     * 友善程度
     * @return
     */
    public Byte getFriendliness() {
        return friendliness;
    }

    /**
     * 友善程度
     * @param friendliness
     */
    public void setFriendliness(Byte friendliness) {
        this.friendliness = friendliness;
    }

    /**
     * 掉毛程度
     * @return
     */
    public Byte getHairfalling() {
        return hairfalling;
    }

    /**
     * 掉毛程度
     * @param hairfalling
     */
    public void setHairfalling(Byte hairfalling) {
        this.hairfalling = hairfalling;
    }

    /**
     * 美容程度
     * @return
     */
    public Byte getCosmetology() {
        return cosmetology;
    }

    /**
     * 美容程度
     * @param cosmetology
     */
    public void setCosmetology(Byte cosmetology) {
        this.cosmetology = cosmetology;
    }

    /**
     * 体味程度
     * @return
     */
    public Byte getBodyOdour() {
        return bodyOdour;
    }

    /**
     * 体味程度
     * @param bodyOdour
     */
    public void setBodyOdour(Byte bodyOdour) {
        this.bodyOdour = bodyOdour;
    }

    /**
     * 口水程度
     * @return
     */
    public Byte getSaliva() {
        return saliva;
    }

    /**
     * 口水程度
     * @param saliva
     */
    public void setSaliva(Byte saliva) {
        this.saliva = saliva;
    }

    /**
     * 可训练
     * @return
     */
    public Byte getTrainable() {
        return trainable;
    }

    /**
     * 可训练
     * @param trainable
     */
    public void setTrainable(Byte trainable) {
        this.trainable = trainable;
    }

    /**
     * 活跃程度
     * @return
     */
    public Byte getVitality() {
        return vitality;
    }

    /**
     * 活跃程度
     * @param vitality
     */
    public void setVitality(Byte vitality) {
        this.vitality = vitality;
    }

    /**
     * 城市适应能力
     * @return
     */
    public Byte getAdaptiveFaculty() {
        return adaptiveFaculty;
    }

    /**
     * 城市适应能力
     * @param adaptiveFaculty
     */
    public void setAdaptiveFaculty(Byte adaptiveFaculty) {
        this.adaptiveFaculty = adaptiveFaculty;
    }

    /**
     * 耐寒程度
     * @return
     */
    public Byte getColdResistant() {
        return coldResistant;
    }

    /**
     * 耐寒程度
     * @param coldResistant
     */
    public void setColdResistant(Byte coldResistant) {
        this.coldResistant = coldResistant;
    }

    /**
     * 耐热程度
     * @return
     */
    public Byte getHeatResisting() {
        return heatResisting;
    }

    /**
     * 耐热程度
     * @param heatResisting
     */
    public void setHeatResisting(Byte heatResisting) {
        this.heatResisting = heatResisting;
    }

    /**
     * 运动程度
     * @return
     */
    public Byte getKinetism() {
        return kinetism;
    }

    /**
     * 运动程度
     * @param kinetism
     */
    public void setKinetism(Byte kinetism) {
        this.kinetism = kinetism;
    }

    /**
     * 是否展示:1-展示,0-不展示
     * @return
     */
    public Boolean getEffective() {
        return effective;
    }

    /**
     * 是否展示:1-展示,0-不展示
     * @param effective
     */
    public void setEffective(Boolean effective) {
        this.effective = effective;
    }

    /**
     * 创建时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}