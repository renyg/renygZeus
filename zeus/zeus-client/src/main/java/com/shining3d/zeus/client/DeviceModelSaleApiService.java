package com.shining3d.zeus.client;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.DeviceModelDto;
import com.shining3d.zeus.client.dto.ModelMaterialRelationDto;

import java.util.List;

/**
 * Created by renyg on 2016/12/21.
 */

public interface DeviceModelSaleApiService {
    /***
     * @title 批量删除设备信息并且要维护设备材料的关系
     * @param ids
     * @return
     */
    public Result<Boolean> batchDeleteByIds(String ids);

    /**
     * @param ids
     * @param status
     * @return
     * @title 批量修改设备状态
     */
    public Result<Boolean> batchUpdateModelStatus(String ids, String status);

    /**
     * @param deviceModelDto
     * @param list
     * @return
     * @title 新增设备信息并且要维护设备材料的关系
     */
    public Result<DeviceModelDto> insert(DeviceModelDto deviceModelDto, List<ModelMaterialRelationDto> list);

    /**
     * @param deviceModelDto
     * @param list
     * @return
     * @title 更新设备信息并且要维护设备材料的关系
     */
    public Result<DeviceModelDto> update(DeviceModelDto deviceModelDto, List<ModelMaterialRelationDto> list);

    /**
     * @param deviceModelDto
     * @param pageInfo
     * @return
     * @title 查询设备信息
     */
    public PageResult<DeviceModelDto> getDeviceModel(DeviceModelDto deviceModelDto, PageInfo pageInfo);


    /**
     * @param deviceModelDto
     * @param pageInfo
     * @return
     * @title 查询设备信息
     */
    public PageResult<DeviceModelDto> getDeviceModelList(DeviceModelDto deviceModelDto, PageInfo pageInfo);

    /**
     * @param userId
     * @param modelName
     * @param supplierId
     * @return
     * @title 提供给设备供应商添加显示的页面
     */
    public PageResult<DeviceModelDto> getDeviceModelByCondition(String userId, String modelName, Long supplierId);

    /**
     *
     * @param precisionId
     * @param marterialId
     * @return
     */
    public PageResult<ModelMaterialRelationDto> getMateialRealtionByPrecisionIdAndMaterialId(Long precisionId , Long marterialId);




}
