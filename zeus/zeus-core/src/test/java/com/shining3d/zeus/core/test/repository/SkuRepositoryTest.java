package com.shining3d.zeus.core.test.repository;

import com.shining3d.zeus.client.dto.SkuDto;
import com.shining3d.zeus.core.test.BaseTest;
import com.shining3d.zeus.repository.SkuRepository;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * Created by fe on 2017/1/6.
 */
public class SkuRepositoryTest extends BaseTest {

    @Resource
    private SkuRepository skuRepository;

    @Test
    public void querySkuBySkuIdTest() throws Exception {
        SkuDto skuDto = skuRepository.querySkuBySkuId(127l);
        Assert.assertNotNull(skuDto);

    }


}
