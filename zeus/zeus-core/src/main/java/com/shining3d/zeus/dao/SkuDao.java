package com.shining3d.zeus.dao;

import com.shining3d.zeus.client.dto.SkuDto;
import com.shining3d.zeus.entity.SkuEntity;

import java.util.List;

public interface SkuDao {
    int deleteByPrimaryKey(Long id);

    int insert(SkuEntity record);

    int insertSelective(SkuEntity record);

    SkuEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkuEntity record);

    int updateByProductIdSelective(SkuEntity record);

    int updateByPrimaryKey(SkuEntity record);

    List<SkuEntity> querySkuListByProductId(Long productId);

    int batchSaveSku(List<SkuEntity> skuEntityList);

    int removeSkuByProductId(Long productId);

    List<SkuEntity> queryBySkuIdList(List<Long> skuIdList);

    List<SkuEntity> queryByProductIdList(List<Long> productIdList);
}