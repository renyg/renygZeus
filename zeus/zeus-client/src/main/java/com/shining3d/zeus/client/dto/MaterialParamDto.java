package com.shining3d.zeus.client.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.shining3d.common.dto.BaseDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class MaterialParamDto extends BaseDto {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String precisions;

    private String precisionsName;

    private Long materialId;

    private String materialName;

    private Long precisionsId;

    private String precisionsRatio;


    public String getPrecisions() {
        return precisions;
    }

    public void setPrecisions(String precisions) {
        this.precisions = precisions;
    }

    public String getPrecisionsName() {
        return precisionsName;
    }

    public void setPrecisionsName(String precisionsName) {
        this.precisionsName = precisionsName;
    }


    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }


    public String getPrecisionsRatio() {
        return precisionsRatio;
    }

    public void setPrecisionsRatio(String precisionsRatio) {
        this.precisionsRatio = precisionsRatio;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getPrecisionsId() {
        return precisionsId;
    }

    public void setPrecisionsId(Long precisionsId) {
        this.precisionsId = precisionsId;
    }
}
