package com.shining3d.zeus.entity;

import java.math.BigDecimal;

/**
 * 基础库相关的设备型号管理
 *
 * @author renyg
 */
public class DeviceModelEntity extends BaseEntity {

    private String type;

    private Long supplierId;

    private String modelName;

    private String buildSizeReal;

    private String buildSizeTheory;

    private String precisionRange;

    private String resolution;

    private String layerThickness;

    private String printSpeed;

    private String status;

    private BigDecimal buildSizeRealX;

    private BigDecimal buildSizeRealY;

    private BigDecimal buildSizeRealZ;

    public BigDecimal getBuildSizeRealX() {
        return buildSizeRealX;
    }

    public void setBuildSizeRealX(BigDecimal buildSizeRealX) {
        this.buildSizeRealX = buildSizeRealX;
    }

    public BigDecimal getBuildSizeRealY() {
        return buildSizeRealY;
    }

    public void setBuildSizeRealY(BigDecimal buildSizeRealY) {
        this.buildSizeRealY = buildSizeRealY;
    }

    public BigDecimal getBuildSizeRealZ() {
        return buildSizeRealZ;
    }

    public void setBuildSizeRealZ(BigDecimal buildSizeRealZ) {
        this.buildSizeRealZ = buildSizeRealZ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getBuildSizeReal() {
        return buildSizeReal;
    }

    public void setBuildSizeReal(String buildSizeReal) {
        this.buildSizeReal = buildSizeReal == null ? null : buildSizeReal.trim();
    }

    public String getBuildSizeTheory() {
        return buildSizeTheory;
    }

    public void setBuildSizeTheory(String buildSizeTheory) {
        this.buildSizeTheory = buildSizeTheory == null ? null : buildSizeTheory.trim();
    }

    public String getPrecisionRange() {
        return precisionRange;
    }

    public void setPrecisionRange(String precisionRange) {
        this.precisionRange = precisionRange == null ? null : precisionRange.trim();
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution == null ? null : resolution.trim();
    }

    public String getLayerThickness() {
        return layerThickness;
    }

    public void setLayerThickness(String layerThickness) {
        this.layerThickness = layerThickness == null ? null : layerThickness.trim();
    }

    public String getPrintSpeed() {
        return printSpeed;
    }

    public void setPrintSpeed(String printSpeed) {
        this.printSpeed = printSpeed == null ? null : printSpeed.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
