package com.shining3d.zeus.client.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fe on 2016/12/20.
 */
public class PropDto implements Serializable{
    private static final long serialVersionUID = -1860132820866463783L;

    /**
     * 属性id
     */
    private Long propId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 属性名称
     */
    private String propName;
    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 排序
     */
    private Integer sortNo;
    /**
     * 说明
     */
    private String comments;



    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
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

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
