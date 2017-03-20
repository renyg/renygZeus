package com.shining3d.zeus.client.dto;

import java.io.Serializable;

/**
 * Created by fe on 2016/12/26.
 *
 */
public class PropKeyDto implements Serializable {
    private static final long serialVersionUID = -3775805855680270015L;
    private Long propId;
    private Long propValueId;

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public Long getPropValueId() {
        return propValueId;
    }

    public void setPropValueId(Long propValueId) {
        this.propValueId = propValueId;
    }
}
