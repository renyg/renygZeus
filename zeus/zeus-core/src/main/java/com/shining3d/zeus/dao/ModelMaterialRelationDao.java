package com.shining3d.zeus.dao;

import com.shining3d.zeus.client.dto.ModelMaterialRelationDto;
import com.shining3d.zeus.entity.ModelMaterialRelationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelMaterialRelationDao {

    void batchInsert(@Param("list") List<ModelMaterialRelationEntity> list);


    void batchUpdate(@Param("entityList") List<ModelMaterialRelationEntity> entityList);


    void batchDeleteByDeviceId(@Param("list") List<Long> list);


    List<ModelMaterialRelationDto> getMateialRealtionByPrecisionIdAndMaterialId(@Param("precisionId") Long precisionId ,@Param("marterialId") Long marterialId);
}
