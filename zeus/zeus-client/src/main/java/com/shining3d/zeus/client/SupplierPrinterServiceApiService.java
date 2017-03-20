package com.shining3d.zeus.client;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.MaterialRecordationServiceDto;
import com.shining3d.zeus.client.dto.SupplierPrinterServiceDto;

import java.util.List;
import java.util.Set;

/**
 * Created by renyg on 2016/12/21.
 */
public interface SupplierPrinterServiceApiService {
    /**
     * @param id
     * @return
     * @title 删除服务商备案信息
     */
    public Result<Boolean> deleteById(Long id);

    /**
     * @param supplierPrinterServiceDto
     * @return
     * @title 插入服务商备案信息
     */
    public Result<SupplierPrinterServiceDto> insert(SupplierPrinterServiceDto supplierPrinterServiceDto);


    /**
     * @param supplierPrinterServiceDto
     * @return
     * @title 插入服务商备案信息
     */
    public Result<SupplierPrinterServiceDto> update(SupplierPrinterServiceDto supplierPrinterServiceDto);

    /**
     * 修改服务商备案信息
     * @param list
     * @param status
     * @return
     */
    public Result<SupplierPrinterServiceDto> batchUpdateStatus(List<Long> list, String status);

    /**
     * @param supplierPrinterServiceDto
     * @param pageInfo
     * @return
     * @title 查询服务商备案信息
     */
    public PageResult<SupplierPrinterServiceDto> getSupplierPrintServiceByCondition(SupplierPrinterServiceDto supplierPrinterServiceDto, PageInfo pageInfo);

    /**
     * 提供下单页面使用（点击选择颜色和精度）
     *
     * @param adapterMaterialId
     * @param color
     * @return
     */
    public Result<Set> getCanOrderSupplierPrintByCondition(Long adapterMaterialId, String color);
}
