package com.shining3d.zeus.entity;

public class ModelMaterialRelationEntity extends BaseEntity {


    private Long deviceId;

    private Long marterialId;

    private Long precisionId;



    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getMarterialId() {
        return marterialId;
    }

    public void setMarterialId(Long marterialId) {
        this.marterialId = marterialId;
    }

    public Long getPrecisionId() {
        return precisionId;
    }

    public void setPrecisionId(Long precisionId) {
        this.precisionId = precisionId;
    }
}
