package com.shining3d.zeus.client.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fe on 2016/12/20.
 */
public class ShoppingCartDto implements Serializable{
    private static final long serialVersionUID = 8679785952306037894L;
    /**
     * 购物车主键id
     */
    private Long shoppingCartId;
    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 用户id
     */
    private String userId;

    /**
     * skuId
     */
    private Long skuId;
    /**
     * productId
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer num;

    /**
     * 商品名称
     */
    private String name;
    /**
     * 主图文件id
     */
    private String mainPicId;
    /**
     * 所属类目名称
     */
    private String categoryName;

    /**
     * sku是否被删除 y/n
     */
    private String skuIsDeleted;
    /**
     * 属性及属性值id组合,格式[{"propId":1,"propValueId":1}]
     */
    private String propKey;
    /**
     * 属性及属性值name组合[{"propName":"颜色","propValueName":"黑色"}]
     */
    private String propName;
    /**
     * 原价
     */
    private BigDecimal originalPrice;
    /**
     * 促销开始时间
     */
    private Date promotionStartTime;
    /**
     * 促销结束时间
     */
    private Date promotionEndTime;
    /**
     * 促销价格
     */
    private BigDecimal promotionPrice;
    /**
     * 状态 online:上架,offline:下架
     */
    private String state;
    /**
     * 数据商品时 有分润价格
     */
    private BigDecimal dataSharePrice;
    /**
     * 商品类型,data数据,device设备,material材料,package套餐
     */
    private String productType;
    /**
     * 如果是套餐商品package，则把对应的套餐下商品填充进来
     */
    private List<ShoppingCartPackageProductDto> shoppingCartPackageProductDtoList;


    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainPicId() {
        return mainPicId;
    }

    public void setMainPicId(String mainPicId) {
        this.mainPicId = mainPicId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getDataSharePrice() {
        return dataSharePrice;
    }

    public void setDataSharePrice(BigDecimal dataSharePrice) {
        this.dataSharePrice = dataSharePrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<ShoppingCartPackageProductDto> getShoppingCartPackageProductDtoList() {
        return shoppingCartPackageProductDtoList;
    }

    public void setShoppingCartPackageProductDtoList(List<ShoppingCartPackageProductDto> shoppingCartPackageProductDtoList) {
        this.shoppingCartPackageProductDtoList = shoppingCartPackageProductDtoList;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getSkuIsDeleted() {
        return skuIsDeleted;
    }

    public void setSkuIsDeleted(String skuIsDeleted) {
        this.skuIsDeleted = skuIsDeleted;
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
}
