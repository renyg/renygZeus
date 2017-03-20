package com.shining3d.zeus.entity;

public class MaterialRecordationServiceEntity extends BaseEntity {

    private String userId;

    private Long recordMaterialId;

    private String materialClassifyId;

    private String recordMaterialName;

    private String precisions;

    private String precisionsDegree;

    private String logistics;

    private String logisticsCompany;

    private String status;

    /**
     * 审核不同的原因存储字段
     */
    private String reason;
    /**
     * 审核的图片的ID
     */
    private String auditDfsId;




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }



    public String getMaterialClassifyId() {
        return materialClassifyId;
    }

    public void setMaterialClassifyId(String materialClassifyId) {
        this.materialClassifyId = materialClassifyId == null ? null : materialClassifyId.trim();
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? null : logistics.trim();
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany == null ? null : logisticsCompany.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRecordMaterialName() {
        return recordMaterialName;
    }

    public void setRecordMaterialName(String recordMaterialName) {
        this.recordMaterialName = recordMaterialName;
    }

    public String getPrecisions() {
        return precisions;
    }

    public void setPrecisions(String precisions) {
        this.precisions = precisions;
    }

    public String getPrecisionsDegree() {
        return precisionsDegree;
    }

    public void setPrecisionsDegree(String precisionsDegree) {
        this.precisionsDegree = precisionsDegree;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAuditDfsId() {
        return auditDfsId;
    }

    public void setAuditDfsId(String auditDfsId) {
        this.auditDfsId = auditDfsId;
    }

    public Long getRecordMaterialId() {
        return recordMaterialId;
    }

    public void setRecordMaterialId(Long recordMaterialId) {
        this.recordMaterialId = recordMaterialId;
    }
}
