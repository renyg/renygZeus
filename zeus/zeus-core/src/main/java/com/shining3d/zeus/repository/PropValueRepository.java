package com.shining3d.zeus.repository;

import com.shining3d.zeus.client.dto.PropValueDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.PropValueDao;
import com.shining3d.zeus.entity.PropValueEntity;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fe on 2016/12/26.
 */
@Repository
public class PropValueRepository {

    public static final Logger logger = LoggerFactory.getLogger(PropValueRepository.class);

    @Resource
    private PropValueDao propValueDao;

    public List<PropValueDto> queryPropValueListByPropName(Long categoryId, String propName) throws BizException,RepositoryException {
        try {
            if(categoryId == null || StringUtils.isEmpty(propName)) throw new BizException(BizConstant.COMMON_PARAM_ERROR,"参数缺失");

            List<PropValueEntity> propValueEntityList = propValueDao.queryPropValueListByParam(categoryId,propName);

            List<PropValueDto> propValueDtoList = new LinkedList<PropValueDto>();
            if (CollectionUtils.isNotEmpty(propValueEntityList)) {
                propValueEntityList.forEach(item -> {
                    PropValueDto propValueDto = new PropValueDto();
                    try {
                        BeanUtils.copyProperties(propValueDto,item);
                        propValueDto.setPropValueId(item.getId());
                        propValueDtoList.add(propValueDto);
                    } catch (IllegalAccessException e) {
                        throw new BizException(BizConstant.COMMON_PARAM_ERROR,"copy异常");
                    } catch (InvocationTargetException e) {
                        throw new BizException(BizConstant.COMMON_PARAM_ERROR,"copy异常");
                    }
                });
            }

            return propValueDtoList;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryPropValueListByPropName error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }



}
