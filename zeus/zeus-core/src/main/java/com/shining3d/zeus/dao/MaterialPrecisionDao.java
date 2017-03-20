package com.shining3d.zeus.dao;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.MaterialDto;
import com.shining3d.zeus.client.dto.MaterialPrecisionDto;
import com.shining3d.zeus.entity.MaterialEntity;
import com.shining3d.zeus.entity.MaterialPrecisionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialPrecisionDao {


    int batchInset(List<MaterialPrecisionEntity> list);

    int inset(MaterialPrecisionEntity entity);

    int update(MaterialPrecisionEntity entity);

    List<MaterialPrecisionDto> getPrecisionByClassifyIdAndNameEn(@Param("nameEn") String nameEn,@Param("classifyId") String classifyId);

    List<MaterialPrecisionDto> getPrecisionByCondition(MaterialPrecisionDto dto);

    void deleteByObjectId(Long id);

    void deleteByObjectIds(@Param("ids") List<String> ids);
}
