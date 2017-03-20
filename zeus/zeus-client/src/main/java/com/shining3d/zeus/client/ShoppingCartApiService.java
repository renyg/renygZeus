package com.shining3d.zeus.client;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.ShoppingCartDto;

import java.util.Date;
import java.util.List;

/**
 * Created by fe on 2016/12/20.
 */
public interface ShoppingCartApiService {
    /**
     * 添加商品到购物车
     * @param userId 用户id
     * @param skuId skuId
     * @param num 数量
     * @return
     */
    public Result<Long> saveSkuToShoppingCart(String userId,Long skuId,Integer num);
    /**
     * 购物车列表
     * @param userId 用户id
     * @return
     */
    public Result<List<ShoppingCartDto>> queryMyAllShoppingCart(String userId);
    /**
     * 删除购物车商品
     * @param skuId skuId
     * @param userId 用户id
     * @return
     */
    public Result<Boolean> deleteSkuFromShoppingCart(Long skuId,String userId);
    /**
     * 更新购物车数量
     * @param userId 用户id
     * @param skuId skuId
     * @param num 数量
     * @return
     */
    public Result<Boolean> updateShoppingCartSkuNum(String userId,Long skuId,Integer num);
    /**
     * 清空用户购物车
     * @param userId 用户id
     * @return
     */
    public Result<Boolean> clearShoppingCart(String userId);

    /**
     * 批量清空指定商品
     * @param userId
     * @param skuIdList
     * @return
     */
    public Result<Boolean> clearShoppingCart(String userId,List<Long> skuIdList);






}
