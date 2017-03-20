package com.shining3d.zeus.client.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fe on 2016/12/21.
 */
public class DataProductDto implements Serializable{
    private static final long serialVersionUID = -178059498516074790L;

    /**
     * productId
     */
    private Long productId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 数据id
     */
    private Long dataId;
    /**
     * 数据商品时 有分润价格
     */
    private BigDecimal dataSharePrice;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 发用户id
     */
    private String userId;
    /**
     * 作品名称
     */
    private String name;
    /**
     * 分类id
     */
    private Long classifyId;
    /**
     * 作品授权
     */
    private String authType;
    /**
     * 图片id,逗号分隔
     */
    private String picFileId;
    /**
     * 主图
     */
    private String mainPicId;
    /**
     * 作品标签id,逗号分隔
     */
    private String label;
    /**
     * 作品简介
     */
    private String description;
    /**
     * 发布时间
     */
    private Date publishTime;
    /**
     * 是否设为精品 1精品 0非精品
     */
    private String isBest;
    /**
     * seo标题
     */
    private String seoTitle;
    /**
     * seo关键字
     */
    private String seoKeyword;
    /**
     * seo描述
     */
    private String seoDescription;
    /**
     * 推荐材料
     */
    private String material;
    /**
     * 推荐打印机
     */
    private String device;
    /**
     * 文章id,逗号分隔
     */
    private String articleId;
    /**
     * 状态 online:上架,offline:下架
     */
    private String state;
    /**
     * 上架类型 now:立即上架,delay:定时上架
     */
    private String onlineType;
    /**
     * 上架时间
     */
    private Date onlineTime;
    /**
     * 下架类型 forever:永久销售,delay:定时下架
     */
    private String offlineType;
    /**
     * 下架时间
     */
    private Date offlineTime;
    /**
     * stl obj文件列表
     */
    private List<DataModelProductDto> dataModelProductDtoDtoList;
    /**
     * sku关联
     */
    private List<SkuDto> skuDto;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getPicFileId() {
        return picFileId;
    }

    public void setPicFileId(String picFileId) {
        this.picFileId = picFileId;
    }

    public String getMainPicId() {
        return mainPicId;
    }

    public void setMainPicId(String mainPicId) {
        this.mainPicId = mainPicId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getIsBest() {
        return isBest;
    }

    public void setIsBest(String isBest) {
        this.isBest = isBest;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeyword() {
        return seoKeyword;
    }

    public void setSeoKeyword(String seoKeyword) {
        this.seoKeyword = seoKeyword;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public List<DataModelProductDto> getDataModelProductDtoDtoList() {
        return dataModelProductDtoDtoList;
    }

    public void setDataModelProductDtoDtoList(List<DataModelProductDto> dataModelProductDtoDtoList) {
        this.dataModelProductDtoDtoList = dataModelProductDtoDtoList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<SkuDto> getSkuDto() {
        return skuDto;
    }

    public void setSkuDto(List<SkuDto> skuDto) {
        this.skuDto = skuDto;
    }

    public BigDecimal getDataSharePrice() {
        return dataSharePrice;
    }

    public void setDataSharePrice(BigDecimal dataSharePrice) {
        this.dataSharePrice = dataSharePrice;
    }

    public String getOnlineType() {
        return onlineType;
    }

    public void setOnlineType(String onlineType) {
        this.onlineType = onlineType;
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
        this.offlineType = offlineType;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }
}


