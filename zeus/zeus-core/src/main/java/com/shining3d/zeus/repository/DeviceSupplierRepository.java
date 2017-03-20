package com.shining3d.zeus.repository;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.DeviceSupplierDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.DeviceSupplierDao;
import com.shining3d.zeus.entity.DeviceSupplierEntity;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by renyg on 2017/1/4.
 */
@Repository
public class DeviceSupplierRepository {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MaterialRepository.class);

    @Resource
    DeviceSupplierDao deviceSupplierDao;

    /**
     * @param ids
     * @throws RepositoryException
     */
    public void deleteBatchByIds(List<Long> ids) throws RepositoryException {
        try {
            deviceSupplierDao.deleteBatchByIds(ids);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }

    /**
     * @param record
     * @throws BizException
     * @throws RepositoryException
     */
    public void insertSelective(DeviceSupplierDto record) throws BizException, RepositoryException {

        DeviceSupplierDto dto = deviceSupplierDao.selectByName(record.getName());
        if (null !=dto && dto.getName().equals(record.getName())) {
            logger.error("记录已经存在！");
            throw new BizException(BizConstant.COMMON_RECORD_EXIST_ERROR,BizConstant.COMMON_RECORD_EXIST_MSG_ERROR);
        }
        try {
            DeviceSupplierEntity deviceSupplierEntity = new DeviceSupplierEntity();
            BeanUtils.copyProperties(deviceSupplierEntity, record);
            deviceSupplierDao.insertSelective(deviceSupplierEntity);
            record.setId(deviceSupplierEntity.getId());
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR,e);
        }

    }

    /**
     * @param record
     * @throws BizException
     * @throws RepositoryException
     */
    public void updateByPrimaryKeySelective(DeviceSupplierDto record) throws BizException, RepositoryException {
        DeviceSupplierDto dto = deviceSupplierDao.selectByName(record.getName());
        if (null != dto && dto.getName().equals(record.getName()) && dto.getId() != record.getId()) {
            logger.error("记录已经存在！");
            throw new BizException(BizConstant.COMMON_RECORD_EXIST_ERROR,BizConstant.COMMON_RECORD_EXIST_MSG_ERROR);

        }
        try {
            DeviceSupplierEntity deviceSupplierEntity = new DeviceSupplierEntity();
            BeanUtils.copyProperties(deviceSupplierEntity, record);
            deviceSupplierDao.updateByPrimaryKeySelective(deviceSupplierEntity);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }

    /**
     * @param deviceSupplierDto
     * @return
     * @throws RepositoryException
     */

    public int countDeviceSupplierByCondition(DeviceSupplierDto deviceSupplierDto) throws RepositoryException {
        try {
            return deviceSupplierDao.countDeviceSupplierByCondition(deviceSupplierDto);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }
    }

    /**
     * @param deviceSupplierDto
     * @param pageInfo
     * @return
     * @throws RepositoryException
     */
    public List<DeviceSupplierDto> queryDeviceSupplierByCondition(DeviceSupplierDto deviceSupplierDto, PageInfo pageInfo) throws RepositoryException {
        try {
            return deviceSupplierDao.queryDeviceSupplierByCondition(deviceSupplierDto, pageInfo);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }
    }
}
