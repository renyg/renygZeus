package com.shining3d.zeus.service;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.PropValueApiService;
import com.shining3d.zeus.client.dto.PropValueDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.PropValueDao;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.PropValueRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fe on 2016/12/26.
 */
@Service
public class PropValueService implements PropValueApiService {


    @Resource
    private PropValueRepository propValueRepository;

    @Override
    public Result<List<PropValueDto>> queryPropValueListByPropName(Long categoryId, String propName) {
        Result<List<PropValueDto>> ret = new Result<List<PropValueDto>>();
        try {
            List<PropValueDto> result = propValueRepository.queryPropValueListByPropName(categoryId,propName);
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
