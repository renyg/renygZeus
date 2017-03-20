package com.shining3d.zeus.client;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.DataProductDto;
import com.shining3d.zeus.client.dto.PackageProductDto;
import com.shining3d.zeus.client.dto.ProductDto;
import com.shining3d.zeus.client.dto.SingleProductDto;
import com.shining3d.zeus.client.dto.query.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fe on 2016/12/20.
 */
public interface ProductApiService {

    /**
     * 商品上下架接口 online上架/offline下架
     * @param productId 商品id
     * @param state 状态
     * @return
     */
    public Result<Boolean> updateProductStateByBops(Long productId,String state);
    /**
     * 后台管理员删除商品接口
     * @param productId 商品id
     * @return
     */
    public Result<Boolean> deleteProductByBops(Long productId);
    /**
     * 后台保存数据商品接口
     * @param dataProductDto 数据商品
     * @return
     */
    public Result<Long> saveDataProductByBops(DataProductDto dataProductDto);
    /**
     * 后台查询商品列表数据接口
     * @return
     */
    public PageResult<DataProductDto> queryDataProductListByBops(DataProductBopsQueryDto dataProductBopsQueryDto, PageInfo pageInfo);
    /**
     * 后台查询数据商品详情接口
     * @param productId 商品id
     * @return
     */
    public Result<DataProductDto> queryDataProductDetailByBops(Long productId);
    /**
     * 前台根据dataId获取dataProduct
     * @param dataId
     * @return
     */
    public Result<DataProductDto> queryDataProductDetailByDataId(Long dataId);
    /**
     * 后台修改数据商品接口
     * @param dataProductDto 数据商品
     * @return
     */
    public Result<Boolean> updateDataProductByBops(DataProductDto dataProductDto);
    /**
     * 前台查询数据商品列表接口
     * @param dataProductQueryDto 查询条件
     * @return
     */
    public PageResult<DataProductDto> queryDataProductList(DataProductQueryDto dataProductQueryDto,PageInfo pageInfo);
    /**
     * 前台用户修改数据商品分润价格接口
     * @param productId 商品id
     * @param userId 用户id
     * @param sharePrice 分润价格
     * @return
     */
    public Result<Boolean> updateMyDataProduct(Long productId,String userId,BigDecimal sharePrice);
    /**
     * 前台用户删除数据商品接口
     * @param productId 商品id
     * @param userId 用户id
     * @return
     */
    public Result<Boolean> deleteMyDataProduct(Long productId,String userId);
    /**
     * 如果有分润价格  为前台用户生成数据商品
     * @param dataProductDto 数据商品
     * @return
     */
    public Result<Long> saveMyDataProduct(DataProductDto dataProductDto);
    /**
     * 后台保存单品商品(设备/材料)接口
     * @param singleProductDto
     * @return
     */
    public Result<Long> saveSingleProductByBops(SingleProductDto singleProductDto);
    /**
     * 后台查询单品商品列表接口
     * @param singleProductQueryDto 查询条件
     * @param pageInfo 分页
     * @return
     */
    public PageResult<SingleProductDto> querySingleProductListByBops(SingleProductQueryDto singleProductQueryDto, PageInfo pageInfo);
    /**
     * 后台查询单品数据详情接口
     * @param productId 商品id
     * @return
     */
    public Result<SingleProductDto> querySingleProductDetailByBops(Long productId);
    /**
     * 后台单品数据修改接口
     * @param singleProductDto 单品商品(设备/材料)
     * @return
     */
    public Result<Boolean> updateSingleProductByBops(SingleProductDto singleProductDto);
    /**
     * 后台保存套餐商品接口
     * @param packageProductDto 套餐商品
     * @return
     */
    public Result<Long> savePackageProductByBops(PackageProductDto packageProductDto);
    /**
     * 查询所有的单品商品列表接口
     * @return
     */
    public Result<List<SingleProductDto>> queryAllSingleProductList();
    /**
     * 查询所有套餐商品列表
     * @return
     */
    public Result<List<PackageProductDto>> queryAllPackageProductList();
    /**
     * 后台查询套餐商品列表接口
     * @param packageProductQueryDto 查询条件
     * @param pageInfo 分页参数
     * @return
     */
    public PageResult<PackageProductDto> queryPackageProductListByBops(PackageProductQueryDto packageProductQueryDto, PageInfo pageInfo);
    /**
     * 后台查询套餐数据详情接口
     * @param productId 商品id
     * @return
     */
    public Result<PackageProductDto> queryPackageProductDetailByBops(Long productId);
    /**
     * 后台套餐数据修改接口
     * @param packageProductDto 套餐商品
     * @return
     */
    public Result<Boolean> updatePackageProductByBops(PackageProductDto packageProductDto);

    /**
     * 根据productId获取product
     * @param productId 商品id
     * @return
     */
    public Result<ProductDto> queryProductByProductId(Long productId);
    /**
     * 后台套餐筛选 商品查询商品
     * @param productQueryDto 查询参数
     * @return
     */
    public PageResult<ProductDto> queryProductByBops(ProductQueryDto productQueryDto,PageInfo pageInfo);


}
