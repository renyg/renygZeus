package com.shining3d.zeus.repository;

import com.shining3d.common.Assert;
import com.shining3d.common.BeanHelper;
import com.shining3d.common.DecimalHelper;
import com.shining3d.zeus.ZeusConfig;
import com.shining3d.zeus.client.dto.PackageProductDto;
import com.shining3d.zeus.client.dto.ShoppingCartDto;
import com.shining3d.zeus.client.dto.ShoppingCartPackageProductDto;
import com.shining3d.zeus.client.dto.SkuDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.*;
import com.shining3d.zeus.entity.*;
import com.shining3d.zeus.enums.ProductTypeEnum;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by fe on 2016/12/23.
 */
@Repository
public class ShoppingCartRepository {

    public static final Logger logger = LoggerFactory.getLogger(ShoppingCartRepository.class);

    @Resource
    private ShoppingCartDao shoppingCartDao;

    @Resource
    private SkuDao skuDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private ZeusConfig zeusConfig;

    @Resource
    private ProductPackageRelationDao productPackageRelationDao;


    public Long saveSkuToShoppingCart(String userId,Long skuId,Integer num) throws BizException,RepositoryException{
        try {

            Assert.assertNotNull(Arrays.asList(userId,skuId,num), BizConstant.COMMON_PARAM_ERROR,"参数错误!");
            Assert.assertNotMinusAndZero(num,"num必须大于0!");
            if (num.compareTo(999) == 1)
                throw new BizException(BizConstant.COMMON_BIZ_ERROR,"最大数量不能超过999!");


            SkuEntity skuEntity = skuDao.selectByPrimaryKey(skuId);

            Assert.assertNotNull(skuEntity,BizConstant.COMMON_BIZ_ERROR,"sku不存在！");

            List<ShoppingCartEntity> shoppingCartEntityList = shoppingCartDao.queryAllShoppingCartByUserId(userId);
            if (CollectionUtils.isNotEmpty(shoppingCartEntityList) && shoppingCartEntityList.size() >= zeusConfig.getShoppingCartSkuMaxNum())
                throw new BizException(BizConstant.SHOPPING_CART_SKU_NUM_OVER_FLOW,"购物车数量超出上限数量:" + zeusConfig.getShoppingCartSkuMaxNum() + ",无法继续添加!");

            ShoppingCartEntity shoppingCartEntity = shoppingCartDao.selectByParam(userId,skuId);
            if (shoppingCartEntity == null) {
                shoppingCartEntity = new ShoppingCartEntity();
                shoppingCartEntity.setCreator("sys");
                shoppingCartEntity.setGmtCreate(new Date());
                shoppingCartEntity.setModifier("sys");
                shoppingCartEntity.setGmtModified(new Date());
                shoppingCartEntity.setIsDeleted("n");
                shoppingCartEntity.setUserId(userId);
                shoppingCartEntity.setSkuId(skuId);
                shoppingCartEntity.setNum(num);
                shoppingCartEntity.setProductId(skuEntity.getProductId());
                shoppingCartDao.insertSelective(shoppingCartEntity);
            } else {
                Integer total = num + shoppingCartEntity.getNum();
                if (total.compareTo(999) == 1)
                    throw new BizException(BizConstant.COMMON_BIZ_ERROR,"最大数量不能超过999!");

                shoppingCartDao.updateShoppingCartSkuIncrementNum(userId,skuId,num);
            }


            return shoppingCartEntity.getId();
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("saveSkuToShoppingCart error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public List<ShoppingCartDto> queryMyAllShoppingCart(String userId) throws BizException,RepositoryException {
        try {
            Assert.assertNotNull(userId, BizConstant.COMMON_PARAM_ERROR,"参数错误!");

            List<ShoppingCartEntity> shoppingCartEntityList = shoppingCartDao.queryAllShoppingCartByUserId(userId);
            List<ShoppingCartDto> shoppingCartDtoList = new LinkedList<ShoppingCartDto>();
            if (CollectionUtils.isNotEmpty(shoppingCartEntityList)) {
                List<Long> productIdList = new ArrayList<Long>();
                List<Long> packageProductIdList = new ArrayList<Long>();
                List<Long> skuIdList = new ArrayList<Long>();
                List<Long> categoryIdList = new ArrayList<Long>();
                shoppingCartEntityList.forEach(item -> {
                    Long productId = item.getProductId();
                    Long skuId = item.getSkuId();
                    productIdList.add(productId);
                    skuIdList.add(skuId);
                });

                //TODO 获取product信息
                List<ProductEntity> productEntityList = productDao.queryAllProductByProductIdList(productIdList);
                productEntityList.forEach(item -> {
                    Long categoryId = item.getCategoryId();
                    categoryIdList.add(categoryId);

                    if (item.getProductType().equals(ProductTypeEnum.PACKAGE.getCode())) packageProductIdList.add(item.getId());
                });
                Map<Long,ProductEntity> productEntityMap = mappingByProductId(productEntityList);

                //TODO 获取套餐商品下对应的商品信息
                List<ProductPackageRelationEntity> productPackageRelationEntityList = null;
                if (CollectionUtils.isNotEmpty(packageProductIdList)) productPackageRelationEntityList = productPackageRelationDao.queryProductPackageRelationListByPackageProductIds(packageProductIdList);

                List<Long> packageProductIds = new ArrayList<Long>();
                if (CollectionUtils.isNotEmpty(productPackageRelationEntityList)) {
                    productPackageRelationEntityList.forEach(item -> {
                        packageProductIds.add(item.getProductId());
                    });
                }
                List<ProductEntity> packageProductList = null;
                if (CollectionUtils.isNotEmpty(packageProductIds)) packageProductList  = productDao.queryAllProductByProductIdList(packageProductIds);
                Map<Long,ProductEntity> packageProductMap = mappingByProductId(packageProductList);
                Map<Long,List<ProductPackageRelationEntity>> productPackageRelationMap = mappingProductPackageRelationEntityList(productPackageRelationEntityList);

                //TODO 获取sku信息
                List<SkuEntity> skuEntityList = skuDao.queryBySkuIdList(skuIdList);
                Map<Long,SkuEntity> skuEntityMap = mappingBySkuId(skuEntityList);


                //TODO 获取类目信息
                List<CategoryEntity> categoryEntityList = null;
                if (CollectionUtils.isNotEmpty(categoryIdList))  categoryEntityList = categoryDao.queryByCategoryIdList(categoryIdList);
                Map<Long,CategoryEntity> categoryEntityMap = mappingByCategoryId(categoryEntityList);

                shoppingCartEntityList.forEach(item -> {
                    ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
                    BeanHelper.copyProperties(shoppingCartDto,item);
                    shoppingCartDto.setShoppingCartId(item.getId());

                    Long productId = shoppingCartDto.getProductId();
                    ProductEntity productEntity = productEntityMap.get(productId);
                    if (productEntity != null) {
                        shoppingCartDto.setName(productEntity.getName());
                        shoppingCartDto.setMainPicId(productEntity.getMainPicId());
                        Long categoryId = productEntity.getCategoryId();
                        CategoryEntity categoryEntity = categoryEntityMap.get(categoryId);
                        if (categoryEntity != null) shoppingCartDto.setCategoryName(categoryEntity.getName());

                        shoppingCartDto.setProductType(productEntity.getProductType());

                        shoppingCartDto.setState(productEntity.getState());
                        if (productEntity.getProductType().equals(ProductTypeEnum.DATA.getCode()))
                            shoppingCartDto.setDataSharePrice(productEntity.getDataSharePrice());

                        //TODO 如果是套餐商品
                        if (productEntity.getProductType().equals(ProductTypeEnum.PACKAGE.getCode())) {
                            List<ProductPackageRelationEntity> packageRelationEntityList = productPackageRelationMap.get(productEntity.getId());

                            if (CollectionUtils.isNotEmpty(packageRelationEntityList)) {
                                List<ShoppingCartPackageProductDto> shoppingCartPackageProductDtoList = new ArrayList<ShoppingCartPackageProductDto>();
                                packageRelationEntityList.forEach(packageRelationEntity -> {
                                    ShoppingCartPackageProductDto shoppingCartPackageProductDto = new ShoppingCartPackageProductDto();

                                    shoppingCartPackageProductDto.setProductId(packageRelationEntity.getProductId());
                                    shoppingCartPackageProductDto.setNum(packageRelationEntity.getNum());
                                    ProductEntity pe = packageProductMap.get(packageRelationEntity.getProductId());

                                    if (pe != null) {
                                        shoppingCartPackageProductDto.setName(pe.getName());
                                        shoppingCartPackageProductDto.setMainPicId(pe.getMainPicId());
                                        shoppingCartPackageProductDtoList.add(shoppingCartPackageProductDto);
                                    }
                                });
                                shoppingCartDto.setShoppingCartPackageProductDtoList(shoppingCartPackageProductDtoList);
                            }
                        }
                    }

                    Long skuId = shoppingCartDto.getSkuId();
                    SkuEntity skuEntity = skuEntityMap.get(skuId);
                    if (skuEntity != null) {
                        shoppingCartDto.setPropKey(skuEntity.getPropKey());
                        shoppingCartDto.setPropName(skuEntity.getPropName());
                        shoppingCartDto.setOriginalPrice(skuEntity.getOriginalPrice());
                        shoppingCartDto.setPromotionPrice(skuEntity.getPromotionPrice());
                        shoppingCartDto.setSkuIsDeleted(skuEntity.getIsDeleted());
                        shoppingCartDto.setPromotionStartTime(skuEntity.getPromotionStartTime());
                        shoppingCartDto.setPromotionEndTime(skuEntity.getPromotionEndTime());
                    }
                    shoppingCartDtoList.add(shoppingCartDto);
                });
            }
            return shoppingCartDtoList;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryMyAllShoppingCart error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    private Map<Long,ProductEntity> mappingByProductId(List<ProductEntity> productEntityList) {
        Map<Long,ProductEntity> productEntityMap = new HashMap<Long,ProductEntity>();
        Optional.ofNullable(productEntityList).ifPresent(list -> {
            list.forEach(item -> {
                Long id = item.getId();
                productEntityMap.put(id,item);
            });
        });
        return productEntityMap;
    }

    private Map<Long,List<ProductPackageRelationEntity>> mappingProductPackageRelationEntityList(List<ProductPackageRelationEntity> productPackageRelationEntityList) {
        Map<Long,List<ProductPackageRelationEntity>> map = new HashMap<Long,List<ProductPackageRelationEntity>>();
        Optional.ofNullable(productPackageRelationEntityList).ifPresent(list -> {
            list.forEach(item -> {
                List<ProductPackageRelationEntity> packageProductDtoList = null;
                if (!map.containsKey(item.getPackageProductId()))
                    packageProductDtoList = new ArrayList<ProductPackageRelationEntity>();
                else
                    packageProductDtoList = map.get(item.getPackageProductId());

                packageProductDtoList.add(item);
                map.put(item.getPackageProductId(),packageProductDtoList);
            });

        });
        return map;
    }

    private Map<Long,SkuEntity> mappingBySkuId(List<SkuEntity> skuEntityList) {
        Map<Long,SkuEntity> skuEntityMap = new HashMap<Long,SkuEntity>();
        Optional.ofNullable(skuEntityList).ifPresent(list -> {
            list.forEach(item -> {
                Long id = item.getId();
                skuEntityMap.put(id,item);
            });
        });
        return skuEntityMap;
    }

    private Map<Long,CategoryEntity> mappingByCategoryId(List<CategoryEntity> categoryEntityList) {
        Map<Long,CategoryEntity> categoryEntityMap = new HashMap<Long,CategoryEntity>();
        Optional.ofNullable(categoryEntityList).ifPresent(list -> {
            list.forEach(item -> {
                Long id = item.getId();
                categoryEntityMap.put(id,item);
            });
        });
        return categoryEntityMap;
    }

    public Boolean deleteSkuFromShoppingCart(Long skuId,String userId) throws BizException,RepositoryException  {
        try {

            Assert.assertNotNull(Arrays.asList(skuId,userId), BizConstant.COMMON_PARAM_ERROR,"参数错误!");
            int result = shoppingCartDao.removeSkuFromShoppingCartByParam(skuId,userId);
            return result > 0;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("deleteProductFromShoppingCart error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Boolean updateShoppingCartSkuNum(String userId,Long skuId,Integer num) throws BizException,RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(userId,skuId,num), "参数错误!");

            SkuEntity skuEntity = skuDao.selectByPrimaryKey(skuId);
            Assert.assertNotNull(skuEntity,BizConstant.COMMON_BIZ_ERROR,"sku不存在！");
            Assert.assertNotZero(num,"num不能为零!");

            ShoppingCartEntity shoppingCartEntity = shoppingCartDao.selectByParam(userId,skuId);
            Assert.assertNotNull(shoppingCartEntity,"异常请求!");

            //TODO 如果是扣减逻辑 扣减数量不能大于存储的数量
            if (num.compareTo(0) == -1 &&  Math.abs(num) >= shoppingCartEntity.getNum().intValue())
                throw new BizException(BizConstant.COMMON_BIZ_ERROR,"扣减数量异常!");

            if (num.compareTo(999) == 1)
                throw new BizException(BizConstant.COMMON_BIZ_ERROR,"最大数量不能超过999!");

            int result = shoppingCartDao.updateShoppingCartSkuNum(userId,skuId,num);
            return result > 0;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("updateShoppingCartSkuNum error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Boolean clearShoppingCart(String userId) throws BizException,RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(userId), BizConstant.COMMON_PARAM_ERROR,"参数错误!");
            int result = shoppingCartDao.removeSkuFromShoppingCartByParam(null,userId);
            return result > 0;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("clearShoppingCart error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Boolean clearShoppingCart(String userId,List<Long> skuIdList) throws BizException,RepositoryException {
        try {
            Assert.assertNotNull(userId, BizConstant.COMMON_PARAM_ERROR,"参数错误!");
            Assert.assertNotNull(skuIdList, BizConstant.COMMON_PARAM_ERROR,"skuIdList不能为空!");
            int result = shoppingCartDao.batchRemoveSkuFromShoppingCart(skuIdList,userId);
            return result > 0;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("clearShoppingCart error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }
}
