package com.shining3d.zeus.client;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.DeviceModelDto;
import com.shining3d.zeus.client.dto.MaterialDto;
import com.shining3d.zeus.client.dto.MaterialPrecisionDto;

import java.util.List;
import java.util.Map;

/**
 * Created by renyg on 2016/12/21.
 */
public interface MaterialApiService {

    /**
     * @param materialDto
     * @return
     * @title 增加材料信息
     */
    public Result<MaterialDto> insert(MaterialDto materialDto, List<Map<String, String>> list);

    /**
     * @param materialDto
     * @return
     * @title 修改材料
     */
    public Result<MaterialDto> update(MaterialDto materialDto, List<Map<String, String>> list);

    /**
     * @param status
     * @param ids
     * @return
     * @title 批量或者单个修改状态
     */
    public Result<Boolean> batchOperateStatus(String status, String ids);

    /**
     * @param ids
     * @return
     * @title 批量删除
     */
    public Result<Boolean> batchDelete(String ids);

    /**
     * @param materialDto
     * @param pageInfo
     * @return
     * @title 通过条件查询材料信息
     */
    public PageResult<MaterialDto> selectByConditions(MaterialDto materialDto, PageInfo pageInfo);

    /**
     * @param materialPrecisionDto
     * @return
     * @title 分页查询
     */
    public Result<List<MaterialPrecisionDto>> getPrecisionByCondition(MaterialPrecisionDto materialPrecisionDto);


    /**
     * @param nameEn
     * @param classifyId
     * @return
     * @title 提供给材料备案查询用（新增的时候）
     */
    public Result<List<MaterialPrecisionDto>> getListByNameAndClassify(String nameEn, String classifyId);

    /**
     * @param buildSizeRealX
     * @param buildSizeRealY
     * @param buildSizeRealZ
     * @return
     * @title 通过尺寸查询是否可以下单的信息（下单）
     */
    public Result<List<MaterialDto>> getCanOrderMaterialByCondition(String buildSizeRealX, String buildSizeRealY, String buildSizeRealZ);

    /**
     * @param ids
     * @return
     */
    public Result<List<MaterialDto>> batchSelectByIds(List<Long> ids);

}
