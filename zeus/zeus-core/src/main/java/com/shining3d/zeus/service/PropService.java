package com.shining3d.zeus.service;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.PropApiService;
import com.shining3d.zeus.client.dto.PropDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.PropRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fe on 2017/1/10.
 */
@Service
public class PropService implements PropApiService {

    @Resource
    private PropRepository propRepository;

    @Override
    public Result<List<PropDto>> queryPropListByCategoryId(Long categoryId) {
        Result<List<PropDto>> ret = new Result<List<PropDto>>();
        try {
            List<PropDto> result = propRepository.queryPropListByCategoryId(categoryId);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }
}
