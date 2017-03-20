package com.shining3d.zeus.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by fe on 2016/12/26.
 */
public enum SingleProductTypeEnum {
    DEVICE("device","设备"),
    MATERIAL("material","材料");

    SingleProductTypeEnum(String code, String text) {
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

    public static SingleProductTypeEnum fromCode(String code) {
        for (SingleProductTypeEnum singleProductTypeEnum : SingleProductTypeEnum.values()) {
            if (StringUtils.equals(code, singleProductTypeEnum.getCode())) {
                return singleProductTypeEnum;
            }
        }
        return null;
    }
}
