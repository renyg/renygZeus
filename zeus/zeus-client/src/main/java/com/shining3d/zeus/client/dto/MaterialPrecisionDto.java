package com.shining3d.zeus.client.dto;

import com.shining3d.common.dto.BaseDto;

/**
 * 基础库相关的设备型号管理
 *
 * @author renyg
 */
public class MaterialPrecisionDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Long   objectId;

    private String precisions;

    private String precisionsName;

    private String precisionsRatio;

    private Long precisionsId;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getPrecisionsRatio() {
        return precisionsRatio;
    }

    public void setPrecisionsRatio(String precisionsRatio) {
        this.precisionsRatio = precisionsRatio;
    }

    public String getPrecisionsName() {
        return precisionsName;
    }

    public void setPrecisionsName(String precisionsName) {
        this.precisionsName = precisionsName;
    }

    public String getPrecisions() {
        return precisions;
    }

    public void setPrecisions(String precisions) {
        this.precisions = precisions;
    }

    public Long getPrecisionsId() {
        return precisionsId;
    }

    public void setPrecisionsId(Long precisionsId) {
        this.precisionsId = precisionsId;
    }
}
