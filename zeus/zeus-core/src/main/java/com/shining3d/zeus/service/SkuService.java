package com.shining3d.zeus.service;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.SkuApiService;
import com.shining3d.zeus.client.dto.SkuDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.SkuRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fe on 2017/1/10.
 */
@Service
public class SkuService implements SkuApiService {

    @Resource
    private SkuRepository skuRepository;

    @Override
    public Result<SkuDto> querySkuBySkuId(Long skuId) {
        Result<SkuDto> ret = new Result<SkuDto>();
        try {
            SkuDto result = skuRepository.querySkuBySkuId(skuId);
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
