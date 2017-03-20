package com.shining3d.zeus.client.dto.query;

import java.io.Serializable;

/**
 * Created by fe on 2017/1/13.
 */
public class CategoryQueryDto implements Serializable {
    private static final long serialVersionUID = 422345189708963660L;
    private Long categoryId;
    private String categoryName;
    private String code;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
