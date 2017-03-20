package com.shining3d.zeus.dao;

import com.shining3d.zeus.entity.ShoppingCartEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartDao {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCartEntity record);

    int insertSelective(ShoppingCartEntity record);

    ShoppingCartEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCartEntity record);

    int updateByPrimaryKey(ShoppingCartEntity record);

    ShoppingCartEntity selectByParam(@Param("userId") String userId,@Param("skuId") Long skuId);

    List<ShoppingCartEntity> queryAllShoppingCartByUserId(String userId);

    int removeSkuFromShoppingCartByParam(@Param("skuId") Long skuId,@Param("userId") String userId);

    int batchRemoveSkuFromShoppingCart(@Param("skuIdList") List<Long> skuIdList,@Param("userId") String userId);

    int updateShoppingCartSkuNum(@Param("userId") String userId, @Param("skuId") Long skuId, @Param("num") Integer num);

    int updateShoppingCartSkuIncrementNum(@Param("userId") String userId, @Param("skuId") Long skuId, @Param("num") Integer num);

}