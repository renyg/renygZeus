package com.shining3d.zeus.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by renyg on 2016/12/26.
 */
public enum MaterialFilingEnum {
    FILING("filing", "备案中"),
    NOFILE("noFile", "未备案"),
    FILED("filed", "已备案");

    MaterialFilingEnum(String code, String text) {
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

    public static MaterialFilingEnum fromCode(String code) {
        for (MaterialFilingEnum typeEnum : MaterialFilingEnum.values()) {
            if (StringUtils.equals(code, typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }
}
