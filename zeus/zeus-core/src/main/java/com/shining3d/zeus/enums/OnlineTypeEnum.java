package com.shining3d.zeus.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by fe on 2016/12/26.
 */
public enum OnlineTypeEnum {
    NOW("now","立即上架"),
    DELAY("delay","定时上架");

    OnlineTypeEnum(String code, String text) {
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

    public static OnlineTypeEnum fromCode(String code) {
        for (OnlineTypeEnum onlineTypeEnum : OnlineTypeEnum.values()) {
            if (StringUtils.equals(code, onlineTypeEnum.getCode())) {
                return onlineTypeEnum;
            }
        }
        return null;
    }
}
