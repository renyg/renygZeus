package com.shining3d.zeus.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by fe on 2016/12/26.
 */
public enum StateEnum {
    ONLINE("online","上架"),
    OFFLINE("offline","下架");

    StateEnum(String code, String text) {
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

    public static StateEnum fromCode(String code) {
        for (StateEnum stateEnum : StateEnum.values()) {
            if (StringUtils.equals(code, stateEnum.getCode())) {
                return stateEnum;
            }
        }
        return null;
    }
}
