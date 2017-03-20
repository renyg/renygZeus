package com.shining3d.zeus.dao;

import com.shining3d.zeus.entity.CategoryEntity;

import java.util.List;

public interface CategoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(CategoryEntity record);

    int insertSelective(CategoryEntity record);

    CategoryEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CategoryEntity record);

    int updateByPrimaryKey(CategoryEntity record);

    CategoryEntity queryByCode(String code);

    List<CategoryEntity> queryByCategoryIdList(List<Long> categoryIdList);
}