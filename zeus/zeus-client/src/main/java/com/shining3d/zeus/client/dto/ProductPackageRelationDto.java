package com.shining3d.zeus.client.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fe on 2016/12/20.
 */
public class ProductPackageRelationDto implements Serializable{
    private static final long serialVersionUID = -7073468118973820708L;

    /**
     * productPackageRelationId
     */
    private Long productPackageRelationId;

    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 套餐商品id
     */
    private Long packageProductId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer num;

    public Long getProductPackageRelationId() {
        return productPackageRelationId;
    }

    public void setProductPackageRelationId(Long productPackageRelationId) {
        this.productPackageRelationId = productPackageRelationId;
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

    public Long getPackageProductId() {
        return packageProductId;
    }

    public void setPackageProductId(Long packageProductId) {
        this.packageProductId = packageProductId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
