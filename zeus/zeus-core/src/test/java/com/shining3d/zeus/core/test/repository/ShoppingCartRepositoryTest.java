package com.shining3d.zeus.core.test.repository;

import com.shining3d.zeus.client.dto.ShoppingCartDto;
import com.shining3d.zeus.core.test.BaseTest;
import com.shining3d.zeus.repository.ShoppingCartRepository;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fe on 2017/1/6.
 */
public class ShoppingCartRepositoryTest extends BaseTest {

    @Resource
    private ShoppingCartRepository shoppingCartRepository;



    @Test
    public void saveSkuToShoppingCartTest() throws Exception {
        Long result = shoppingCartRepository.saveSkuToShoppingCart("5825348f9891ea00071fcb8f",100l,1);
        Assert.assertNotNull(result);
    }


    @Test
    public void queryMyAllShoppingCartTest() throws Exception {
        List<ShoppingCartDto> shoppingCartDtoList = shoppingCartRepository.queryMyAllShoppingCart("5825348f9891ea00071fcb8f");
        Assert.assertNotNull(shoppingCartDtoList);
    }

    @Test
    public void deleteSkuFromShoppingCartTest() throws Exception {
        boolean result = shoppingCartRepository.deleteSkuFromShoppingCart(103l,"gdfgrtrtyRTYFJKqweqwe");
        Assert.assertEquals(result,true);

    }


    @Test
    public void updateShoppingCartSkuNumTest() throws Exception {
        boolean result = shoppingCartRepository.updateShoppingCartSkuNum("5825348f9891ea00071fcb8f",100l,1);
        Assert.assertEquals(result,true);
    }

    @Test
    public void clearShoppingCartTest() throws Exception {
        boolean result = shoppingCartRepository.clearShoppingCart("asdfasoiuyqwerqwer1234kjh");
        Assert.assertEquals(result,true);
    }

    @Test
    public void batchClearShoppingCartTest() throws Exception {
        boolean result = shoppingCartRepository.clearShoppingCart("1", Arrays.asList(355l,352l));
        Assert.assertEquals(result,true);
    }

}
