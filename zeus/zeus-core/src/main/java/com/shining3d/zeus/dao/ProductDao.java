package com.shining3d.zeus.dao;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProductEntity record);

    int insertSelective(ProductEntity record);

    ProductEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductEntity record);

    int updateByPrimaryKey(ProductEntity record);

    int updateMyDataByParam(@Param("productId") Long productId,@Param("userId") String userId,@Param("paramMap") Map<String,Object> setParamMap);

    List<ProductEntity> queryAllProductPageByParam(@Param("paramMap") Map<String,Object> paramMap, @Param("pageInfo") PageInfo pageInfo);

    List<ProductEntity> queryAllProductByCategoryIdList(@Param("list") List<Long> categoryIdList,@Param("state") String state);

    Integer queryAllProductCountByParam(@Param("paramMap") Map<String,Object> paramMap);

    List<ProductEntity> queryAllProductByProductIdList(List<Long> productIdList);

    ProductEntity queryByDataId(Long dataId);

    Integer queryCountByName(String productName);
}