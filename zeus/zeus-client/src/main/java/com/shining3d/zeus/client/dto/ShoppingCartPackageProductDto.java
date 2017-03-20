package com.shining3d.zeus.client.dto;

import java.math.BigDecimal;

/**
 * Created by fe on 2017/1/21.
 */
public class ShoppingCartPackageProductDto {

    /**
     * productId
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 主图
     */
    private String mainPicId;
    /**
     * 数量
     */
    private Integer num;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
