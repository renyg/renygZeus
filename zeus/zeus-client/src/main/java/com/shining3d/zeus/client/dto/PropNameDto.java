package com.shining3d.zeus.client.dto;

import java.io.Serializable;

/**
 * Created by fe on 2016/12/26.
 * {"propName":"颜色","propValueName":"黑色"}
 */
public class PropNameDto implements Serializable{
    private static final long serialVersionUID = -7114075022405239294L;
    private String propName;
    private String propValueName;

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropValueName() {
        return propValueName;
    }

    public void setPropValueName(String propValueName) {
        this.propValueName = propValueName;
    }
}
