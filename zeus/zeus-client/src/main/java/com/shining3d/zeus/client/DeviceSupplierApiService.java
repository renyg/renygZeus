package com.shining3d.zeus.client;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.DeviceSupplierDto;
import com.shining3d.zeus.client.dto.SupplierPrinterServiceDto;

/**
 * Created by renyg on 2016/12/21.
 */
public interface DeviceSupplierApiService {

    /**
     * @param ids
     * @return
     * @title 删除供应商（新增的时候）id需要;分割
     */
    public Result<Boolean> deleteBatchByIds(String ids);

    /**
     * @param deviceSupplierDto
     * @return
     * @title 新增供应商
     */
    public Result<DeviceSupplierDto> insert(DeviceSupplierDto deviceSupplierDto);

    /**
     * @param deviceSupplierDto
     * @return
     * @title 修改供应商
     */
    public Result<DeviceSupplierDto> update(DeviceSupplierDto deviceSupplierDto);

    /**
     * @param deviceSupplierDto
     * @param pageInfo
     * @return
     * @title 提供页面列表信息查询
     */
    public PageResult<DeviceSupplierDto> queryDeviceSupplierByCondition(DeviceSupplierDto deviceSupplierDto, PageInfo pageInfo);

}
