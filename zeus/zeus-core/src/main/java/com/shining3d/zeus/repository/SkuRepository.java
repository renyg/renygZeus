package com.shining3d.zeus.repository;

import com.shining3d.common.Assert;
import com.shining3d.common.BeanHelper;
import com.shining3d.zeus.client.dto.SkuDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.SkuDao;
import com.shining3d.zeus.entity.SkuEntity;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by fe on 2017/1/6.
 */
@Repository
public class SkuRepository {
    public static final Logger logger = LoggerFactory.getLogger(SkuRepository.class);

    @Resource
    private SkuDao skuDao;

    public SkuDto querySkuBySkuId(Long skuId) throws BizException, RepositoryException {
        try {
            Assert.assertNotNull(Arrays.asList(skuId), BizConstant.COMMON_PARAM_ERROR,"参数错误!");
            SkuEntity skuEntity = skuDao.selectByPrimaryKey(skuId);
            SkuDto skuDto = null;
            if (skuEntity != null) {
                skuDto = new SkuDto();
                BeanHelper.copyProperties(skuDto,skuEntity);
                skuDto.setSkuId(skuEntity.getId());
            }

            return skuDto;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("querySkuBySkuId error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }
}
