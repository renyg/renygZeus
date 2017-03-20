package com.shining3d.zeus.dao;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.DeviceModelDto;
import com.shining3d.zeus.entity.DeviceModelEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface DeviceModelDao {

    int insertSelective(DeviceModelEntity record);

    int updateByPrimaryKeySelective(DeviceModelEntity record);

    int getCountDeviceModel(DeviceModelDto deviceModelDto);
    //提供给编辑页面使用，方便返回一个设备型号下多个材料
    List<DeviceModelDto> getDeviceModel(@Param("deviceModelDto") DeviceModelDto deviceModelDto,@Param("pageInfo") PageInfo pageInfo);
   //提供查询列表使用
    List<DeviceModelDto> getDeviceModelList(@Param("deviceModelDto") DeviceModelDto deviceModelDto,@Param("pageInfo") PageInfo pageInfo);

    int getCountDeviceModelList(@Param("deviceModelDto") DeviceModelDto deviceModelDto);

    void batchUpdateModelStatus(@Param("list") List<Long> list, @Param("status") String status);

    void batchDeleteByIds(@Param("ids") List<Long> ids);

    List<DeviceModelDto> getDeviceModelByCondition(@Param("modelName") String modelName,@Param("supplierId") Long supplierId);
}
