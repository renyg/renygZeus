package com.shining3d.zeus.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private String creator;

    private Date gmtModified;

    private String modifier;

    private String isDeleted;

    private String name;

    private Long dataId;

    private String userId;

    private BigDecimal dataSharePrice;

    private String mainPicId;

    private String picFileId;

    private Long categoryId;

    private String description;

    private String productType;

    private Date publishTime;

    private String state;

    private String onlineType;

    private Date onlineTime;

    private String offlineType;

    private Date offlineTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public BigDecimal getDataSharePrice() {
        return dataSharePrice;
    }

    public void setDataSharePrice(BigDecimal dataSharePrice) {
        this.dataSharePrice = dataSharePrice;
    }

    public String getMainPicId() {
        return mainPicId;
    }

    public void setMainPicId(String mainPicId) {
        this.mainPicId = mainPicId == null ? null : mainPicId.trim();
    }

    public String getPicFileId() {
        return picFileId;
    }

    public void setPicFileId(String picFileId) {
        this.picFileId = picFileId == null ? null : picFileId.trim();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getOnlineType() {
        return onlineType;
    }

    public void setOnlineType(String onlineType) {
        this.onlineType = onlineType == null ? null : onlineType.trim();
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getOfflineType() {
        return offlineType;
    }

    public void setOfflineType(String offlineType) {
        this.offlineType = offlineType == null ? null : offlineType.trim();
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}