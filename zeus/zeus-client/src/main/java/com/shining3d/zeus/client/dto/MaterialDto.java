package com.shining3d.zeus.client.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.shining3d.common.dto.BaseDto;

public class MaterialDto extends BaseDto {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 分类id
     */
    private Long classifyId;

    private String name;
    /**
     * 材料唯一中文名
     */
    private String nameEn;

    private BigDecimal price;
    /**
     * 起步价
     */
    private BigDecimal startingPrice;

    private String density;

    private String status;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    /***
     * 打印周期
     */
    private String schedule;
    /**
     * 颜色
     */
    private String color;
    /**
     * 是否使用优惠券
     */
    private String useCoupon;
    /**
     * 简介
     */
    private String profile;
    /**
     * 详情
     */
    private String detail;
    /**
     * 审核数据的dfsId
     */
    private String dfsId;
    /**
     * 单独给页面添加的时候使用
     */
    private List<ResultMaterialDto> list;
    /**
     * 打印系数
     */
    private String precisionsRatio;
    /**
     * 精度
     */
    private String precisions;
    /**
     * 审核时候使用的文件名
     */
    private String fileName;


    private List<MaterialPrecisionDto> pricisionList;


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


    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density == null ? null : density.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule == null ? null : schedule.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(String useCoupon) {
        this.useCoupon = useCoupon == null ? null : useCoupon.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public List<ResultMaterialDto> getList() {
        return list;
    }

    public void setList(List<ResultMaterialDto> list) {
        this.list = list;
    }

    public String getDfsId() {
        return dfsId;
    }

    public void setDfsId(String dfsId) {
        this.dfsId = dfsId;
    }

    public String getPrecisionsRatio() {
        return precisionsRatio;
    }

    public void setPrecisionsRatio(String precisionsRatio) {
        this.precisionsRatio = precisionsRatio;
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

    public String getPrecisions() {
        return precisions;
    }

    public void setPrecisions(String precisions) {
        this.precisions = precisions;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<MaterialPrecisionDto> getPricisionList() {
        return pricisionList;
    }

    public void setPricisionList(List<MaterialPrecisionDto> pricisionList) {
        this.pricisionList = pricisionList;
    }

}
