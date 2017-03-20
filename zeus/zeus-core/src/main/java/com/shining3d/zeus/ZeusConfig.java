package com.shining3d.zeus;

import java.util.Map;

/**
 * Created by fe on 2016/12/8.
 */
public class ZeusConfig {

    private String jobMailSender;
    private String jobMailEnv;
    private String jobMailReceivers;
    private String jobMailProject;

    private String zeusDirectExchange;
    private String productStateModifyDlxRouteKey;

    private Long materialCategoryId;
    private Long dataProductCategoryId;
    private Long devicePrintCategoryId;
    private Long deviceScanCategoryId;
    private Long packageCategoryId;

    private Long packagePropId = 3l;
    private String packagePropName = "套餐";

    private String bearyChatWarnUrl;

    private int shoppingCartSkuMaxNum;

    public String getJobMailSender() {
        return jobMailSender;
    }

    public void setJobMailSender(String jobMailSender) {
        this.jobMailSender = jobMailSender;
    }

    public String getJobMailEnv() {
        return jobMailEnv;
    }

    public void setJobMailEnv(String jobMailEnv) {
        this.jobMailEnv = jobMailEnv;
    }

    public String getJobMailReceivers() {
        return jobMailReceivers;
    }

    public void setJobMailReceivers(String jobMailReceivers) {
        this.jobMailReceivers = jobMailReceivers;
    }

    public String getJobMailProject() {
        return jobMailProject;
    }

    public void setJobMailProject(String jobMailProject) {
        this.jobMailProject = jobMailProject;
    }

    public Long getDataProductCategoryId() {
        return dataProductCategoryId;
    }

    public void setDataProductCategoryId(Long dataProductCategoryId) {
        this.dataProductCategoryId = dataProductCategoryId;
    }

    public Long getDevicePrintCategoryId() {
        return devicePrintCategoryId;
    }

    public void setDevicePrintCategoryId(Long devicePrintCategoryId) {
        this.devicePrintCategoryId = devicePrintCategoryId;
    }

    public Long getDeviceScanCategoryId() {
        return deviceScanCategoryId;
    }

    public void setDeviceScanCategoryId(Long deviceScanCategoryId) {
        this.deviceScanCategoryId = deviceScanCategoryId;
    }

    public Long getMaterialCategoryId() {
        return materialCategoryId;
    }

    public void setMaterialCategoryId(Long materialCategoryId) {
        this.materialCategoryId = materialCategoryId;
    }

    public Long getPackageCategoryId() {
        return packageCategoryId;
    }

    public void setPackageCategoryId(Long packageCategoryId) {
        this.packageCategoryId = packageCategoryId;
    }

    public String getZeusDirectExchange() {
        return zeusDirectExchange;
    }

    public void setZeusDirectExchange(String zeusDirectExchange) {
        this.zeusDirectExchange = zeusDirectExchange;
    }

    public String getProductStateModifyDlxRouteKey() {
        return productStateModifyDlxRouteKey;
    }

    public void setProductStateModifyDlxRouteKey(String productStateModifyDlxRouteKey) {
        this.productStateModifyDlxRouteKey = productStateModifyDlxRouteKey;
    }

    public Long getPackagePropId() {
        return packagePropId;
    }

    public void setPackagePropId(Long packagePropId) {
        this.packagePropId = packagePropId;
    }

    public String getPackagePropName() {
        return packagePropName;
    }

    public void setPackagePropName(String packagePropName) {
        this.packagePropName = packagePropName;
    }

    public int getShoppingCartSkuMaxNum() {
        return shoppingCartSkuMaxNum;
    }

    public void setShoppingCartSkuMaxNum(int shoppingCartSkuMaxNum) {
        this.shoppingCartSkuMaxNum = shoppingCartSkuMaxNum;
    }

    public String getBearyChatWarnUrl() {
        return bearyChatWarnUrl;
    }

    public void setBearyChatWarnUrl(String bearyChatWarnUrl) {
        this.bearyChatWarnUrl = bearyChatWarnUrl;
    }
}
