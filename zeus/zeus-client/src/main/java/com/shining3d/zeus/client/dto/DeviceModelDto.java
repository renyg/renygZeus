package com.shining3d.zeus.client.dto;

import com.shining3d.common.dto.BaseDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 基础库相关的设备型号管理
 *
 * @author renyg
 */
public class DeviceModelDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private String type;

    private Long supplierId;

    private String modelName;

    /**
     * 构建实际尺寸
     */
    private String buildSizeReal;

    /**
     * 构建理论尺寸
     */
    private String buildSizeTheory;

    private BigDecimal buildSizeRealX;

    private BigDecimal buildSizeRealY;

    private BigDecimal buildSizeRealZ;

    private String resolution;

    private String layerThickness;
    /**
     * 打印速率
     */
    private String printSpeed;

    private String status;
    /**
     * 精度范围
     */
    private String precisionRange;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 材料名称
     */
    private String materialName;
    /**
     * 材料唯一名
     */
    private String materialNameEn;

    private String precisionsName;

    private Long materialId;

    private String color;

    private BigDecimal price;

    private BigDecimal startingPrice;

    private List<MaterialParamDto> list;

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

    public String getPrecisionRange() {
        return precisionRange;
    }

    public void setPrecisionRange(String precisionRange) {
        this.precisionRange = precisionRange;
    }


    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialNameEn() {
        return materialNameEn;
    }

    public void setMaterialNameEn(String materialNameEn) {
        this.materialNameEn = materialNameEn;
    }

    public String getPrecisionsName() {
        return precisionsName;
    }

    public void setPrecisionsName(String precisionsName) {
        this.precisionsName = precisionsName;
    }


    public List<MaterialParamDto> getList() {
        return list;
    }

    public void setList(List<MaterialParamDto> list) {
        this.list = list;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
}
