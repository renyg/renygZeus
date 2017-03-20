package com.shining3d.zeus.core.test;

import com.shining3d.zeus.service.ShoppingCartService;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import javax.annotation.Resource;

/**
 * Created by fe on 2016/12/7.
 */
@Commit
//@Rollback
@ContextConfiguration(locations = "classpath:spring.xml")
public class BaseTest extends AbstractTransactionalTestNGSpringContextTests {

    @Resource
    private ShoppingCartService shoppingCartService;

    @BeforeMethod
    public void before() {

    }
}


