package com.shining3d.zeus.client.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fe on 2016/12/20.
 */
public class SkuDto implements Serializable {
    private static final long serialVersionUID = 3222400403037403239L;

    /**
     * skuId
     */
    private Long skuId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 是否删除
     */
    private String isDeleted;
    /**
     * productId
     */
    private Long productId;
    /**
     * 属性及属性值id组合,格式[{"propId":1,"propValueId":1}]
     */
    private String propKey;
    /**
     * 属性及属性值name组合[{"propName":"颜色","propValueName":"黑色"}]
     */
    private String propName;
    /**
     * 库存数量,-1无限库存数量
     */
    private Integer quantity;
    /**
     * 单位
     */
    private String unit;
    /**
     * 单位数量
     */
    private Double unitNum;
    /**
     * 原价
     */
    private BigDecimal originalPrice;
    /**
     * 促销价格
     */
    private BigDecimal promotionPrice;
    /**
     * 促销开始时间
     */
    private Date promotionStartTime;
    /**
     * 促销结束时间
     */
    private Date promotionEndTime;
    /**
     * 是否可以使用优惠券
     */
    private String canUseCoupon;
    /**
     * 说明
     */
    private String comments;


    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(Double unitNum) {
        this.unitNum = unitNum;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Date getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(Date promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public Date getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(Date promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public String getCanUseCoupon() {
        return canUseCoupon;
    }

    public void setCanUseCoupon(String canUseCoupon) {
        this.canUseCoupon = canUseCoupon;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
}
