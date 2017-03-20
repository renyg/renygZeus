package com.shining3d.zeus.dao;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.SupplierPrinterServiceDto;
import com.shining3d.zeus.entity.SupplierPrinterServiceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface SupplierPrinterServiceDao {

    int deleteByPK(Long id);

    int insertSelective(SupplierPrinterServiceEntity record);

    SupplierPrinterServiceDto selectByPK(Long id);

    int updateByPrimaryKeySelective(SupplierPrinterServiceEntity record);

    int getCountByCondition(SupplierPrinterServiceDto supplierPrinterServiceDto);

    List<SupplierPrinterServiceDto> getSupplierPrintServiceByCondition(@Param("supplierPrinterServiceDto") SupplierPrinterServiceDto supplierPrinterServiceDto,@Param("pageInfo") PageInfo pageInfo);

    List<SupplierPrinterServiceDto> getCanOrderSupplierPrintByCondition(@Param("adapterMaterialId") Long adapterMaterialId,@Param("color") String color);

    void updateStatus(SupplierPrinterServiceEntity supplierPrinterServiceEntity);

    void batchUpdateStatus(@Param("list") List<Long> list, @Param("status") String status);

}
