package com.shining3d.zeus.dao;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.MaterialDto;
import com.shining3d.zeus.entity.MaterialEntity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MaterialDao {

    int deleteByPK(@Param("ids") List<String> ids);

    int insertSelective(MaterialEntity record);

    MaterialDto selectByNameCn(String NameCn);

    int updateByPrimaryKeySelective(MaterialEntity record);

    void batchOperateStatus(@Param("status") String status,@Param("ids") List<String> ids);

    List<MaterialDto> selectByConditions(@Param("materialDto") MaterialDto materialDto, @Param("pageInfo") PageInfo pageInfo);

    int getCount(MaterialDto materialDto);

    List<MaterialDto> getListByCondition(String status,String name ,String classifyId);

    /**
     * 提供给订单下单页面使用
     * @param buildSizeRealX
     * @param buildSizeRealY
     * @param buildSizeRealZ
     * @return
     */
    List<MaterialDto> getCanOrderMaterialByCondition(@Param("buildSizeRealX") BigDecimal buildSizeRealX, @Param("buildSizeRealY") BigDecimal buildSizeRealY, @Param("buildSizeRealZ") BigDecimal buildSizeRealZ);


    List<MaterialDto> batchSelectByIds(@Param("ids") List<Long> list);
}
