package com.shining3d.zeus.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public enum PrintEnum {
    PRINT("print","打印"),
    SCAN("scan","扫描");

    PrintEnum(String code, String text) {
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

    public static PrintEnum fromCode(String code) {
        for (PrintEnum stateEnum : PrintEnum.values()) {
            if (StringUtils.equals(code, stateEnum.getCode())) {
                return stateEnum;
            }
        }
        return null;
    }

}
