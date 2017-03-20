package com.shining3d.zeus.service;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.MaterialRecordationServiceApiService;
import com.shining3d.zeus.client.dto.MaterialRecordationServiceDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.repository.MaterialRecordationServiceRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "materialRecordationService")
public class MaterialRecordationService implements MaterialRecordationServiceApiService {

    @Resource
    MaterialRecordationServiceRepository materialRecordationServiceRepository;


    @Override
    public Result<Boolean> deleteById(String recordMaterialId, String userId) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            if (StringUtils.isEmpty(recordMaterialId) || StringUtils.isEmpty(userId)) {
                ret.setErrMsg("参数错误");
                ret.setErrCode(BizConstant.COMMON_PARAM_ERROR);
                return ret;
            }
            materialRecordationServiceRepository.deleteBatchById(recordMaterialId, userId);
        } catch (Exception e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }

        return ret;
    }

    @Override
    public Result<MaterialRecordationServiceDto> insert(MaterialRecordationServiceDto materialRecordationServiceDto) {
        Result<MaterialRecordationServiceDto> res = new Result<MaterialRecordationServiceDto>();
        if (null == materialRecordationServiceDto
                || StringUtils.isEmpty(materialRecordationServiceDto.getUserId())
                || StringUtils.isEmpty(materialRecordationServiceDto.getMaterialClassifyId())
                || null == materialRecordationServiceDto.getRecordMaterialId()
                || StringUtils.isEmpty(materialRecordationServiceDto.getRecordMaterialName())
                || StringUtils.isEmpty(materialRecordationServiceDto.getPrecisions())){
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            materialRecordationServiceRepository.insert(materialRecordationServiceDto);
        } catch (Exception e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage().toString());
        }
        return res;

    }

    @Override
    public Result<MaterialRecordationServiceDto> update(MaterialRecordationServiceDto materialRecordationServiceDto) {
        Result<MaterialRecordationServiceDto> res = new Result<MaterialRecordationServiceDto>();
        if (null == materialRecordationServiceDto
                || null ==materialRecordationServiceDto.getRecordMaterialId()
                || StringUtils.isEmpty(materialRecordationServiceDto.getStatus())
                || StringUtils.isEmpty(materialRecordationServiceDto.getUserId())) {
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            materialRecordationServiceRepository.update(materialRecordationServiceDto);
        } catch (Exception e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage().toString());
        }
        return res;
    }

    @Override
    public PageResult<MaterialRecordationServiceDto> getMaterialRecordationCondition(MaterialRecordationServiceDto materialRecordationServiceDto, PageInfo pageInfo) {
        PageResult<MaterialRecordationServiceDto> res = new PageResult<MaterialRecordationServiceDto>();
        try {
            List<MaterialRecordationServiceDto> list = materialRecordationServiceRepository.getMaterialRecordationCondition(materialRecordationServiceDto,pageInfo);
            res.setResult(list);
            res.setPageInfo(pageInfo);
        } catch (Exception e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage().toString());
        }
        return res;
    }
}
