package com.shining3d.zeus.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by renyg on 2016/12/26.
 */
public enum MaterialTypeEnum {
    COMMIT("commit", "提交"),
    AUDITING("audit", "审核中"),
    PASS("pass", "审核通过"),
    NOPASS("nopass", "不通过");

    MaterialTypeEnum(String code, String text) {
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

    public static MaterialTypeEnum fromCode(String code) {
        for (MaterialTypeEnum onlineTypeEnum : MaterialTypeEnum.values()) {
            if (StringUtils.equals(code, onlineTypeEnum.getCode())) {
                return onlineTypeEnum;
            }
        }
        return null;
    }
}
