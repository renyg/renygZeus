package com.shining3d.zeus.repository;

import com.shining3d.common.dto.OrderByInfo;
import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.DeviceModelDto;
import com.shining3d.zeus.client.dto.MaterialRecordationServiceDto;
import com.shining3d.zeus.client.dto.ModelMaterialRelationDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.DeviceModelDao;
import com.shining3d.zeus.dao.MaterialRecordationServiceDao;
import com.shining3d.zeus.dao.ModelMaterialRelationDao;
import com.shining3d.zeus.entity.DeviceModelEntity;
import com.shining3d.zeus.entity.ModelMaterialRelationEntity;
import com.shining3d.zeus.enums.MaterialFilingEnum;
import com.shining3d.zeus.enums.MaterialTypeEnum;
import com.shining3d.zeus.enums.PrintEnum;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by renyg on 2017/1/4.
 */
@Repository
public class DeviceModelRepository {

    @Resource
    private DeviceModelDao deviceModelDao;

    @Resource
    private ModelMaterialRelationDao materialRelationDao;

    @Resource
    private MaterialRecordationServiceDao materialRecordationServiceDao;

    /**
     * @param record
     * @param list
     * @throws BizException
     * @throws RepositoryException
     */
    public void insertSelective(DeviceModelDto record, List<ModelMaterialRelationDto> list) throws BizException, RepositoryException {
        if (null == record
                || StringUtils.isEmpty(record.getModelName())
                || null == record.getSupplierId()
                || StringUtils.isEmpty(record.getBuildSizeReal())) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        //按照供应商和设备名称做唯一约束
        DeviceModelDto d = new DeviceModelDto();
        d.setSupplierId(record.getSupplierId());
        d.setModelName(record.getModelName());
        List<DeviceModelDto> dmList = deviceModelDao.getDeviceModel(d, new PageInfo());
        if (!CollectionUtils.isEmpty(dmList)) {
            throw new BizException(BizConstant.COMMON_RECORD_EXIST_ERROR, BizConstant.COMMON_RECORD_EXIST_MSG_ERROR);
        }

        if ((record.getType().equals(PrintEnum.PRINT.getCode())
                && StringUtils.isEmpty(record.getBuildSizeTheory()))
                || null == list || list.size() < 0) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        try {
            DeviceModelEntity deviceModelEntity = new DeviceModelEntity();
            BeanUtils.copyProperties(deviceModelEntity, record);
            deviceModelEntity.setBuildSizeRealX(new BigDecimal(deviceModelEntity.getBuildSizeReal().split("&")[0]));
            deviceModelEntity.setBuildSizeRealY(new BigDecimal(deviceModelEntity.getBuildSizeReal().split("&")[1]));
            deviceModelEntity.setBuildSizeRealZ(new BigDecimal(deviceModelEntity.getBuildSizeReal().split("&")[2]));
            deviceModelDao.insertSelective(deviceModelEntity);
            record.setId(deviceModelEntity.getId());
            List<ModelMaterialRelationEntity> entityList = new ArrayList();
            for (ModelMaterialRelationDto dto : list) {
                ModelMaterialRelationEntity modelMaterialRelationEntity = new ModelMaterialRelationEntity();
                dto.setDeviceId(deviceModelEntity.getId());
                BeanUtils.copyProperties(modelMaterialRelationEntity, dto);
                entityList.add(modelMaterialRelationEntity);
            }
            materialRelationDao.batchInsert(entityList);
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }
    }

    /**
     * @param record
     * @param list
     * @throws BizException
     * @throws RepositoryException
     */
    public void updateByPrimaryKeySelective(DeviceModelDto record, List<ModelMaterialRelationDto> list) throws BizException, RepositoryException {
        if (null == record || null == record.getId()
                || null == record.getSupplierId()
                || StringUtils.isEmpty(record.getModelName())) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        //按照供应商和设备名称做唯一约束
        DeviceModelDto d = new DeviceModelDto();
        d.setSupplierId(record.getSupplierId());
        d.setModelName(record.getModelName());
        List<DeviceModelDto> dmList = deviceModelDao.getDeviceModel(d, new PageInfo());
        if (!CollectionUtils.isEmpty(dmList)&&dmList.get(0).getId() != record.getId()) {
            throw new BizException(BizConstant.COMMON_RECORD_EXIST_ERROR, BizConstant.COMMON_RECORD_EXIST_MSG_ERROR);
        }
        try {
            DeviceModelEntity deviceModelEntity = new DeviceModelEntity();
            BeanUtils.copyProperties(deviceModelEntity, record);
            if (StringUtils.isNotEmpty(deviceModelEntity.getBuildSizeReal())) {
                deviceModelEntity.setBuildSizeRealX(new BigDecimal(deviceModelEntity.getBuildSizeReal().split("&")[0]));
                deviceModelEntity.setBuildSizeRealY(new BigDecimal(deviceModelEntity.getBuildSizeReal().split("&")[1]));
                deviceModelEntity.setBuildSizeRealZ(new BigDecimal(deviceModelEntity.getBuildSizeReal().split("&")[2]));
            }
            deviceModelDao.updateByPrimaryKeySelective(deviceModelEntity);

            List<Long> l = new ArrayList<Long>();
            l.add(record.getId());
            materialRelationDao.batchDeleteByDeviceId(l);
            List<ModelMaterialRelationEntity> entityList = new ArrayList();

            for (ModelMaterialRelationDto dto : list) {
                ModelMaterialRelationEntity modelMaterialRelationEntity = new ModelMaterialRelationEntity();
                dto.setDeviceId(record.getId());
                BeanUtils.copyProperties(modelMaterialRelationEntity, dto);
                entityList.add(modelMaterialRelationEntity);
            }
            materialRelationDao.batchInsert(entityList);
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }
    }

    /**
     * @param ids
     * @throws BizException
     * @throws RepositoryException
     */
    public void batchDeleteByIds(String ids) throws BizException, RepositoryException {
        if (StringUtils.isEmpty(ids)) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        try {
            List<Long> list = new ArrayList<Long>();
            for (String s : ids.split(";")) {
                list.add(Long.parseLong(s));
            }
            deviceModelDao.batchDeleteByIds(list);
            materialRelationDao.batchDeleteByDeviceId(list);
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }
    }

    public void batchUpdateModelStatus(String ids, String status) throws BizException, RepositoryException {
        if (StringUtils.isEmpty(ids) || StringUtils.isEmpty(status)) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR);
        }
        try {
            List<Long> list = new ArrayList<Long>();
            for (String s : ids.split(";")) {
                list.add(Long.parseLong(s));
            }
            deviceModelDao.batchUpdateModelStatus(list, status);
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }

    }

    /**
     * @param deviceModelDto
     * @param pageInfo
     * @return
     * @throws BizException
     * @throws RepositoryException
     */
    public List<DeviceModelDto> getDeviceModel(DeviceModelDto deviceModelDto, PageInfo pageInfo) throws BizException, RepositoryException {
        if (null == pageInfo) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        try {
            int count = deviceModelDao.getCountDeviceModel(deviceModelDto);
            pageInfo.setRowCount(count);
            OrderByInfo o = new OrderByInfo("gmt_create", OrderByInfo.SORT_DIRECTION_DESC);
            List<OrderByInfo> li = new ArrayList<OrderByInfo>();
            li.add(o);
            pageInfo.setOrderByList(li);
            List<DeviceModelDto> list = deviceModelDao.getDeviceModel(deviceModelDto, pageInfo);
            return list;
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }
    }


    /**
     * @param deviceModelDto
     * @param pageInfo
     * @return
     * @throws BizException
     * @throws RepositoryException
     */
    public List<DeviceModelDto> getDeviceModelList(DeviceModelDto deviceModelDto, PageInfo pageInfo) throws BizException, RepositoryException {
        if (null == pageInfo) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        try {
            int count = deviceModelDao.getCountDeviceModelList(deviceModelDto);
            pageInfo.setRowCount(count);
            OrderByInfo o = new OrderByInfo("gmt_create", OrderByInfo.SORT_DIRECTION_DESC);
            List<OrderByInfo> li = new ArrayList<OrderByInfo>();
            li.add(o);
            pageInfo.setOrderByList(li);
            List<DeviceModelDto> list = deviceModelDao.getDeviceModelList(deviceModelDto, pageInfo);
            return list;
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }
    }


    /**
     * 提供给添加供应商页面增加时的列表使用
     *
     * @return
     * @throws BizException
     * @throws RepositoryException
     */
    public List<DeviceModelDto> getDeviceModelByCondition(String userId, String modelName, Long supplierId)
            throws BizException, RepositoryException {
        try {

            List<DeviceModelDto> list = deviceModelDao.getDeviceModelByCondition(modelName, supplierId);
            for (DeviceModelDto dto : list) {
                if (StringUtils.isNotEmpty(dto.getPrecisionsName())) {
                    String[] ps = dto.getPrecisionsName().split(",");
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < ps.length; i++) {
                        String precisions = ps[i].split(":")[1];
                        MaterialRecordationServiceDto mDto = new MaterialRecordationServiceDto();
                        mDto.setRecordMaterialId(dto.getMaterialId());
                        mDto.setPrecisions(precisions);
                        mDto.setUserId(userId);
                        List<MaterialRecordationServiceDto> l = materialRecordationServiceDao.getMaterialRecordationCondition(mDto, new PageInfo());
                        if (l.size() > 0) {
                            if(l.get(0).getStatus().equals(MaterialTypeEnum.PASS.getCode())){
                                ps[i] = ps[i] + "&" + MaterialFilingEnum.FILED.getText();
                            }else{
                                ps[i] = ps[i] + "&" + MaterialFilingEnum.FILING.getText();
                            }
                        }else {
                            ps[i] = ps[i] + "&" + MaterialFilingEnum.NOFILE.getText();
                        }
                        sb.append(ps[i]+";");
                    }
                    dto.setPrecisionsName(sb.toString());
                }
            }
            return list;
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }
    }

    public List<ModelMaterialRelationDto> getMateialRealtionByPrecisionIdAndMaterialId(Long precisionId, Long marterialId) throws BizException, RepositoryException {
        if (null == precisionId && null == marterialId) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        try {
            List<ModelMaterialRelationDto> list = materialRelationDao.getMateialRealtionByPrecisionIdAndMaterialId(precisionId,marterialId);
            return list;
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }
    }
}
