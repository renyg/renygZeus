package com.shining3d.zeus.core.test.repository;

import com.shining3d.zeus.client.dto.PropDto;
import com.shining3d.zeus.core.test.BaseTest;
import com.shining3d.zeus.repository.PropRepository;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fe on 2017/1/6.
 */
public class PropRepositoryTest extends BaseTest {

    @Resource
    private PropRepository propRepository;

    @Test
    public void queryPropListByCategoryIdTest() throws Exception {
        List<PropDto> propDtoList = propRepository.queryPropListByCategoryId(2l);
        Assert.assertNotNull(propDtoList);

    }
}

