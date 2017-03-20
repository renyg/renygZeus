package com.shining3d.zeus.dao;

import com.shining3d.zeus.entity.PropValueEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PropValueDao {
    int deleteByPrimaryKey(Long id);

    int insert(PropValueEntity record);

    int insertSelective(PropValueEntity record);

    PropValueEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PropValueEntity record);

    int updateByPrimaryKey(PropValueEntity record);

    int batchSavePropValue(List<PropValueEntity> propValueEntityList);

    PropValueEntity queryByPropValueName(String propValueName);

    List<PropValueEntity> queryPropValueListByParam(@Param("categoryId") Long categoryId, @Param("propName") String propName);
}