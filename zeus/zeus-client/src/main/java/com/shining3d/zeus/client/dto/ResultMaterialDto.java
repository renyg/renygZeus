package com.shining3d.zeus.client.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.shining3d.common.dto.BaseDto;

import java.math.BigDecimal;
import java.util.Date;

public class ResultMaterialDto extends BaseDto {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private String nameEn;

    private BigDecimal price;

    private BigDecimal startingPrice;

    private String density;

    private String color;

    private Long classifyId;

    private Long materialId;

    private String profile;

    private String schedule;
    /**
     * 精度系数
     */
    private String precisionsRatio;


    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density == null ? null : density.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPrecisionsRatio() {
        return precisionsRatio;
    }

    public void setPrecisionsRatio(String precisionsRatio) {
        this.precisionsRatio = precisionsRatio;
    }
}
