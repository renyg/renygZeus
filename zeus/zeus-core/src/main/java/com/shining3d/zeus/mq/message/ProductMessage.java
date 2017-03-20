package com.shining3d.zeus.mq.message;

/**
 * Created by fe on 2017/1/5.
 */
public class ProductMessage {
    private Long productId;
    private String state;
    private Long executeTime;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }
}
