package com.shining3d.zeus.dao;

import com.shining3d.zeus.entity.ProductEntity;
import com.shining3d.zeus.entity.ProductPackageRelationEntity;

import java.util.List;

public interface ProductPackageRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProductPackageRelationEntity record);

    int insertSelective(ProductPackageRelationEntity record);

    ProductPackageRelationEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductPackageRelationEntity record);

    int updateByPrimaryKey(ProductPackageRelationEntity record);

    List<ProductPackageRelationEntity> queryProductPackageRelationListByPackageProductId(Long packageProductId);

    List<ProductPackageRelationEntity> queryProductPackageRelationListByPackageProductIds(List<Long> packageProductId);

    int batchSaveProductPackageRelation(List<ProductPackageRelationEntity> productPackageRelationEntityList);

    int removeProductPackageRelationByPackageProductId(Long packageProductId);
}