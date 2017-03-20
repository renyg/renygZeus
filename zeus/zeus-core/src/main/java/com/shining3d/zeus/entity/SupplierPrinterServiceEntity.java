package com.shining3d.zeus.entity;

public class SupplierPrinterServiceEntity extends BaseEntity {

    private Long supplierId;

    private Long deviceModelId;

    private String deviceAsName;

    private Long adapterMaterialId;

    private String userId;

    private String color;

    private String status;

    private String precisions;

    private String buildSizeReal;


    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getDeviceAsName() {
        return deviceAsName;
    }

    public void setDeviceAsName(String deviceAsName) {
        this.deviceAsName = deviceAsName == null ? null : deviceAsName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrecisions() {
        return precisions;
    }

    public void setPrecisions(String precisions) {
        this.precisions = precisions;
    }

    public String getBuildSizeReal() {
        return buildSizeReal;
    }

    public void setBuildSizeReal(String buildSizeReal) {
        this.buildSizeReal = buildSizeReal;
    }

    public Long getAdapterMaterialId() {
        return adapterMaterialId;
    }

    public void setAdapterMaterialId(Long adapterMaterialId) {
        this.adapterMaterialId = adapterMaterialId;
    }
}
