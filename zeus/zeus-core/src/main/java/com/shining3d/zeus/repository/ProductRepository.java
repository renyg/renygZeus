package com.shining3d.zeus.repository;

import com.shining3d.chaos.client.DataApiService;
import com.shining3d.chaos.client.dto.DataDto;
import com.shining3d.chaos.client.dto.DataModelDto;
import com.shining3d.common.Assert;
import com.shining3d.common.BeanHelper;
import com.shining3d.common.FastJson;
import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.ZeusConfig;
import com.shining3d.zeus.client.dto.*;
import com.shining3d.zeus.client.dto.query.*;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.constant.Constants;
import com.shining3d.zeus.dao.ProductDao;
import com.shining3d.zeus.dao.ProductPackageRelationDao;
import com.shining3d.zeus.dao.PropValueDao;
import com.shining3d.zeus.dao.SkuDao;
import com.shining3d.zeus.entity.ProductEntity;
import com.shining3d.zeus.entity.ProductPackageRelationEntity;
import com.shining3d.zeus.entity.PropValueEntity;
import com.shining3d.zeus.entity.SkuEntity;
import com.shining3d.zeus.enums.*;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.mq.MQHelper;
import com.shining3d.zeus.mq.message.ProductMessage;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.Op;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by fe on 2016/12/23.
 */
@Repository
public class ProductRepository {

    public static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Resource
    private ProductDao productDao;

    @Resource
    private SkuDao skuDao;

    @Resource
    private ZeusConfig zeusConfig;

    @Resource
    private DataApiService dataApiService;

    @Resource
    private ProductPackageRelationDao productPackageRelationDao;

    @Resource
    private PropValueDao propValueDao;

    @Resource
    private MQHelper mqHelper;

    public boolean updateProductStateByBops(Long productId, String state) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(productId,state));

            StateEnum stateEnum = StateEnum.fromCode(state);
            if (stateEnum == null) throw new BizException(BizConstant.COMMON_PARAM_ERROR, "state状态错误!");

            ProductEntity updateProductEntity = new ProductEntity();
            updateProductEntity.setId(productId);
            updateProductEntity.setState(state);
            updateProductEntity.setGmtModified(new Date());

            int result = productDao.updateByPrimaryKeySelective(updateProductEntity);

            return result > 0;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("updateProductStateByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }

    }

    public Boolean deleteProductByBops(Long productId) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(productId);
            ProductEntity productEntity = productDao.selectByPrimaryKey(productId);
            Assert.assertNotNull(productEntity,"商品不存在！");

            if (productEntity.getCategoryId().equals(zeusConfig.getDataProductCategoryId())) {
                Result<Boolean> dataResult = dataApiService.deleteDataByBops(productEntity.getDataId());
                if (!dataResult.isSuccess()) throw new BizException("数据服务异常!");
            }

            ProductEntity updateProductEntity = new ProductEntity();
            updateProductEntity.setId(productId);
            updateProductEntity.setIsDeleted("y");
            updateProductEntity.setGmtModified(new Date());

            int result = productDao.updateByPrimaryKeySelective(updateProductEntity);
            return result > 0;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("deleteProductByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Long saveDataProductByBops(DataProductDto dataProductDto) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(dataProductDto,dataProductDto.getUserId(),
                    dataProductDto.getName(),dataProductDto.getClassifyId(),dataProductDto.getLabel(),
                    dataProductDto.getDescription(),dataProductDto.getPublishTime(),dataProductDto.getSeoTitle(),
                    dataProductDto.getSeoKeyword(),dataProductDto.getSeoDescription(),
                    dataProductDto.getOnlineType(),dataProductDto.getOfflineType(),dataProductDto.getPublishTime()));

            Assert.assertNotNull(Arrays.asList(dataProductDto.getPicFileId(),dataProductDto.getMainPicId(),
                    dataProductDto.getDataModelProductDtoDtoList()),"上传文件缺失！");
            checkParam(dataProductDto.getOnlineType(),dataProductDto.getOnlineTime(),dataProductDto.getOfflineType(),dataProductDto.getOfflineTime());
            checkSkuList(dataProductDto.getSkuDto());

            Long id = saveData(dataProductDto, false);
            sendToMq(id,dataProductDto.getOnlineType(),dataProductDto.getOnlineTime(),dataProductDto.getOfflineType(),dataProductDto.getOfflineTime());
            return id;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("saveDataProductByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public List<DataProductDto> queryDataProductListByBops(DataProductBopsQueryDto dataProductBopsQueryDto, PageInfo pageInfo) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(dataProductBopsQueryDto,pageInfo));

            Map<String, Object> paramMap = buildQueryMap(dataProductBopsQueryDto);
            return queryDataProductByParam(paramMap, pageInfo);
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryDataProductListByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public DataProductDto queryDataProductDetailByBops(Long productId) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(productId);

            ProductEntity productEntity = productDao.selectByPrimaryKey(productId);
            DataProductDto dataProductDto = convertToDataProductDto(productEntity,false);

            return dataProductDto;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryDataProductDetail error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Boolean updateDataProductByBops(DataProductDto dataProductDto) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(dataProductDto,dataProductDto.getProductId()));

            checkParam(dataProductDto.getOnlineType(),dataProductDto.getOnlineTime(),dataProductDto.getOfflineType(),dataProductDto.getOfflineTime());
            checkSkuList(dataProductDto.getSkuDto());

            ProductEntity productEntity = productDao.selectByPrimaryKey(dataProductDto.getProductId());
            Assert.assertNotNull(productEntity,"参数异常,无法找到商品!");

            ProductEntity updateProductEntity = new ProductEntity();
            BeanUtils.copyProperties(updateProductEntity, dataProductDto);
            updateProductEntity.setId(dataProductDto.getProductId());
            updateProductEntity.setGmtModified(new Date());
            productDao.updateByPrimaryKeySelective(updateProductEntity);

            DataDto dataDto = new DataDto();
            dataDto.setDataId(productEntity.getDataId());
            dataDto.setUserId(dataProductDto.getUserId());
            dataDto.setName(dataProductDto.getName());
            dataDto.setClassifyId(dataProductDto.getClassifyId());
            dataDto.setAuthType("dataPrintShare");
            dataDto.setPicFileId(dataProductDto.getPicFileId());
            dataDto.setMainPicId(dataProductDto.getMainPicId());
            dataDto.setLabel(dataProductDto.getLabel());
            dataDto.setDescription(dataProductDto.getDescription());
            dataDto.setPublishTime(dataProductDto.getPublishTime());
            dataDto.setIsBest("1");
            dataDto.setSeoTitle(dataProductDto.getSeoTitle());
            dataDto.setSeoKeyword(dataProductDto.getSeoKeyword());
            dataDto.setSeoDescription(dataProductDto.getSeoDescription());
            dataDto.setArticleId(dataProductDto.getArticleId());

            List<DataModelDto> dataModelDtoList = convertToDataModel(dataProductDto.getDataModelProductDtoDtoList());
            dataDto.setDataModelDtoList(dataModelDtoList);
            Result<Boolean> result = dataApiService.updateData(dataDto);

            if (!result.isSuccess()) throw new BizException(BizConstant.COMMON_BIZ_ERROR,"数据服务异常," + result.getErrMsg());

            updateSkuList(dataProductDto.getSkuDto(), dataProductDto.getProductId(), zeusConfig.getDataProductCategoryId());

            //TODO 如果延时时间有修改,则往mq发送延时消息
            if (dataProductDto.getOnlineType().equals(OnlineTypeEnum.DELAY.getCode())  && !dataProductDto.getOnlineTime().equals(productEntity.getOnlineTime())) {
                sendOnlineDataToMq(dataProductDto.getProductId(),dataProductDto.getOnlineTime());
            }
            if (dataProductDto.getOfflineType().equals(OffLineTypeEnum.DELAY.getCode()) && !dataProductDto.getOfflineTime().equals(productEntity.getOfflineTime())) {
                sendOffLineDataToMq(dataProductDto.getProductId(),dataProductDto.getOfflineTime());
            }


            return true;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("updateDataProductByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    private List<DataModelDto> convertToDataModel(List<DataModelProductDto> dataProductDtoList) {
        List<DataModelDto> dataModelDtoList = new ArrayList<DataModelDto>();
        Optional.ofNullable(dataProductDtoList).ifPresent(list -> {
            list.forEach(item -> {
                DataModelDto dataModelDto = new DataModelDto();
                BeanHelper.copyProperties(dataModelDto, item);
                dataModelDto.setStatus(dataModelDto.getStatus());
                dataModelDtoList.add(dataModelDto);
            });
        });
        return dataModelDtoList;
    }

    public List<DataProductDto> queryDataProductList(DataProductQueryDto dataProductQueryDto, PageInfo pageInfo) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(dataProductQueryDto,pageInfo));

            Map<String, Object> paramMap = buildQueryMap(dataProductQueryDto);
            return queryDataProductByParam(paramMap, pageInfo);
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryDataProductList error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    private List<DataProductDto> queryDataProductByParam(Map<String, Object> paramMap, PageInfo pageInfo) throws BizException, RepositoryException {
        try {

            int total = productDao.queryAllProductCountByParam(paramMap);
            pageInfo.setRowCount(total);

            List<DataProductDto> dataProductDtoList = new ArrayList<DataProductDto>();
            List<ProductEntity> productEntityList = productDao.queryAllProductPageByParam(paramMap, pageInfo);

            Optional.ofNullable(productEntityList).ifPresent(list -> {
                list.forEach(item -> {
                    DataProductDto dataProductDto = new DataProductDto();
                    BeanHelper.copyProperties(dataProductDto, item);
                    dataProductDto.setProductId(item.getId());
                    dataProductDtoList.add(dataProductDto);

                });

            });
            return dataProductDtoList;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryDataProductByParam error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Boolean updateMyDataProduct(Long productId, String userId, BigDecimal bigDecimal) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(productId,userId,bigDecimal));

            Map<String, Object> setParamMap = new HashMap<String, Object>();
            setParamMap.put("dataSharePrice", bigDecimal);
            int result = productDao.updateMyDataByParam(productId, userId, setParamMap);

            return result > 0;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("updateMyDataProduct error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Boolean deleteMyDataProduct(Long productId, String userId) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(productId,userId));

            Map<String, Object> setParamMap = new HashMap<String, Object>();
            setParamMap.put("isDeleted", "y");
            int result = productDao.updateMyDataByParam(productId, userId, setParamMap);

            return result > 0;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("deleteMyDataProduct error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Long saveMyDataProduct(DataProductDto dataProductDto) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(dataProductDto,dataProductDto.getUserId(),
                    dataProductDto.getName(),dataProductDto.getClassifyId(),dataProductDto.getDataSharePrice(),
                    dataProductDto.getLabel(),dataProductDto.getDescription()));

            Assert.assertNotNull(Arrays.asList(dataProductDto.getPicFileId(),dataProductDto.getMainPicId(),
                    dataProductDto.getDataModelProductDtoDtoList()),"上传文件缺失！");

            return saveData(dataProductDto, true);
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("saveMyDataProduct error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Long saveSingleProductByBops(SingleProductDto singleProductDto) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(singleProductDto,singleProductDto.getName(),singleProductDto.getMainPicId(),
                    singleProductDto.getDescription(),singleProductDto.getSingleProductType(),singleProductDto.getPublishTime()));

            SingleProductTypeEnum singleProductTypeEnum = SingleProductTypeEnum.fromCode(singleProductDto.getSingleProductType());
            if (singleProductTypeEnum == null) throw new BizException(BizConstant.COMMON_PARAM_ERROR, "参数缺失");

            checkParam(singleProductDto.getOnlineType(), singleProductDto.getOnlineTime(), singleProductDto.getOfflineType(), singleProductDto.getOfflineTime());
            checkSkuList(singleProductDto.getSkuDtoList());

            ProductEntity productEntity = new ProductEntity();
            BeanUtils.copyProperties(productEntity, singleProductDto);
            productEntity.setGmtCreate(new Date());
            productEntity.setCreator("sys");
            productEntity.setGmtModified(new Date());
            productEntity.setModifier("sys");
            productEntity.setIsDeleted("n");
            productEntity.setState(getStateByOnlineType(singleProductDto.getOnlineType()));


            //TODO 如果是设备类型 根据型号类型判断 判断当前单品所属类目
            Long categoryId = getCategoryIdBySingleProduct(singleProductTypeEnum, singleProductDto);
            productEntity.setCategoryId(categoryId);

            if (singleProductTypeEnum.getCode().equals(SingleProductTypeEnum.DEVICE.getCode()))
                productEntity.setProductType(ProductTypeEnum.DEVICE.getCode());

            if (singleProductTypeEnum.getCode().equals(SingleProductTypeEnum.MATERIAL.getCode()))
                productEntity.setProductType(ProductTypeEnum.MATERIAL.getCode());


            productDao.insertSelective(productEntity);
            Long productId = productEntity.getId();

            batchSaveSkuList(singleProductDto.getSkuDtoList(), productId, categoryId);

            sendToMq(productId, singleProductDto.getOnlineType(), singleProductDto.getOnlineTime(), singleProductDto.getOfflineType(), singleProductDto.getOfflineTime());

            return productId;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("saveSingleProductByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    private void sendToMq(Long productId, String onlineType, Date onlineTime, String offlineType, Date offlineTime) {
        if (onlineType.equals(OnlineTypeEnum.DELAY.getCode()) ||
                offlineType.equals(OffLineTypeEnum.DELAY.getCode())) {
            if (onlineType.equals(OnlineTypeEnum.DELAY.getCode())) {
                sendOnlineDataToMq(productId,onlineTime);
            }

            if (offlineType.equals(OffLineTypeEnum.DELAY.getCode())) {
                sendOffLineDataToMq(productId,offlineTime);
            }
        }
    }

    private void sendOnlineDataToMq(Long productId,Date onlineTime) {
        Assert.assertNotNull(onlineTime.getTime(),"定时上架时间不能为空!");
        long expiration = onlineTime.getTime() - DateTime.now().toDate().getTime();
        sendDelayProductToMq(productId,onlineTime.getTime(),StateEnum.ONLINE.getCode(),expiration);
    }

    private void sendOffLineDataToMq(Long productId,Date offlineTime) {
        Assert.assertNotNull(offlineTime.getTime(),"定时下架时间不能为空!");
        long expiration = offlineTime.getTime() - DateTime.now().toDate().getTime();
        sendDelayProductToMq(productId,offlineTime.getTime(),StateEnum.OFFLINE.getCode(),expiration);
    }

    private void sendDelayProductToMq(Long productId,Long executeTime,String state,long expiration) {
        ProductMessage productMessage = new ProductMessage();
        productMessage.setProductId(productId);
        productMessage.setState(state);
        productMessage.setExecuteTime(executeTime);
        mqHelper.sendMq(zeusConfig.getProductStateModifyDlxRouteKey(), FastJson.toJson(productMessage), expiration);
    }


    public List<SingleProductDto> querySingleProductListByBops(SingleProductQueryDto singleProductQueryDto, PageInfo pageInfo) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(singleProductQueryDto,pageInfo));

            SingleProductTypeEnum singleProductTypeEnum = SingleProductTypeEnum.fromCode(singleProductQueryDto.getSingleProductType());

            if (singleProductTypeEnum == null) throw new BizException(BizConstant.COMMON_PARAM_ERROR, "参数缺失");


            List<Long> categoryIdList = new ArrayList<Long>();
            if (singleProductTypeEnum.getCode().equals(SingleProductTypeEnum.MATERIAL.getCode()))
                categoryIdList.add(zeusConfig.getMaterialCategoryId());

            if (singleProductTypeEnum.getCode().equals(SingleProductTypeEnum.DEVICE.getCode())) {
                categoryIdList.add(zeusConfig.getDevicePrintCategoryId());
                categoryIdList.add(zeusConfig.getDeviceScanCategoryId());
            }

            Map<String, Object> paramMap = buildQueryMap(singleProductQueryDto, categoryIdList);
            int total = productDao.queryAllProductCountByParam(paramMap);
            pageInfo.setRowCount(total);

            List<ProductEntity> productEntityList = productDao.queryAllProductPageByParam(paramMap, pageInfo);

            return convertToSingleProductDto(productEntityList);
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("querySingleProductListByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }

    }

    public List<SingleProductDto> queryAllSingleProductList() throws BizException, RepositoryException {
        try {
            List<Long> categoryIdList = Arrays.asList(zeusConfig.getMaterialCategoryId(),zeusConfig.getDevicePrintCategoryId(),zeusConfig.getDeviceScanCategoryId());
            List<ProductEntity> productEntityList = productDao.queryAllProductByCategoryIdList(categoryIdList,StateEnum.ONLINE.getCode());

            List<SingleProductDto> singleProductDtoList = convertToSingleProductDto(productEntityList);
            return singleProductDtoList;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryAllSingleProductList error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }


    public List<PackageProductDto> queryAllPackageProductList() throws BizException, RepositoryException {
        try {
            List<Long> categoryIdList = Arrays.asList(zeusConfig.getPackageCategoryId());
            List<ProductEntity> productEntityList = productDao.queryAllProductByCategoryIdList(categoryIdList,StateEnum.ONLINE.getCode());
            List<PackageProductDto> packageProductDtoList = convertToPackageProductDto(productEntityList);

            return packageProductDtoList;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryAllPackageProductList error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }


    private Map<Long,List<SkuEntity>> groupByProductId(List<SkuEntity> skuEntityList) {
        Map<Long,List<SkuEntity>> productEntityMap = new HashMap<Long,List<SkuEntity>>();
        for (SkuEntity skuEntity : skuEntityList) {
            Long id = skuEntity.getProductId();
            List<SkuEntity> list = null;
            if (productEntityMap.containsKey(id)) {
                list = productEntityMap.get(id);
            } else {
                list = new ArrayList<SkuEntity>();
                productEntityMap.put(id,list);
            }
            list.add(skuEntity);
        }
        return productEntityMap;
    }

    private List<SingleProductDto> convertToSingleProductDto(List<ProductEntity> productEntityList) {
        List<SingleProductDto> singleProductDtoList = new ArrayList<SingleProductDto>();;
        if (CollectionUtils.isNotEmpty(productEntityList)) {
            productEntityList.forEach(item -> {
                SingleProductDto singleProductDto = new SingleProductDto();
                BeanHelper.copyProperties(singleProductDto, item);
                Long productId = item.getId();
                singleProductDto.setProductId(productId);

                List<SkuDto> skuDtoList = querySkuListByProductId(productId);
                singleProductDto.setSkuDtoList(skuDtoList);
                singleProductDtoList.add(singleProductDto);
            });
        }
        if (singleProductDtoList.size() == 0)
            return null;
        else
            return singleProductDtoList;
    }

    private List<PackageProductDto> convertToPackageProductDto(List<ProductEntity> productEntityList) {
        List<PackageProductDto> packageProductDtoList = new ArrayList<PackageProductDto>();;
        if (CollectionUtils.isNotEmpty(productEntityList)) {
            productEntityList.forEach(item -> {
                PackageProductDto packageProductDto = new PackageProductDto();
                BeanHelper.copyProperties(packageProductDto, item);
                Long productId = item.getId();
                packageProductDto.setProductId(productId);

                List<SkuDto> skuDtoList = querySkuListByProductId(productId);
                List<ProductPackageRelationDto> packageRelationDtoList = queryProductPackageRelationListByProductId(productId);
                packageProductDto.setSkuDtoList(skuDtoList);
                packageProductDto.setProductPackageRelationDtoList(packageRelationDtoList);
                packageProductDtoList.add(packageProductDto);
            });
        }
        if (packageProductDtoList.size() == 0)
            return null;
        else
            return packageProductDtoList;
    }


    public SingleProductDto querySingleProductDetailByBops(Long productId) throws BizException, RepositoryException {
        try {
            if (productId == null)
                throw new BizException(BizConstant.COMMON_PARAM_ERROR, "参数缺失");

            ProductEntity productEntity = productDao.selectByPrimaryKey(productId);
            SingleProductDto singleProductDto = new SingleProductDto();
            Optional.ofNullable(productEntity).ifPresent(item -> {
                try {
                    List<SkuDto> skuDtoList = querySkuListByProductId(productId);
                    BeanUtils.copyProperties(singleProductDto, item);
                    singleProductDto.setProductId(item.getId());
                    singleProductDto.setSkuDtoList(skuDtoList);
                } catch (IllegalAccessException e) {
                    throw new BizException(BizConstant.COMMON_PARAM_ERROR, "copy异常");
                } catch (InvocationTargetException e) {
                    throw new BizException(BizConstant.COMMON_PARAM_ERROR, "copy异常");
                }
            });


            return singleProductDto;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("querySingleProductDetailByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public ProductDto queryProductByProductId(Long productId) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(productId);

            ProductEntity productEntity = productDao.selectByPrimaryKey(productId);
            ProductDto productDto = convertToProductDto(productEntity);
            return productDto;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryProductByProductId error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public DataProductDto queryDataProductDetailByDataId(Long dataId) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(dataId);

            ProductEntity productEntity = productDao.queryByDataId(dataId);
            DataProductDto dataProductDto = convertToDataProductDto(productEntity,true);
            return dataProductDto;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryDataProductDetailByDataId error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    private DataProductDto convertToDataProductDto(ProductEntity productEntity,boolean isFront) {
        DataProductDto dataProductDto = null;
        if (productEntity != null) {
            dataProductDto = new DataProductDto();
            BeanHelper.copyProperties(dataProductDto, productEntity);
            dataProductDto.setProductId(productEntity.getId());

            Long dataId = dataProductDto.getDataId();
            if (dataId != null) {

                Result<DataDto> dataDtoResult = null;
                if (isFront)
                    dataDtoResult = dataApiService.queryDataDetail(dataId);
                else
                    dataDtoResult = dataApiService.queryDataByBops(dataId);

                if (dataDtoResult.isSuccess() && dataDtoResult.getResult() != null) {
                    DataDto dataDto = dataDtoResult.getResult();
                    BeanHelper.copyProperties(dataProductDto, dataDto);

                    List<DataModelProductDto> dataModelProductDtoList = new LinkedList<DataModelProductDto>();
                    List<DataModelDto> dataModelDtoList = dataDto.getDataModelDtoList();
                    Optional.ofNullable(dataModelDtoList).ifPresent(list -> {
                        list.forEach(item -> {
                            DataModelProductDto dataModelProductDto = new DataModelProductDto();
                            BeanHelper.copyProperties(dataModelProductDto, item);
                            dataModelProductDtoList.add(dataModelProductDto);

                        });
                    });
                    dataProductDto.setDataModelProductDtoDtoList(dataModelProductDtoList);
                }
            }

            List<SkuDto> skuDtoList = querySkuListByProductId(productEntity.getId());
            dataProductDto.setSkuDto(skuDtoList);
        }
        return dataProductDto;
    }

    private ProductDto convertToProductDto(ProductEntity productEntity) {
        ProductDto productDto = null;
        if (productEntity != null) {
            productDto = new ProductDto();
            BeanHelper.copyProperties(productDto, productEntity);
            productDto.setProductId(productEntity.getId());

            Long categoryId = productEntity.getCategoryId();
            if (categoryId != null && categoryId.equals(zeusConfig.getPackageCategoryId())) {
                List<ProductPackageRelationDto> productPackageRelationDtoList = queryProductPackageRelationListByProductId(productEntity.getId());
                productDto.setProductPackageRelationDtoList(productPackageRelationDtoList);
            }
            List<SkuDto> skuDtoList = querySkuListByProductId(productEntity.getId());
            productDto.setSkuDtoList(skuDtoList);
        }
        return productDto;
    }


    private List<SkuDto> querySkuListByProductId(Long productId) {
        List<SkuDto> skuDtoList = new LinkedList<SkuDto>();
        List<SkuEntity> skuEntityList = skuDao.querySkuListByProductId(productId);
        Optional.ofNullable(skuEntityList).ifPresent(skuList -> {
            skuList.forEach(item -> {
                SkuDto skuDto = new SkuDto();
                BeanHelper.copyProperties(skuDto, item);
                skuDto.setSkuId(item.getId());
                skuDtoList.add(skuDto);

            });
        });
        return skuDtoList;
    }

    public Boolean updateSingleProductByBops(SingleProductDto singleProductDto) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(singleProductDto,singleProductDto.getProductId()));

            SingleProductTypeEnum singleProductTypeEnum = SingleProductTypeEnum.fromCode(singleProductDto.getSingleProductType());
            if (singleProductTypeEnum == null) throw new BizException(BizConstant.COMMON_PARAM_ERROR, "参数缺失");

            checkSkuList(singleProductDto.getSkuDtoList());

            ProductEntity productEntity = productDao.selectByPrimaryKey(singleProductDto.getProductId());
            Assert.assertNotNull(productEntity,"参数异常,无法找到商品!");

            Long categoryId = getCategoryIdBySingleProduct(singleProductTypeEnum, singleProductDto);

            ProductEntity updateProductEntity = new ProductEntity();
            BeanUtils.copyProperties(updateProductEntity, singleProductDto);
            updateProductEntity.setId(singleProductDto.getProductId());
            updateProductEntity.setGmtModified(new Date());
            productDao.updateByPrimaryKeySelective(updateProductEntity);

            updateSkuList(singleProductDto.getSkuDtoList(), singleProductDto.getProductId(), categoryId);

            //TODO 如果延时时间有修改,则往mq发送延时消息
            if (singleProductDto.getOnlineType().equals(OnlineTypeEnum.DELAY.getCode())  && !singleProductDto.getOnlineTime().equals(productEntity.getOnlineTime())) {
                sendOnlineDataToMq(singleProductDto.getProductId(),singleProductDto.getOnlineTime());
            }
            if (singleProductDto.getOfflineType().equals(OffLineTypeEnum.DELAY.getCode()) && !singleProductDto.getOfflineTime().equals(productEntity.getOfflineTime())) {
                sendOffLineDataToMq(singleProductDto.getProductId(),singleProductDto.getOfflineTime());
            }
            return true;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("updateSingleProductByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Long savePackageProductByBops(PackageProductDto packageProductDto) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(packageProductDto,packageProductDto.getName(),
                    packageProductDto.getMainPicId(),packageProductDto.getDescription(),packageProductDto.getPublishTime()));

            List<ProductPackageRelationDto> productPackageRelationDtoList = packageProductDto.getProductPackageRelationDtoList();
            checkProductPackageRelationParam(productPackageRelationDtoList);


            checkParam(packageProductDto.getOnlineType(), packageProductDto.getOnlineTime(), packageProductDto.getOfflineType(), packageProductDto.getOfflineTime());
            checkSkuList(packageProductDto.getSkuDtoList(), true);
            List<SkuDto> skuDtoList = packageProductDto.getSkuDtoList();
            if (skuDtoList.size() > 1) throw new BizException(BizConstant.COMMON_PARAM_ERROR,"套餐商品sku只能配置一个!");

            ProductEntity productEntity = new ProductEntity();
            BeanUtils.copyProperties(productEntity, packageProductDto);
            productEntity.setGmtCreate(new Date());
            productEntity.setCreator("sys");
            productEntity.setGmtModified(new Date());
            productEntity.setModifier("sys");
            productEntity.setIsDeleted("n");
            productEntity.setCategoryId(zeusConfig.getPackageCategoryId());
            productEntity.setProductType(ProductTypeEnum.PACKAGE.getCode());
            productDao.insertSelective(productEntity);

            batchSaveProductPackageRelation(productEntity.getId(), productPackageRelationDtoList);

            batchSaveSkuList(packageProductDto.getSkuDtoList(), productEntity.getId(), productEntity.getName(), zeusConfig.getPackageCategoryId());

            sendToMq(productEntity.getId(),packageProductDto.getOnlineType(),packageProductDto.getOnlineTime(),packageProductDto.getOfflineType(),packageProductDto.getOfflineTime());
            return productEntity.getId();
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("savePackageProductByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public List<PackageProductDto> queryPackageProductListByBops(PackageProductQueryDto packageProductQueryDto, PageInfo pageInfo) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(packageProductQueryDto,pageInfo));

            Map<String, Object> paramMap = buildQueryMap(packageProductQueryDto);
            int total = productDao.queryAllProductCountByParam(paramMap);
            pageInfo.setRowCount(total);

            List<PackageProductDto> packageProductDtoList = new ArrayList<PackageProductDto>();
            List<ProductEntity> productEntityList = productDao.queryAllProductPageByParam(paramMap, pageInfo);


            Optional.ofNullable(productEntityList).ifPresent(list -> {
                list.forEach(item -> {
                    PackageProductDto packageProductDto = new PackageProductDto();
                    BeanHelper.copyProperties(packageProductDto, item);
                    Long productId = item.getId();

                    List<SkuDto> skuDtoList = querySkuListByProductId(productId);
                    packageProductDto.setSkuDtoList(skuDtoList);

                    packageProductDto.setProductId(productId);
                    packageProductDtoList.add(packageProductDto);

                });
            });
            return packageProductDtoList;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryPackageProductListByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public PackageProductDto queryPackageProductDetailByBops(Long productId) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(productId);

            ProductEntity productEntity = productDao.selectByPrimaryKey(productId);
            PackageProductDto packageProductDto = new PackageProductDto();
            Optional.ofNullable(productEntity).ifPresent(item -> {
                List<ProductPackageRelationDto> productPackageRelationDtoList = queryProductPackageRelationListByProductId(productId);

                List<SkuDto> skuDtoList = querySkuListByProductId(productId);
                BeanHelper.copyProperties(packageProductDto, item);
                packageProductDto.setProductId(item.getId());
                packageProductDto.setSkuDtoList(skuDtoList);
                packageProductDto.setProductPackageRelationDtoList(productPackageRelationDtoList);
            });
            return packageProductDto;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryPackageProductDetailByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public Boolean updatePackageProductByBops(PackageProductDto packageProductDto) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(packageProductDto,packageProductDto.getProductId(),packageProductDto.getName(),
                    packageProductDto.getOnlineType(),packageProductDto.getOfflineType()));

            List<ProductPackageRelationDto> productPackageRelationDtoList = packageProductDto.getProductPackageRelationDtoList();
            checkProductPackageRelationParam(productPackageRelationDtoList);

            ProductEntity productEntity = productDao.selectByPrimaryKey(packageProductDto.getProductId());
            Assert.assertNotNull(productEntity,"参数异常,无法找到商品!");

            ProductEntity updateProductEntity = new ProductEntity();
            BeanUtils.copyProperties(updateProductEntity, packageProductDto);
            updateProductEntity.setId(packageProductDto.getProductId());
            updateProductEntity.setGmtModified(new Date());

            productDao.updateByPrimaryKeySelective(updateProductEntity);
            //TODO 先remove后批量插入
            productPackageRelationDao.removeProductPackageRelationByPackageProductId(packageProductDto.getProductId());
            batchSaveProductPackageRelation(packageProductDto.getProductId(), packageProductDto.getProductPackageRelationDtoList());

            List<SkuDto> skuDtoList = packageProductDto.getSkuDtoList();
            Assert.assertNotNull(skuDtoList,"sku不能为空!");
            if (skuDtoList.size() > 1) throw new BizException(BizConstant.COMMON_PARAM_ERROR,"套餐商品sku只能配置一个!");
            SkuDto skuDto = skuDtoList.get(0);
            Assert.assertNotNull(skuDto.getSkuId(),"skuId不能为空!");

            SkuEntity skuEntity = new SkuEntity();
            BeanHelper.copyProperties(skuEntity,skuDto);
            skuEntity.setGmtModified(new Date());
            skuEntity.setId(skuDto.getSkuId());

            //TODO 如果名字有变化，则生成新的套餐属性名
            if (StringUtils.isNotEmpty(packageProductDto.getName()) && !productEntity.getName().equals(packageProductDto.getName())) {
                PropValueEntity propValueEntity = propValueDao.queryByPropValueName(packageProductDto.getName());
                PropKeyDto propKeyDto = new PropKeyDto();
                propKeyDto.setPropId(zeusConfig.getPackagePropId());
                PropValueDto propValueDto = new PropValueDto();
                propValueDto.setPropName(zeusConfig.getPackagePropName());

                if (propValueEntity == null) {
                    propValueEntity = new PropValueEntity();
                    propValueEntity.setCreator("sys");
                    propValueEntity.setGmtCreate(DateTime.now().toDate());
                    propValueEntity.setModifier("sys");
                    propValueEntity.setGmtModified(DateTime.now().toDate());
                    propValueEntity.setIsDeleted("n");
                    propValueEntity.setPropValueName(packageProductDto.getName());
                    propValueEntity.setPropId(zeusConfig.getPackagePropId());
                    propValueEntity.setPropName(zeusConfig.getPackagePropName());
                    propValueEntity.setSortNo(1);
                    propValueEntity.setComments(packageProductDto.getName());
                    propValueDao.insertSelective(propValueEntity);

                    propKeyDto.setPropValueId(propValueEntity.getId());
                    propValueDto.setPropValueName(packageProductDto.getName());

                } else {
                    propKeyDto.setPropValueId(propValueEntity.getId());
                    propValueDto.setPropValueName(propValueEntity.getPropValueName());
                }
                skuEntity.setPropKey(FastJson.toJson(propKeyDto));
                skuEntity.setPropName(FastJson.toJson(propValueDto));
                skuEntity.setUnit("套");
            }
            skuDao.updateByPrimaryKeySelective(skuEntity);

            //TODO 如果延时时间有修改,则往mq发送延时消息
            if (packageProductDto.getOnlineType().equals(OnlineTypeEnum.DELAY.getCode())  && !packageProductDto.getOnlineTime().equals(productEntity.getOnlineTime())) {
                sendOnlineDataToMq(packageProductDto.getProductId(),packageProductDto.getOnlineTime());
            }
            if (packageProductDto.getOfflineType().equals(OffLineTypeEnum.DELAY.getCode()) && !packageProductDto.getOfflineTime().equals(productEntity.getOfflineTime())) {
                sendOffLineDataToMq(packageProductDto.getProductId(),packageProductDto.getOfflineTime());
            }
            return true;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("updatePackageProductByBops error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public List<ProductDto> queryProductByBops(ProductQueryDto productQueryDto, PageInfo pageInfo) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(productQueryDto,pageInfo));

            Map<String, Object> paramMap = buildQueryMap(productQueryDto);
            int total = productDao.queryAllProductCountByParam(paramMap);
            pageInfo.setRowCount(total);

            List<ProductDto> productDtoList = new ArrayList<ProductDto>();
            List<ProductEntity> productEntityList = productDao.queryAllProductPageByParam(paramMap, pageInfo);


            Optional.ofNullable(productEntityList).ifPresent(list -> {
                list.forEach(item -> {
                    ProductDto productDto = new ProductDto();
                    BeanHelper.copyProperties(productDto, item);
                    Long productId = item.getId();

                    productDto.setProductId(productId);
                    productDtoList.add(productDto);

                });
            });
            return productDtoList;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryProductByParam error ,e : {}", e);
            throw new RepositoryException(e);
        }

    }


    private Long getCategoryIdBySingleProduct(SingleProductTypeEnum singleProductTypeEnum, SingleProductDto singleProductDto) {
        Long categoryId = null;
        if (singleProductTypeEnum.getCode().equals(SingleProductTypeEnum.DEVICE.getCode())) {
            SkuDto skuDto = singleProductDto.getSkuDtoList().get(0);
            String propNameJson = skuDto.getPropName();
            List<PropNameDto> propNameDtoList = FastJson.jsonToList(propNameJson, PropNameDto.class);

            boolean resultOne = propNameDtoList.stream().anyMatch(item -> {
                String propValueName = item.getPropValueName();
                return StringUtils.equals(propValueName, Constants.DEVICE_EINSTART_L) || StringUtils.equals(propValueName, Constants.DEVICE_EINSTART_S);
            });

            boolean resultTwo = propNameDtoList.stream().anyMatch(item -> {
                String propValueName = item.getPropValueName();
                return StringUtils.equals(propValueName, Constants.DEVICE_EINSCAN_S) || StringUtils.equals(propValueName, Constants.DEVICE_EINSCAN_PRO);
            });

            if (!resultOne && !resultTwo) throw new BizException(BizConstant.COMMON_PARAM_ERROR, "型号错误!");

            if (resultOne) categoryId = zeusConfig.getDevicePrintCategoryId();

            if (resultTwo) categoryId = zeusConfig.getDeviceScanCategoryId();
        }

        if (singleProductTypeEnum.getCode().equals(SingleProductTypeEnum.MATERIAL.getCode()))
            categoryId = zeusConfig.getMaterialCategoryId();

        return categoryId;
    }

    private void batchSaveProductPackageRelation(Long productId, List<ProductPackageRelationDto> productPackageRelationDtoList) {
        List<ProductPackageRelationEntity> productPackageRelationEntityList = new ArrayList<ProductPackageRelationEntity>();
        productPackageRelationDtoList.forEach(item -> {
            ProductPackageRelationEntity productPackageRelationEntity = new ProductPackageRelationEntity();
            BeanHelper.copyProperties(productPackageRelationEntity, item);
            productPackageRelationEntity.setGmtCreate(new Date());
            productPackageRelationEntity.setCreator("sys");
            productPackageRelationEntity.setGmtModified(new Date());
            productPackageRelationEntity.setModifier("sys");
            productPackageRelationEntity.setIsDeleted("n");
            productPackageRelationEntity.setPackageProductId(productId);

            productPackageRelationEntityList.add(productPackageRelationEntity);

        });
        if (CollectionUtils.isNotEmpty(productPackageRelationDtoList))
            productPackageRelationDao.batchSaveProductPackageRelation(productPackageRelationEntityList);
    }

    private void checkProductPackageRelationParam(List<ProductPackageRelationDto> productPackageRelationDtoList) {
        Assert.assertNotNull(productPackageRelationDtoList,"商品选择不能为空!");

        productPackageRelationDtoList.forEach(item -> {
            Assert.assertNotNull(Arrays.asList(item.getProductId(),item.getNum()));

            Assert.assertNotMinusAndZero(item.getNum(),"数量必须大于0");

        });
    }

    private Long saveData(DataProductDto dataProductDto, boolean isFront) throws BizException, RepositoryException {

        if (!isFront)
            checkParam(dataProductDto.getOnlineType(), dataProductDto.getOnlineTime(), dataProductDto.getOfflineType(), dataProductDto.getOfflineTime());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setGmtCreate(new Date());
        productEntity.setCreator("sys");
        productEntity.setGmtModified(new Date());
        productEntity.setModifier("sys");
        productEntity.setIsDeleted("n");
        productEntity.setName(dataProductDto.getName());
        productEntity.setUserId(dataProductDto.getUserId());
        productEntity.setDataSharePrice(dataProductDto.getDataSharePrice());
        productEntity.setMainPicId(dataProductDto.getMainPicId());
        productEntity.setPicFileId(dataProductDto.getPicFileId());
        productEntity.setCategoryId(zeusConfig.getDataProductCategoryId());
        productEntity.setDescription(dataProductDto.getDescription());
        productEntity.setProductType(ProductTypeEnum.DATA.getCode());
        productEntity.setPublishTime(dataProductDto.getPublishTime());
        productEntity.setState(dataProductDto.getState());

        productEntity.setOnlineType(dataProductDto.getOnlineType());
        productEntity.setOnlineTime(dataProductDto.getOnlineTime());
        productEntity.setOfflineType(dataProductDto.getOfflineType());
        productEntity.setOfflineTime(dataProductDto.getOfflineTime());
        productDao.insertSelective(productEntity);
        Long productId = productEntity.getId();

        if (CollectionUtils.isNotEmpty(dataProductDto.getSkuDto())) batchSaveSkuList(dataProductDto.getSkuDto(),productId, zeusConfig.getDataProductCategoryId());

        Long dataId = syncData(dataProductDto,isFront);
        Assert.assertNotNull(dataId,"数据服务异常!");

        ProductEntity updateProduct = new ProductEntity();
        updateProduct.setDataId(dataId);
        updateProduct.setId(productId);
        productDao.updateByPrimaryKeySelective(updateProduct);

        return productId;
    }

    private void checkParam(String onlineType, Date onlineTime, String offlineType, Date offlineTime) {
        OnlineTypeEnum onlineTypeEnum = OnlineTypeEnum.fromCode(onlineType);
        if (onlineTypeEnum == null) throw new BizException(BizConstant.COMMON_PARAM_ERROR, "上架状态类型错误!");

        if (onlineTypeEnum.getCode().equals(OnlineTypeEnum.NOW.getCode()) && onlineTime != null)
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, "立即上架,不能配置上架时间!");

        if (onlineTypeEnum.getCode().equals(OnlineTypeEnum.DELAY.getCode()) && onlineTime == null)
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, "定时上架,上架时间不能为空!");

        OffLineTypeEnum offLineTypeEnum = OffLineTypeEnum.fromCode(offlineType);


        Assert.assertNotNull(offLineTypeEnum,"下架时间不能为空!");

        if (offLineTypeEnum.getCode().equals(OffLineTypeEnum.FOREVER.getCode()) && offlineTime != null)
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, "永久销售,不能配置下架时间!");

        if (offLineTypeEnum.getCode().equals(OffLineTypeEnum.DELAY.getCode()) && offlineTime == null)
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, "定时下架,下架时间不能为空!");


        if (onlineTypeEnum.getCode().equals(OnlineTypeEnum.DELAY.getCode()) && offLineTypeEnum.getCode().equals(OffLineTypeEnum.DELAY.getCode())
                && onlineTime.getTime() > offlineTime.getTime())
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, "上架时间不能大于下架时间!");

    }

    private String getStateByOnlineType(String onlineType) {
        String state = StateEnum.OFFLINE.getCode();
        if (StringUtils.isNotEmpty(onlineType) && onlineType.equals(OnlineTypeEnum.NOW.getCode()))
            state = StateEnum.ONLINE.getCode();
        return state;
    }

    private Map<String, Object> buildQueryMap(DataProductQueryDto dataProductQueryDto) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", dataProductQueryDto.getName());
        paramMap.put("publishBeginTime", dataProductQueryDto.getPublishBeginTime());
        paramMap.put("publishEndTime", dataProductQueryDto.getPublishEndTime());
        paramMap.put("userId", dataProductQueryDto.getUserId());
        paramMap.put("state",dataProductQueryDto.getState());
        paramMap.put("categoryIdList", Arrays.asList(zeusConfig.getDataProductCategoryId()));
        return paramMap;
    }

    private Map<String, Object> buildQueryMap(DataProductBopsQueryDto bopsQueryDto) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("state",bopsQueryDto.getState());
        paramMap.put("name", bopsQueryDto.getName());
        paramMap.put("userId",bopsQueryDto.getUserId());
        paramMap.put("publishBeginTime", bopsQueryDto.getPublishBeginTime());
        paramMap.put("publishEndTime", bopsQueryDto.getPublishEndTime());
        paramMap.put("categoryIdList", Arrays.asList(zeusConfig.getDataProductCategoryId()));
        return paramMap;
    }

    private Map<String, Object> buildQueryMap(SingleProductQueryDto singleProductQueryDto, List<Long> categoryIdList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", singleProductQueryDto.getName());
        paramMap.put("publishBeginTime", singleProductQueryDto.getPublishBeginTime());
        paramMap.put("publishEndTime", singleProductQueryDto.getPublishEndTime());
        paramMap.put("categoryIdList", categoryIdList);
        paramMap.put("state", singleProductQueryDto.getState());
        return paramMap;
    }

    private Map<String, Object> buildQueryMap(PackageProductQueryDto packageProductQueryDto) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", packageProductQueryDto.getName());
        paramMap.put("publishBeginTime", packageProductQueryDto.getPublishBeginTime());
        paramMap.put("publishEndTime", packageProductQueryDto.getPublishEndTime());
        paramMap.put("categoryIdList", Arrays.asList(zeusConfig.getPackageCategoryId()));
        paramMap.put("state", packageProductQueryDto.getState());
        return paramMap;
    }

    private Map<String, Object> buildQueryMap(ProductQueryDto productQueryDto) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", productQueryDto.getName());
        paramMap.put("state",productQueryDto.getState());
        String type = productQueryDto.getType();
        if (StringUtils.isNotEmpty(type)) {
            if (type.equals(ProductTypeEnum.DATA.getCode()))
                paramMap.put("categoryIdList", Arrays.asList(zeusConfig.getDataProductCategoryId()));

            if (type.equals(ProductTypeEnum.DEVICE.getCode()))
                paramMap.put("categoryIdList", Arrays.asList(zeusConfig.getDevicePrintCategoryId(), zeusConfig.getDeviceScanCategoryId()));

            if (type.equals(ProductTypeEnum.MATERIAL.getCode()))
                paramMap.put("categoryIdList", Arrays.asList(zeusConfig.getMaterialCategoryId()));

            if (type.equals(ProductTypeEnum.PACKAGE.getCode()))
                paramMap.put("categoryIdList", Arrays.asList(zeusConfig.getPackageCategoryId()));
        } else {
            //TODO 后台套餐筛选商品只查询 设备和材料
            paramMap.put("categoryIdList", Arrays.asList(zeusConfig.getDevicePrintCategoryId(), zeusConfig.getDeviceScanCategoryId(),zeusConfig.getMaterialCategoryId()));
        }
        return paramMap;
    }

    private void batchSaveSkuList(List<SkuDto> skuDtoList, Long productId, Long categoryId) {
        batchSaveSkuList(skuDtoList, productId, "", categoryId);
    }

    public void updateSkuList(List<SkuDto> skuDtoList, Long productId,Long categoryId) {
        List<SkuEntity> insertSkuList = new ArrayList<SkuEntity>();
        skuDtoList.forEach(item -> {
            //TODO 如果是新增sku
            if (item.getSkuId() == null) {
                SkuEntity skuEntity = new SkuEntity();
                BeanHelper.copyProperties(skuEntity, item);
                skuEntity.setGmtCreate(new Date());
                skuEntity.setCreator("sys");
                skuEntity.setGmtModified(new Date());
                skuEntity.setModifier("sys");
                skuEntity.setIsDeleted("n");
                skuEntity.setProductId(productId);
                skuEntity.setQuantity(Optional.ofNullable(item.getQuantity()).orElse(-1));

                skuEntity.setUnitNum(item.getUnitNum());

                if (categoryId.equals(zeusConfig.getDataProductCategoryId())) skuEntity.setUnit("份");

                if (categoryId.equals(zeusConfig.getDevicePrintCategoryId()) || categoryId.equals(zeusConfig.getDeviceScanCategoryId()))
                    skuEntity.setUnit("台");

                if (categoryId.equals(zeusConfig.getMaterialCategoryId())) skuEntity.setUnit("g");

                insertSkuList.add(skuEntity);

            } else {
                SkuEntity skuEntity = new SkuEntity();
                BeanHelper.copyProperties(skuEntity, item);
                skuEntity.setGmtModified(new Date());
                skuEntity.setProductId(productId);
                skuEntity.setQuantity(Optional.ofNullable(item.getQuantity()).orElse(-1));
                skuEntity.setUnitNum(item.getUnitNum());
                skuEntity.setId(item.getSkuId());
                if (categoryId.equals(zeusConfig.getDataProductCategoryId())) skuEntity.setUnit("份");

                if (categoryId.equals(zeusConfig.getDevicePrintCategoryId()) || categoryId.equals(zeusConfig.getDeviceScanCategoryId()))
                    skuEntity.setUnit("台");

                if (categoryId.equals(zeusConfig.getMaterialCategoryId())) skuEntity.setUnit("g");

                if (categoryId.equals(zeusConfig.getPackageCategoryId())) skuEntity.setUnit("套");

                skuDao.updateByPrimaryKey(skuEntity);
            }

        });

        if (CollectionUtils.isNotEmpty(insertSkuList)) skuDao.batchSaveSku(insertSkuList);
    }

    private void batchSaveSkuList(List<SkuDto> skuDtoList, Long productId, String productName, Long categoryId) {
        List<SkuEntity> skuEntityList = new ArrayList<SkuEntity>();

        skuDtoList.forEach(item -> {
            SkuEntity skuEntity = new SkuEntity();
            BeanHelper.copyProperties(skuEntity, item);
            skuEntity.setGmtCreate(new Date());
            skuEntity.setCreator("sys");
            skuEntity.setGmtModified(new Date());
            skuEntity.setModifier("sys");
            skuEntity.setIsDeleted("n");
            skuEntity.setProductId(productId);
            skuEntity.setQuantity(Optional.ofNullable(item.getQuantity()).orElse(-1));

            skuEntity.setUnitNum(item.getUnitNum());

            if (categoryId.equals(zeusConfig.getDataProductCategoryId())) skuEntity.setUnit("份");

            if (categoryId.equals(zeusConfig.getDevicePrintCategoryId()) || categoryId.equals(zeusConfig.getDeviceScanCategoryId()))
                skuEntity.setUnit("台");

            if (categoryId.equals(zeusConfig.getMaterialCategoryId())) skuEntity.setUnit("g");

            //TODO 如果是套餐商品,动态添加套餐属性
            if (categoryId.equals(zeusConfig.getPackageCategoryId())) {
                PropValueEntity propValueEntity = propValueDao.queryByPropValueName(productName);
                PropKeyDto propKeyDto = new PropKeyDto();
                propKeyDto.setPropId(zeusConfig.getPackagePropId());
                PropValueDto propValueDto = new PropValueDto();
                propValueDto.setPropName(zeusConfig.getPackagePropName());

                if (propValueEntity == null) {
                    propValueEntity = new PropValueEntity();
                    propValueEntity.setCreator("sys");
                    propValueEntity.setGmtCreate(DateTime.now().toDate());
                    propValueEntity.setModifier("sys");
                    propValueEntity.setGmtModified(DateTime.now().toDate());
                    propValueEntity.setIsDeleted("n");
                    propValueEntity.setPropValueName(productName);
                    propValueEntity.setPropId(zeusConfig.getPackagePropId());
                    propValueEntity.setPropName(zeusConfig.getPackagePropName());
                    propValueEntity.setSortNo(1);
                    propValueEntity.setComments(productName);
                    propValueDao.insertSelective(propValueEntity);

                    propKeyDto.setPropValueId(propValueEntity.getId());
                    propValueDto.setPropValueName(productName);

                } else {
                    propKeyDto.setPropValueId(propValueEntity.getId());
                    propValueDto.setPropValueName(propValueEntity.getPropValueName());
                }
                skuEntity.setPropKey(FastJson.toJson(propKeyDto));
                skuEntity.setPropName(FastJson.toJson(propValueDto));
                skuEntity.setUnit("套");
            }
            skuEntityList.add(skuEntity);
        });
        if (CollectionUtils.isNotEmpty(skuDtoList)) skuDao.batchSaveSku(skuEntityList);

    }

    private void checkSkuList(List<SkuDto> skuDtoList) throws BizException, RepositoryException {
        checkSkuList(skuDtoList, false);
    }

    private void checkSkuList(List<SkuDto> skuDtoList, boolean isPackageProduct) throws BizException, RepositoryException {
        Assert.assertNotNull(skuDtoList,"sku配置不能为空!");

        skuDtoList.forEach(item -> {
            if (!isPackageProduct ){
                Assert.assertNotNull(Arrays.asList(item.getPropKey(),item.getPropName()),"sku配置参数缺失！");
            }
            Assert.assertNotNull(item.getOriginalPrice(),"sku原价不能为空!");

            if (item.getPromotionPrice() != null)  Assert.assertNotNull(item.getPromotionStartTime(),"促销开始时间不能为空!");
        });
    }

    private Long syncData(DataProductDto dataProductDto,boolean isFront) throws RepositoryException {
        logger.info("begin sync data");
        DataDto dataDto = new DataDto();
        dataDto.setUserId(dataProductDto.getUserId());
        dataDto.setName(dataProductDto.getName());
        dataDto.setClassifyId(dataProductDto.getClassifyId());
        dataDto.setAuthType("dataPrintShare");
        dataDto.setPicFileId(dataProductDto.getPicFileId());
        dataDto.setMainPicId(dataProductDto.getMainPicId());
        dataDto.setLabel(dataProductDto.getLabel());
        dataDto.setDescription(dataProductDto.getDescription());
        dataDto.setPublishTime(dataProductDto.getPublishTime());
        dataDto.setIsBest("1");
        dataDto.setSeoTitle(dataProductDto.getSeoTitle());
        dataDto.setSeoKeyword(dataProductDto.getSeoKeyword());
        dataDto.setSeoDescription(dataProductDto.getSeoDescription());
        dataDto.setArticleId(dataProductDto.getArticleId());
        dataDto.setStatus("success");

        List<DataModelDto> dataModelDtoList = convertToDataModel(dataProductDto.getDataModelProductDtoDtoList());
        dataDto.setDataModelDtoList(dataModelDtoList);
        Result<Long> result = null;

        if (isFront)
            result = dataApiService.saveData(dataDto);
        else
            result = dataApiService.saveDataByBops(dataDto);

        if (result.isSuccess()) {
            logger.info("sync data success!");
            return result.getResult();
        } else {
            throw new RepositoryException(result.getErrMsg());
        }

    }

    private List<ProductPackageRelationDto> queryProductPackageRelationListByProductId(Long productId) {
        List<ProductPackageRelationEntity> productPackageRelationEntityList = productPackageRelationDao.queryProductPackageRelationListByPackageProductId(productId);
        List<ProductPackageRelationDto> productPackageRelationDtoList = new ArrayList<ProductPackageRelationDto>();
        productPackageRelationEntityList.forEach(productPackageItem -> {
            ProductPackageRelationDto productPackageRelationDto = new ProductPackageRelationDto();
            BeanHelper.copyProperties(productPackageRelationDto, productPackageItem);
            productPackageRelationDto.setProductPackageRelationId(productPackageItem.getPackageProductId());
            productPackageRelationDtoList.add(productPackageRelationDto);

        });
        return productPackageRelationDtoList;
    }

}


