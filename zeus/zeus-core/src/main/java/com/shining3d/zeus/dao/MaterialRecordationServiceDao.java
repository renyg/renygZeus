package com.shining3d.zeus.dao;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.MaterialRecordationServiceDto;
import com.shining3d.zeus.client.dto.query.MaterialAttachmentQueryDto;
import com.shining3d.zeus.entity.MaterialRecordationServiceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialRecordationServiceDao {


    int batchInsert(List<MaterialRecordationServiceEntity> record);

    void update(@Param("dto") MaterialRecordationServiceDto dto);

    void deleteById(@Param("list") List<MaterialRecordationServiceDto> list);

    int getCountMaterialRecordationCondition(@Param("materialRecordationServiceDto") MaterialRecordationServiceDto materialRecordationServiceDto);

    List<MaterialRecordationServiceDto> getMaterialRecordationCondition(@Param("materialRecordationServiceDto") MaterialRecordationServiceDto materialRecordationServiceDto, @Param("pageInfo") PageInfo pageInfo);

    /**
     * 材料附件权限相关
     * @param queryDto
     * @return
     */
    int countPermissionMaterialAttachment(MaterialAttachmentQueryDto queryDto);

}