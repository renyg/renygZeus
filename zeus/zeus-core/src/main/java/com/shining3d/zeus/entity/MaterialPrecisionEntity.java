package com.shining3d.zeus.entity;

import com.shining3d.common.dto.BaseDto;

/**
 * 基础库相关的设备型号管理
 *
 * @author renyg
 */
public class MaterialPrecisionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private Long   objectId;

    private String precisions;

    private String precisionsName;

    private String precisionsRatio;



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
}
