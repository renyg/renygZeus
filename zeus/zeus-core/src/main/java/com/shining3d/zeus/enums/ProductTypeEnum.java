package com.shining3d.zeus.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by fe on 2016/12/26.
 */
public enum ProductTypeEnum {
    DATA("data","数据"),
    DEVICE("device","设备"),
    MATERIAL("material","材料"),
    PACKAGE("package","套餐");

    ProductTypeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private String code;
    private String text;

    public String getText() {
        return text;
    }
    public String getCode() {
        return code;
    }

    public static ProductTypeEnum fromCode(String code) {
        for (ProductTypeEnum productTypeEnum : ProductTypeEnum.values()) {
            if (StringUtils.equals(code, productTypeEnum.getCode())) {
                return productTypeEnum;
            }
        }
        return null;
    }
}
