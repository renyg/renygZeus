package com.shining3d.zeus.service;

import com.shining3d.common.dto.OrderByInfo;
import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.MaterialApiService;
import com.shining3d.zeus.client.dto.MaterialDto;
import com.shining3d.zeus.client.dto.MaterialPrecisionDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.MaterialRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service(value = "materialService")
public class MaterialService implements MaterialApiService {

    @Resource
    MaterialRepository materialRepository;

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public Result<Boolean> batchDelete(String ids) {
        Result<Boolean> res = new Result<Boolean>();
        if (StringUtils.isEmpty(ids)) {
            res.setResult(false);
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            List<String> list = Arrays.asList(ids.split(";"));
            materialRepository.deleteByPKs(list);
        } catch (Exception e) {
            res.setResult(false);
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage());
        }
        return res;
    }

    /**
     * @param materialDto
     * @param pageInfo
     * @return
     */
    @Override
    public PageResult<MaterialDto> selectByConditions(MaterialDto materialDto, PageInfo pageInfo) {

        PageResult<MaterialDto> res = new PageResult<MaterialDto>();
        if (pageInfo.getPageIndex() < 0 || pageInfo.getPageSize() < 0) {
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            int count = materialRepository.getCount(materialDto);
            if (count > 0) {
                pageInfo.setRowCount(count);
                OrderByInfo o = new OrderByInfo("gmt_create", OrderByInfo.SORT_DIRECTION_DESC);
                List<OrderByInfo> li = new ArrayList<OrderByInfo>();
                li.add(o);
                pageInfo.setOrderByList(li);
                List<MaterialDto> list = materialRepository.selectByConditions(materialDto, pageInfo);
                res.setResult(list);
                res.setPageInfo(pageInfo);
            }
        } catch (Exception e) {
            res.setErrMsg(e.getMessage());
            res.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return res;
    }

    /**
     * 新增材料信息
     *
     * @param materialDto
     * @return
     */
    @Override
    public Result<MaterialDto> insert(MaterialDto materialDto, List<Map<String, String>> list) {
        Result<MaterialDto> res = new Result<MaterialDto>();
        if (null == list || list.isEmpty() || null == materialDto || null == materialDto.getClassifyId()
                || StringUtils.isEmpty(materialDto.getName())
                || StringUtils.isEmpty(materialDto.getNameEn())
                || null == materialDto.getStartingPrice()
                || null == materialDto.getPrice()
                || StringUtils.isEmpty(materialDto.getDensity())
                || StringUtils.isEmpty(materialDto.getSchedule())
                || StringUtils.isEmpty(materialDto.getColor())
                || StringUtils.isEmpty(materialDto.getProfile())) {
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            materialRepository.insertSelective(materialDto, list);
        } catch (RepositoryException e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage());
        } catch (BizException e) {
            res.setErrCode(e.getCode());
            res.setErrMsg(e.getMessage());
        }
        return res;

    }

    /**
     * @param materialDto
     * @return
     */
    @Override
    public Result<MaterialDto> update(MaterialDto materialDto, List<Map<String, String>> list) {
        Result<MaterialDto> res = new Result<MaterialDto>();
        if (null == materialDto || null == materialDto.getClassifyId()
                || null == materialDto.getId()
                || StringUtils.isEmpty(materialDto.getName())
                || StringUtils.isEmpty(materialDto.getNameEn())
                || null == materialDto.getStartingPrice()
                || null == materialDto.getPrice()
                || StringUtils.isEmpty(materialDto.getDensity())
                || StringUtils.isEmpty(materialDto.getSchedule())
                || StringUtils.isEmpty(materialDto.getColor())
                || StringUtils.isEmpty(materialDto.getProfile())) {
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            materialRepository.updateByPrimaryKeySelective(materialDto, list);
        } catch (RepositoryException e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage());
        } catch (BizException e) {
            res.setErrCode(e.getCode());
            res.setErrMsg(e.getMessage());
        }
        return res;

    }

    /**
     * @param status
     * @param ids
     * @return
     */
    @Override
    public Result<Boolean> batchOperateStatus(String status, String ids) {
        Result<Boolean> res = new Result<Boolean>();

        if (StringUtils.isEmpty(status) || StringUtils.isEmpty(ids)) {
            res.setResult(false);
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            materialRepository.batchOperateStatus(status, ids);
        } catch (Exception e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage());
        }
        return res;
    }

    /**
     * @param materialPrecisionDto
     * @return
     */
    @Override
    public Result<List<MaterialPrecisionDto>> getPrecisionByCondition(MaterialPrecisionDto materialPrecisionDto) {
        Result<List<MaterialPrecisionDto>> res = new Result<List<MaterialPrecisionDto>>();
        if (null == materialPrecisionDto) {
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            List<MaterialPrecisionDto> list = materialRepository.getPrecisionByCondition(materialPrecisionDto);
            res.setResult(list);
        } catch (Exception e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage());
        }
        return res;
    }

    /**
     * 提供给材料备案查询用（新增的时候）
     *
     * @param nameEn
     * @param classifyId
     * @return
     */
    @Override
    public Result<List<MaterialPrecisionDto>> getListByNameAndClassify(String nameEn, String classifyId) {
        Result<List<MaterialPrecisionDto>> res = new Result<List<MaterialPrecisionDto>>();
        if (StringUtils.isEmpty(nameEn) || StringUtils.isEmpty(classifyId)) {
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            List<MaterialPrecisionDto> list = materialRepository.getListByNameAndClassify(nameEn, classifyId);
            res.setResult(list);
        } catch (Exception e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage());
        }
        return res;

    }

    @Override
    public Result<List<MaterialDto>> getCanOrderMaterialByCondition(String buildSizeRealX, String buildSizeRealY, String buildSizeRealZ) {
        Result<List<MaterialDto>> res = new Result<List<MaterialDto>>();
        if (StringUtils.isEmpty(buildSizeRealX) || StringUtils.isEmpty(buildSizeRealY) || StringUtils.isEmpty(buildSizeRealZ)) {
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            List<MaterialDto> list = materialRepository.getCanOrderMaterialByCondition(buildSizeRealX, buildSizeRealY, buildSizeRealZ);
            res.setResult(list);
        } catch (Exception e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage());
        }
        return res;
    }
    @Override
    public Result<List<MaterialDto>> batchSelectByIds(List<Long> ids) {
        Result<List<MaterialDto>> res = new Result<List<MaterialDto>>();
        if (CollectionUtils.isEmpty(ids)) {
            res.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            res.setErrMsg("参数缺失！");
            return res;
        }
        try {
            List<MaterialDto> list = materialRepository.batchSelectByIds(ids);
            res.setResult(list);
        } catch (Exception e) {
            res.setErrCode(BizConstant.COMMON_BIZ_ERROR);
            res.setErrMsg(e.getMessage());
        }
        return res;
    }
}
