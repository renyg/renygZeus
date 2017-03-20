package com.shining3d.zeus.client;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.query.MaterialAttachmentQueryDto;

/**
 * Created by tiea on 2017/3/13.
 */
public interface MaterialAttachmentApiService {

    Result<Boolean> hasPermissionMaterial(MaterialAttachmentQueryDto queryDto);

}
