package com.shining3d.zeus.core.test.repository;

import com.shining3d.common.FastJson;
import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.*;
import com.shining3d.zeus.client.dto.query.*;
import com.shining3d.zeus.core.test.BaseTest;
import com.shining3d.zeus.enums.OffLineTypeEnum;
import com.shining3d.zeus.enums.OnlineTypeEnum;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.ProductRepository;
import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by fe on 2016/12/27.
 */
public class ProductRepositoryTest extends BaseTest {

    @Resource
    private ProductRepository productRepository;


    @Test
    public void saveDataProductByBopsTest() throws Exception {
        DataProductDto dataProductDto = new DataProductDto();
        dataProductDto.setGmtCreate(new Date());
        dataProductDto.setDataSharePrice(new BigDecimal(1000l));
        dataProductDto.setUserId("5666513d5095600f00184e6c");
        dataProductDto.setName("牛逼哇asdfasdfasdf");
        dataProductDto.setClassifyId(1l);
        dataProductDto.setPicFileId("0f6bf9dba3ed16bebd1222b5adfaa3f2,e49763623618ac7c9aeac28ffc493cbf");
        dataProductDto.setMainPicId("a6097ebd11f962ca5d87401a393b1c47");
        dataProductDto.setLabel("1,2,3");
        dataProductDto.setDescription("description");
        dataProductDto.setPublishTime(new Date());
        dataProductDto.setSeoTitle("123title");
        dataProductDto.setSeoKeyword("123keyword");
        dataProductDto.setSeoDescription("123description");
        dataProductDto.setArticleId("1");
        dataProductDto.setOnlineType("delay");
        dataProductDto.setOnlineTime(new Date());
        dataProductDto.setOfflineType("forever");

        List<DataModelProductDto> dataModelProductDtoList = new ArrayList<DataModelProductDto>();
        DataModelProductDto dataModelProductDto = new DataModelProductDto();
        dataModelProductDto.setDfsId("0f6bf9dba3ed16bebd1222b5adfaa3f2");
        dataModelProductDto.setFileLength(111l);
        dataModelProductDto.setSizeDesc("111mb");
        dataModelProductDto.setFullName("a.stl");
        dataModelProductDto.setMeasurement("12*10*12cm");
        dataModelProductDtoList.add(dataModelProductDto);
        dataProductDto.setDataModelProductDtoDtoList(dataModelProductDtoList);

        List<SkuDto> skuDtoList = new ArrayList<SkuDto>();
        SkuDto skuDto = new SkuDto();
        skuDto.setCanUseCoupon("y");
        skuDto.setComments("test");
        skuDto.setOriginalPrice(new BigDecimal(112l));
        skuDto.setPromotionPrice(new BigDecimal(100l));
        skuDto.setPromotionStartTime(new Date());
        skuDto.setPromotionEndTime(new Date());

        List<Map<String, Long>> propKeyMapList = new LinkedList<Map<String, Long>>();
        Map<String, Long> propKeyMap = new HashMap<String, Long>();
        propKeyMap.put("propId", 4l);
        propKeyMap.put("propValueId", 55l);
        propKeyMapList.add(propKeyMap);


        List<Map<String, Long>> propValueMapList = new LinkedList<Map<String, Long>>();
        Map<String, Long> propValueMap = new HashMap<String, Long>();
        propValueMap.put("propName", 4l);
        propValueMap.put("propValueName", 55l);
        propValueMapList.add(propValueMap);

        skuDto.setPropKey(FastJson.toJson(propKeyMapList));
        skuDto.setPropName(FastJson.toJson(propValueMapList));

        skuDtoList.add(skuDto);

        dataProductDto.setSkuDto(skuDtoList);
        Long id = productRepository.saveDataProductByBops(dataProductDto);
        Assert.assertNotNull(id);

    }

    @Test
    public void deleteProductByBopsTest() throws Exception {
        boolean bool = productRepository.deleteProductByBops(17l);
        Assert.assertEquals(bool, true);

    }

    @Test
    public void updateProductStateByBopsTest() throws Exception {
        boolean bool = productRepository.updateProductStateByBops(17l, "offline");
        Assert.assertEquals(bool, true);

    }

    @Test
    public void queryDataProductListByBopsTest() throws Exception {
        DataProductBopsQueryDto dataProductBopsQueryDto = new DataProductBopsQueryDto();
        dataProductBopsQueryDto.setName("1");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(1);
        pageInfo.setPageSize(20);
        List<DataProductDto> dataProductDtoList = productRepository.queryDataProductListByBops(dataProductBopsQueryDto, pageInfo);
        Assert.assertNotNull(dataProductDtoList);

    }

    @Test
    public void queryDataProductDetailByBops() throws Exception {
        DataProductDto dataProductDto = productRepository.queryDataProductDetailByBops(17l);
        Assert.assertNotNull(dataProductDto);
    }

    @Test
    public void updateDataProductByBopsTest() throws Exception {
        DataProductDto dataProductDto = new DataProductDto();
        dataProductDto.setProductId(17l);
        dataProductDto.setLabel("1,2,3,4,5,6");
        dataProductDto.setDescription("描述性测试！");
        dataProductDto.setPublishTime(new Date());
        dataProductDto.setOnlineType(OnlineTypeEnum.NOW.getCode());
        dataProductDto.setOfflineType(OffLineTypeEnum.FOREVER.getCode());
        List<SkuDto> skuDtoList = new ArrayList<SkuDto>();

        SkuDto skuDto1 = new SkuDto();
        skuDto1.setSkuId(111l);
        skuDto1.setIsDeleted("y");
        skuDto1.setCanUseCoupon("n");
        skuDto1.setComments("哈哈哈哈");
        skuDto1.setOriginalPrice(new BigDecimal(666l));
        skuDto1.setPromotionPrice(new BigDecimal(888l));
        skuDto1.setPromotionStartTime(new Date());
        skuDto1.setPromotionEndTime(new Date());

        SkuDto skuDto = new SkuDto();
        skuDto.setSkuId(112l);
        skuDto.setIsDeleted("n");
        skuDto.setCanUseCoupon("y");
        skuDto.setComments("哈哈哈哈");
        skuDto.setOriginalPrice(new BigDecimal(112l));
        skuDto.setPromotionPrice(new BigDecimal(100l));
        skuDto.setPromotionStartTime(new Date());
        skuDto.setPromotionEndTime(new Date());

        List<Map<String, Long>> propKeyMapList = new LinkedList<Map<String, Long>>();
        Map<String, Long> propKeyMap = new HashMap<String, Long>();
        propKeyMap.put("propId", 4l);
        propKeyMap.put("propValueId", 55l);
        propKeyMapList.add(propKeyMap);


        List<Map<String, String>> propValueMapList = new LinkedList<Map<String, String>>();
        Map<String, String> propValueMap = new HashMap<String, String>();
        propValueMap.put("propName", "金");
        propValueMap.put("propValueName", "黑色");
        propValueMapList.add(propValueMap);

        skuDto1.setPropKey(FastJson.toJson(propKeyMapList));
        skuDto1.setPropName(FastJson.toJson(propValueMapList));

        skuDto.setPropKey(FastJson.toJson(propKeyMapList));
        skuDto.setPropName(FastJson.toJson(propValueMapList));
        skuDtoList.add(skuDto);
        skuDtoList.add(skuDto1);
        dataProductDto.setSkuDto(skuDtoList);
        boolean result = productRepository.updateDataProductByBops(dataProductDto);
        Assert.assertEquals(result, true);

    }

    @Test
    public void queryDataProductListTest() throws Exception {
        DataProductQueryDto dataProductQueryDto = new DataProductQueryDto();
        dataProductQueryDto.setName("1");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(1);
        pageInfo.setPageSize(20);
        List<DataProductDto> dataProductDtoList = productRepository.queryDataProductList(dataProductQueryDto, pageInfo);
        Assert.assertNotNull(dataProductDtoList);

    }

    @Test
    public void updateMyDataProductTest() throws Exception {
        boolean result = productRepository.updateMyDataProduct(17l, "a6097ebd11f962ca5d87401a393b1c66", new BigDecimal(20));
        Assert.assertEquals(result, true);

    }

    @Test
    public void deleteMyDataProductTest() throws Exception {
        boolean result = productRepository.deleteMyDataProduct(17l, "a6097ebd11f962ca5d87401a393b1c66");
        Assert.assertEquals(result, true);
    }

    @Test
    public void saveMyDataProductTest() throws Exception {
        DataProductDto dataProductDto = new DataProductDto();
        dataProductDto.setGmtCreate(new Date());
        dataProductDto.setDataSharePrice(new BigDecimal(1000l));
        dataProductDto.setUserId("5666513d5095600f00184e6c");
        dataProductDto.setName("dota2數據");
        dataProductDto.setClassifyId(2l);
        dataProductDto.setPicFileId("0f6bf9dba3ed16bebd1222b5adfaa3f2,e49763623618ac7c9aeac28ffc493cbf");
        dataProductDto.setMainPicId("a6097ebd11f962ca5d87401a393b1c47");
        dataProductDto.setLabel("4,6,7");
        dataProductDto.setDescription("description");
        dataProductDto.setPublishTime(new Date());
        dataProductDto.setSeoTitle("123title");
        dataProductDto.setSeoKeyword("123keyword");
        dataProductDto.setSeoDescription("123description");
        dataProductDto.setArticleId("1");
        dataProductDto.setOnlineType("delay");
        dataProductDto.setOnlineTime(new Date());
        dataProductDto.setOfflineType("forever");

        List<DataModelProductDto> dataModelProductDtoList = new ArrayList<DataModelProductDto>();
        DataModelProductDto dataModelProductDto = new DataModelProductDto();
        dataModelProductDto.setDfsId("0f6bf9dba3ed16bebd1222b5adfaa3f2");
        dataModelProductDto.setFileLength(111l);
        dataModelProductDto.setSizeDesc("111mb");
        dataModelProductDto.setFullName("a.stl");
        dataModelProductDtoList.add(dataModelProductDto);
        dataProductDto.setDataModelProductDtoDtoList(dataModelProductDtoList);

        List<SkuDto> skuDtoList = new ArrayList<SkuDto>();
        SkuDto skuDto = new SkuDto();
        skuDto.setCanUseCoupon("y");
        skuDto.setComments("test");
        skuDto.setOriginalPrice(new BigDecimal(112l));
        skuDto.setPromotionPrice(new BigDecimal(100l));
        skuDto.setPromotionStartTime(new Date());
        skuDto.setPromotionEndTime(new Date());

        List<Map<String, Long>> propKeyMapList = new LinkedList<Map<String, Long>>();
        Map<String, Long> propKeyMap = new HashMap<String, Long>();
        propKeyMap.put("propId", 4l);
        propKeyMap.put("propValueId", 55l);
        propKeyMapList.add(propKeyMap);


        List<Map<String, Long>> propValueMapList = new LinkedList<Map<String, Long>>();
        Map<String, Long> propValueMap = new HashMap<String, Long>();
        propValueMap.put("propName", 4l);
        propValueMap.put("propValueName", 55l);
        propValueMapList.add(propValueMap);

        skuDto.setPropKey(FastJson.toJson(propKeyMapList));
        skuDto.setPropName(FastJson.toJson(propValueMapList));

        skuDtoList.add(skuDto);

        dataProductDto.setSkuDto(skuDtoList);
        Long id = productRepository.saveMyDataProduct(dataProductDto);
        Assert.assertNotNull(id);
    }

    @Test
    public void saveSingleProductByBopsTest() throws Exception {
        SingleProductDto singleProductDto = new SingleProductDto();
        singleProductDto.setName("设备asdfasdf啊啊啊啊");
        singleProductDto.setPicFileId("66667ebd11f962ca5d87401a393b1c47");
        singleProductDto.setMainPicId("88888ebd11f962ca5d87401a393b1c47");
        singleProductDto.setDescription("描述aaaaa");
        singleProductDto.setSingleProductType("device");
        singleProductDto.setPublishTime(new Date());
        singleProductDto.setOnlineType("delay");
        singleProductDto.setOnlineTime(DateTime.now().plusMinutes(5).toDate());
        singleProductDto.setOfflineType("forever");

        List<SkuDto> skuDtoList = new ArrayList<SkuDto>();
        SkuDto skuDto = new SkuDto();
        skuDto.setCanUseCoupon("y");
        skuDto.setComments("无敌设备001asdfasdf");
        skuDto.setOriginalPrice(new BigDecimal(112l));
        skuDto.setPromotionPrice(new BigDecimal(100l));
        skuDto.setPromotionStartTime(new Date());
        skuDto.setPromotionEndTime(new Date());
        skuDto.setUnitNum(2.12);

        List<Map<String, Long>> propKeyMapList = new LinkedList<Map<String, Long>>();

        Map<String, Long> propKeyMap = new HashMap<String, Long>();
        propKeyMap.put("propId", 6l);
        propKeyMap.put("propValueId", 40l);
        Map<String, Long> propKeyTwoMap = new HashMap<String, Long>();
        propKeyTwoMap.put("propId", 2l);
        propKeyTwoMap.put("propValueId", 45l);

        propKeyMapList.add(propKeyMap);
        propKeyMapList.add(propKeyTwoMap);


        List<Map<String, String>> propValueMapList = new LinkedList<Map<String, String>>();

        Map<String, String> propValueMap = new HashMap<String, String>();
        propValueMap.put("propName", "型号");
        propValueMap.put("propValueName", "EinScan-Pro");
        Map<String, String> propValueTwoMap = new HashMap<String, String>();
        propValueTwoMap.put("propName", "颜色");
        propValueTwoMap.put("propValueName", "浅粉色");

        propValueMapList.add(propValueMap);
        propValueMapList.add(propValueTwoMap);

        skuDto.setPropKey(FastJson.toJson(propKeyMapList));
        skuDto.setPropName(FastJson.toJson(propValueMapList));

        skuDtoList.add(skuDto);

        singleProductDto.setSkuDtoList(skuDtoList);

        System.out.println(FastJson.toJson(singleProductDto));
        Long id = productRepository.saveSingleProductByBops(singleProductDto);
        Assert.assertNotNull(id);
    }


    @Test
    public void querySingleProductListByBopsTest() throws Exception {
        SingleProductQueryDto singleProductQueryDto = new SingleProductQueryDto();
        singleProductQueryDto.setName("111");
        singleProductQueryDto.setSingleProductType("device");
        singleProductQueryDto.setState("offline");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(10);
        pageInfo.setPageIndex(1);

        List<SingleProductDto> singleProductDtoList = productRepository.querySingleProductListByBops(singleProductQueryDto, pageInfo);
        Assert.assertNotNull(singleProductDtoList);
    }

    @Test
    public void querySingleProductDetailByBopsTest() throws Exception {
        SingleProductDto singleProductDto = productRepository.querySingleProductDetailByBops(33l);
        Assert.assertNotNull(singleProductDto);

    }


    @Test
    public void updateSingleProductByBopsTest() throws Exception {
        SingleProductDto singleProductDto = new SingleProductDto();
        singleProductDto.setProductId(38l);
        singleProductDto.setSingleProductType("device");
        singleProductDto.setName("设备NNNNNNNNN");

        List<SkuDto> skuDtoList = new ArrayList<SkuDto>();
        SkuDto skuDto = new SkuDto();
        skuDto.setCanUseCoupon("n");
        skuDto.setComments("asdfasdf");
        skuDto.setOriginalPrice(new BigDecimal(999l));
        skuDto.setPromotionPrice(new BigDecimal(888l));
        skuDto.setPromotionStartTime(new Date());
        skuDto.setPromotionEndTime(new Date());

        List<Map<String, Long>> propKeyMapList = new LinkedList<Map<String, Long>>();
        Map<String, Long> propKeyMap = new HashMap<String, Long>();
        propKeyMap.put("propId", 6l);
        propKeyMap.put("propValueId", 40l);


        propKeyMapList.add(propKeyMap);

        List<Map<String, String>> propValueMapList = new LinkedList<Map<String, String>>();
        Map<String, String> propValueMap = new HashMap<String, String>();
        propValueMap.put("propName", "型号");
        propValueMap.put("propValueName", "EinScan-Pro");

        propValueMapList.add(propValueMap);

        skuDto.setPropKey(FastJson.toJson(propKeyMapList));
        skuDto.setPropName(FastJson.toJson(propValueMapList));

        skuDtoList.add(skuDto);

        singleProductDto.setSkuDtoList(skuDtoList);

        boolean res = productRepository.updateSingleProductByBops(singleProductDto);
        Assert.assertEquals(res, true);

    }


    @Test
    public void savePackageProductByBopsTest() throws Exception {
        PackageProductDto packageProductDto = new PackageProductDto();
        packageProductDto.setName("牛逼套餐hhhhh");
        packageProductDto.setPicFileId("66667ebd11f962ca5d87401a393b1c47");
        packageProductDto.setMainPicId("88888ebd11f962ca5d87401a393b1c47");
        packageProductDto.setDescription("套餐描述");
        packageProductDto.setPublishTime(new Date());
        packageProductDto.setOnlineType("delay");
        packageProductDto.setOnlineTime(DateTime.now().plusMinutes(5).toDate());
        packageProductDto.setOfflineType("forever");

        List<SkuDto> skuDtoList = new ArrayList<SkuDto>();
        SkuDto skuDto = new SkuDto();
        skuDto.setCanUseCoupon("y");
        skuDto.setComments("套餐");
        skuDto.setOriginalPrice(new BigDecimal(112l));
        skuDto.setPromotionPrice(new BigDecimal(100l));
        skuDto.setPromotionStartTime(new Date());
        skuDto.setPromotionEndTime(new Date());

        skuDtoList.add(skuDto);

        List<ProductPackageRelationDto> productPackageRelationDtoList = new ArrayList<ProductPackageRelationDto>();
        ProductPackageRelationDto productPackageRelationDto = new ProductPackageRelationDto();
        productPackageRelationDto.setProductId(25l);
        productPackageRelationDto.setNum(1);
        productPackageRelationDtoList.add(productPackageRelationDto);

        packageProductDto.setProductPackageRelationDtoList(productPackageRelationDtoList);
        packageProductDto.setSkuDtoList(skuDtoList);
        Long id = productRepository.savePackageProductByBops(packageProductDto);
        Assert.assertNotNull(id);

    }

    @Test
    public void queryPackageProductListByBopsTest() throws Exception {
        PackageProductQueryDto packageProductQueryDto = new PackageProductQueryDto();
        packageProductQueryDto.setName("套");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(10);
        pageInfo.setPageIndex(1);

        List<PackageProductDto> productDtoList = productRepository.queryPackageProductListByBops(packageProductQueryDto, pageInfo);
        Assert.assertNotNull(productDtoList);

    }

    @Test
    public void queryPackageProductDetailByBopsTest() throws Exception {
        PackageProductDto packageProductDto = productRepository.queryPackageProductDetailByBops(44l);
        Assert.assertNotNull(packageProductDto);
    }

    @Test
    public void updatePackageProductByBopsTest() throws Exception {
        PackageProductDto packageProductDto = new PackageProductDto();
        packageProductDto.setProductId(44l);
        packageProductDto.setName("牛逼套餐1111");
        packageProductDto.setOnlineType(OnlineTypeEnum.NOW.getCode());
        packageProductDto.setOfflineType(OffLineTypeEnum.FOREVER.getCode());

        List<SkuDto> skuDtoList = new ArrayList<SkuDto>();
        SkuDto skuDto = new SkuDto();
        skuDto.setSkuId(127l);
        skuDto.setCanUseCoupon("y");
        skuDto.setOriginalPrice(new BigDecimal(112l));
        skuDto.setPromotionPrice(new BigDecimal(100l));
        skuDto.setPromotionStartTime(new Date());
        skuDto.setPromotionEndTime(new Date());

        skuDtoList.add(skuDto);

        List<ProductPackageRelationDto> productPackageRelationDtoList = new ArrayList<ProductPackageRelationDto>();
        ProductPackageRelationDto productPackageRelationDto = new ProductPackageRelationDto();
        productPackageRelationDto.setProductId(26l);
        productPackageRelationDto.setNum(2);
        productPackageRelationDtoList.add(productPackageRelationDto);

        packageProductDto.setProductPackageRelationDtoList(productPackageRelationDtoList);
        packageProductDto.setSkuDtoList(skuDtoList);

        boolean res = productRepository.updatePackageProductByBops(packageProductDto);
        Assert.assertEquals(res, true);
    }

    @Test
    public void queryProductByProductIdTest() throws Exception {
        ProductDto productDto = productRepository.queryProductByProductId(44l);
        Assert.assertNotNull(productDto);
    }

    @Test
    public void queryProductByParamTest() throws Exception {
        ProductQueryDto productQueryDto = new ProductQueryDto();
        productQueryDto.setName("123");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(10);
        pageInfo.setPageIndex(1);
        List<ProductDto> productDtoList = productRepository.queryProductByBops(productQueryDto, pageInfo);
        Assert.assertNotNull(productDtoList);
    }


    @Test
    public void queryDataProductDetailByDataIdTest() throws Exception {
        DataProductDto dataProductDto = productRepository.queryDataProductDetailByDataId(1000067l);
        Assert.assertNotNull(dataProductDto);
    }

    @Test
    public void queryAllSingleProductListTest() throws Exception {
        List<SingleProductDto> singleProductDtoList = productRepository.queryAllSingleProductList();
        Assert.assertNotNull(singleProductDtoList);

    }

    @Test
    public void queryAllPackageProductListTest() throws Exception {
        List<PackageProductDto> packageProductDtoList = productRepository.queryAllPackageProductList();
        Assert.assertNotNull(packageProductDtoList);
    }
}
