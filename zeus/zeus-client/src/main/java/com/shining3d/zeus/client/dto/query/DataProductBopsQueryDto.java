package com.shining3d.zeus.client.dto.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fe on 2016/12/22.
 */
public class DataProductBopsQueryDto implements Serializable {

    private static final long serialVersionUID = -5303274686833582898L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 发布起始时间
     */
    private Date publishBeginTime;
    /**
     * 发布结束时间
     */
    private Date publishEndTime;
    /**
     * 名称
     */
    private String name;

    /**
     * 状态
     */
    private String state;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPublishBeginTime() {
        return publishBeginTime;
    }

    public void setPublishBeginTime(Date publishBeginTime) {
        this.publishBeginTime = publishBeginTime;
    }

    public Date getPublishEndTime() {
        return publishEndTime;
    }

    public void setPublishEndTime(Date publishEndTime) {
        this.publishEndTime = publishEndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
