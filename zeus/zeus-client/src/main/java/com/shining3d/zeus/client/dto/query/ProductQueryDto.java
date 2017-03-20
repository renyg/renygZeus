package com.shining3d.zeus.client.dto.query;

/**
 * Created by fe on 2016/12/29.
 */
public class ProductQueryDto {
    /**
     * 名称
     */
    private String name;
    /**
     * 商品类型,data数据,device设备,material材料,package套餐
     */
    private String type;

    /**
     * 状态
     */
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
