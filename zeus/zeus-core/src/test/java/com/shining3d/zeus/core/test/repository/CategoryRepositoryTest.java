package com.shining3d.zeus.core.test.repository;

import com.shining3d.zeus.client.dto.CategoryDto;
import com.shining3d.zeus.core.test.BaseTest;
import com.shining3d.zeus.repository.CategoryRepository;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * Created by fe on 2017/1/16.
 */
public class CategoryRepositoryTest extends BaseTest {

    @Resource
    private CategoryRepository categoryRepository;

    @Test
    public void queryByCodeTest() throws Exception {
        CategoryDto categoryDto = categoryRepository.queryByCode("product_data");
        Assert.assertNotNull(categoryDto);

    }

    @Test
    public void queryByCategoryIdTest() throws Exception {
        CategoryDto categoryDto = categoryRepository.queryByCategoryId(1l);
        Assert.assertNotNull(categoryDto);

    }
}
