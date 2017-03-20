package com.shining3d.zeus.client.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fe on 2016/12/20.
 */
public class PropValueDto implements Serializable {
    private static final long serialVersionUID = 2399366052371374691L;

    /**
     * 属性值id
     */
    private Long propValueId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 属性值名称
     */
    private String propValueName;
    /**
     * 属性id
     */
    private Long propId;
    /**
     * 属性名称
     */
    private String propName;
    /**
     * 排序
     */
    private Integer sortNo;
    /**
     * 说明
     */
    private String comments;

    public Long getPropValueId() {
        return propValueId;
    }

    public void setPropValueId(Long propValueId) {
        this.propValueId = propValueId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getPropValueName() {
        return propValueName;
    }

    public void setPropValueName(String propValueName) {
        this.propValueName = propValueName;
    }

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
