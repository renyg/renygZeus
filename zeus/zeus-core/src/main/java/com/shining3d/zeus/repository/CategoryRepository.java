package com.shining3d.zeus.repository;

import com.shining3d.common.Assert;
import com.shining3d.common.BeanHelper;
import com.shining3d.zeus.client.dto.CategoryDto;
import com.shining3d.zeus.dao.CategoryDao;
import com.shining3d.zeus.entity.CategoryEntity;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by fe on 2017/1/16.
 */
@Repository
public class CategoryRepository {

    public static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @Resource
    private CategoryDao categoryDao;

    public CategoryDto queryByCode(String code) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(code);

            CategoryEntity categoryEntity = categoryDao.queryByCode(code);
            CategoryDto categoryDto = null;
            if (categoryEntity != null) {
                categoryDto = new CategoryDto();
                BeanHelper.copyProperties(categoryDto,categoryEntity);
            }
            return categoryDto;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryByCode error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }

    public CategoryDto queryByCategoryId(Long categoryId) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(categoryId);

            CategoryEntity categoryEntity = categoryDao.selectByPrimaryKey(categoryId);
            CategoryDto categoryDto = null;
            if (categoryEntity != null) {
                categoryDto = new CategoryDto();
                BeanHelper.copyProperties(categoryDto,categoryEntity);
            }
            return categoryDto;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryByCategoryId error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }
}
