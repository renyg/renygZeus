package com.shining3d.zeus.client.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fe on 2016/12/20.
 */
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 8141304225989687264L;

    /**
     * productId
     */
    private Long productId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 用户id 数据商品时 有该字段返回
     */
    private String userId;
    /**
     * 数据商品时 关联3d_data dataId
     */
    private Long dataId;
    /**
     * 数据商品时 有分润价格
     */
    private BigDecimal dataSharePrice;
    /**
     * 图片id,逗号分隔
     */
    private String picFileId;
    /**
     * 主图
     */
    private String mainPicId;
    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 描述
     */
    private String description;
    /**
     * 商品类型 single单品,package套餐
     */
    private String productType;
    /**
     * 发布时间
     */
    private Date publishTime;
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
     * 售卖sku
     */
    private List<SkuDto> skuDtoList;
    /**
     * 套餐商品类型关联商品列表
     */
    private List<ProductPackageRelationDto> productPackageRelationDtoList;

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

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.description = description;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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
        this.state = state;
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

    public List<SkuDto> getSkuDtoList() {
        return skuDtoList;
    }

    public void setSkuDtoList(List<SkuDto> skuDtoList) {
        this.skuDtoList = skuDtoList;
    }

    public List<ProductPackageRelationDto> getProductPackageRelationDtoList() {
        return productPackageRelationDtoList;
    }

    public void setProductPackageRelationDtoList(List<ProductPackageRelationDto> productPackageRelationDtoList) {
        this.productPackageRelationDtoList = productPackageRelationDtoList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
