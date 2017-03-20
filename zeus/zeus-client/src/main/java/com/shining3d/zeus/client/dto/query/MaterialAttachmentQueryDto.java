package com.shining3d.zeus.client.dto.query;

import java.io.Serializable;

/**
 * Created by tiea on 2017/3/13.
 */
public class MaterialAttachmentQueryDto implements Serializable {

    private static final long serialVersionUID = -2153129641880650167L;

    private String userId;
    private String dfsId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDfsId() {
        return dfsId;
    }

    public void setDfsId(String dfsId) {
        this.dfsId = dfsId;
    }

}
