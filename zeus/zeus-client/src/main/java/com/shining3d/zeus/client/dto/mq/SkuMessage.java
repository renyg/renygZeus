package com.shining3d.zeus.client.dto.mq;

import java.util.List;

/**
 * Created by fe on 2017/2/13.
 */
public class SkuMessage {
    private List<Long> skuIdList;
    private String userId;

    public List<Long> getSkuIdList() {
        return skuIdList;
    }

    public void setSkuIdList(List<Long> skuIdList) {
        this.skuIdList = skuIdList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
