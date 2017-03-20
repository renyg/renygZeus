package com.shining3d.zeus.dao;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.DeviceSupplierDto;
import com.shining3d.zeus.entity.DeviceSupplierEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceSupplierDao {
    
    int insertSelective(DeviceSupplierEntity record);

    DeviceSupplierDto selectByName(String name);

    int updateByPrimaryKeySelective(DeviceSupplierEntity record);

    void deleteBatchByIds(@Param("ids") List<Long> ids);

    int countDeviceSupplierByCondition(DeviceSupplierDto deviceSupplierDto);

    List<DeviceSupplierDto> queryDeviceSupplierByCondition(@Param("deviceSupplierDto") DeviceSupplierDto deviceSupplierDto,@Param("pageInfo") PageInfo pageInfo);
}
