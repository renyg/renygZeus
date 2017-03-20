package com.shining3d.zeus.dao;

import com.shining3d.zeus.entity.PropEntity;

import java.util.List;

public interface PropDao {
    int deleteByPrimaryKey(Long id);

    int insert(PropEntity record);

    int insertSelective(PropEntity record);

    PropEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PropEntity record);

    int updateByPrimaryKey(PropEntity record);

    List<PropEntity> queryAllPropEntityByCategoryId(Long categoryId);
}