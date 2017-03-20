package com.shining3d.zeus.client.dto;

import com.shining3d.common.dto.BaseDto;

import java.util.Date;

public class MaterialRecordationServiceDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private String userId;

    private Long recordMaterialId;

    private String recordMaterialName;

    private String materialClassifyId;
    /**
     * 物流编号
     */
    private String logistics;
    /**
     * 物流公司
     */
    private String logisticsCompany;

    private String status;
    /**
     * 精度范围
     */
    private String precisions;
    /**
     * 精细、普通、超精细
     */
    private String precisionsDegree;

    /**
     * 审核不同的原因存储字段
     */
    private String reason;
    /**
     * 审核的图片的ID
     */
    private String auditDfsId;

    private String dfsId;

    private String fileName;

    /**
     * 查询时候开始时间
     */
    private Date gmtCreateStart;
    /**
     * 查询时候结束时间
     */
    private Date gmtCreateEnd;

    /**
     * 审核的时候需要同步到服务商的表,只在更新状态的时候需要
     */
    private String precisionsAudit;

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

    public String getDfsId() {
        return dfsId;
    }

    public void setDfsId(String dfsId) {
        this.dfsId = dfsId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Date getGmtCreateStart() {
        return gmtCreateStart;
    }

    public void setGmtCreateStart(Date gmtCreateStart) {
        this.gmtCreateStart = gmtCreateStart;
    }

    public Date getGmtCreateEnd() {
        return gmtCreateEnd;
    }

    public void setGmtCreateEnd(Date gmtCreateEnd) {
        this.gmtCreateEnd = gmtCreateEnd;
    }

    public String getPrecisionsAudit() {
        return precisionsAudit;
    }

    public void setPrecisionsAudit(String precisionsAudit) {
        this.precisionsAudit = precisionsAudit;
    }

    public Long getRecordMaterialId() {
        return recordMaterialId;
    }

    public void setRecordMaterialId(Long recordMaterialId) {
        this.recordMaterialId = recordMaterialId;
    }
}
