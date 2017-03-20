package com.shining3d.zeus.service;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.MaterialAttachmentApiService;
import com.shining3d.zeus.client.dto.query.MaterialAttachmentQueryDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.MaterialRecordationServiceRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tiea on 2017/3/13.
 */
@Service(value = "materialAttachmentService")
public class MaterialAttachmentService implements MaterialAttachmentApiService {

    @Resource
    private MaterialRecordationServiceRepository materialRecordationServiceRepository;

    @Override
    public Result<Boolean> hasPermissionMaterial(MaterialAttachmentQueryDto queryDto) {
        Result<Boolean> ret = new Result<>();
        if (queryDto == null || StringUtils.isEmpty(queryDto.getDfsId())
                || StringUtils.isEmpty(queryDto.getUserId())) {
            ret.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            ret.setSuccess(false);
            return ret;
        }
        try {
            int count =
                    materialRecordationServiceRepository.countPermissionMaterialAttachment(queryDto);
            ret.setResult(count > 0);

        } catch (BizException e) {
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        } catch (RepositoryException e) {
            ret.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            ret.setSuccess(false);
        }
        return ret;
    }
}
