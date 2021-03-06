package com.shining3d.zeus.repository;

import com.shining3d.zeus.client.dto.PropDto;
import com.shining3d.zeus.client.dto.PropValueDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.PropDao;
import com.shining3d.zeus.dao.PropValueDao;
import com.shining3d.zeus.entity.PropEntity;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by fe on 2016/12/26.
 */
@Repository
public class PropRepository {

    public static final Logger logger = LoggerFactory.getLogger(PropRepository.class);

    @Resource
    private PropDao propDao;

    public List<PropDto> queryPropListByCategoryId(Long categoryId) throws BizException, RepositoryException {
        try {
            if(categoryId == null) throw new BizException(BizConstant.COMMON_PARAM_ERROR,"参数缺失");

            List<PropEntity> propEntityList = propDao.queryAllPropEntityByCategoryId(categoryId);

            List<PropDto> propDtoList = new ArrayList<PropDto>();
            Optional.ofNullable(propEntityList).ifPresent(list -> {
                list.forEach(item -> {
                    PropDto propDto = new PropDto();
                    try {
                        BeanUtils.copyProperties(propDto,item);
                        propDto.setPropId(item.getId());
                        propDtoList.add(propDto);
                    } catch (IllegalAccessException e) {
                        throw new BizException(BizConstant.COMMON_PARAM_ERROR,"copy异常");
                    } catch (InvocationTargetException e) {
                        throw new BizException(BizConstant.COMMON_PARAM_ERROR,"copy异常");
                    }
                });
            });

            return propDtoList;
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("queryPropValueListByPropName error ,e : {}", e);
            throw new RepositoryException(e);
        }
    }
}
