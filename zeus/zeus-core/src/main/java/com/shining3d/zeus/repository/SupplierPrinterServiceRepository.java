package com.shining3d.zeus.repository;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.shining3d.common.dto.OrderByInfo;
import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.*;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.MaterialPrecisionDao;
import com.shining3d.zeus.dao.MaterialRecordationServiceDao;
import com.shining3d.zeus.dao.SupplierPrinterServiceDao;
import com.shining3d.zeus.entity.MaterialRecordationServiceEntity;
import com.shining3d.zeus.entity.SupplierPrinterServiceEntity;
import com.shining3d.zeus.enums.MaterialFilingEnum;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by renyg on 2017/1/4.
 */
@Repository
public class SupplierPrinterServiceRepository {

    public static final Logger logger = LoggerFactory.getLogger(SupplierPrinterServiceRepository.class);

    @Resource
    private SupplierPrinterServiceDao supplierPrinterServiceDao;

    @Resource
    private MaterialRecordationServiceDao materialRecordationServiceDao;
    @Resource
    private MaterialPrecisionDao materialPrecisionDao;

    /**
     * @param id
     * @throws BizException
     * @throws RepositoryException
     */
    public void deleteById(Long id) throws BizException, RepositoryException {
        if (null == id) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR);
        }
        try {
            supplierPrinterServiceDao.deleteByPK(id);
        } catch (Exception e) {
            logger.error("delete is error!" + e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }

    /**
     * @param supplierPrinterServiceDto
     * @throws BizException
     * @throws RepositoryException
     */
    public void insert(SupplierPrinterServiceDto supplierPrinterServiceDto) throws BizException, RepositoryException {
        if (null == supplierPrinterServiceDto
                || null == supplierPrinterServiceDto.getSupplierId()
                || StringUtils.isEmpty(supplierPrinterServiceDto.getDeviceAsName())
                || StringUtils.isEmpty(supplierPrinterServiceDto.getUserId())
                || null == supplierPrinterServiceDto.getAdapterMaterialId()
                || StringUtils.isEmpty(supplierPrinterServiceDto.getColor())
                || StringUtils.isEmpty(supplierPrinterServiceDto.getPrecisions())) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);

        }
        SupplierPrinterServiceDto s = new SupplierPrinterServiceDto();
        s.setUserId(supplierPrinterServiceDto.getUserId());
        s.setAdapterMaterialId(supplierPrinterServiceDto.getAdapterMaterialId());
        s.setDeviceModelId(supplierPrinterServiceDto.getDeviceModelId());
        List<SupplierPrinterServiceDto> l = supplierPrinterServiceDao.getSupplierPrintServiceByCondition(s, new PageInfo());
        if (!CollectionUtils.isEmpty(l)) {
            throw new BizException(BizConstant.COMMON_RECORD_EXIST_ERROR, BizConstant.COMMON_RECORD_EXIST_MSG_ERROR);
        }
        try {
            SupplierPrinterServiceEntity entity = new SupplierPrinterServiceEntity();
            BeanUtils.copyProperties(entity, supplierPrinterServiceDto);
            supplierPrinterServiceDao.insertSelective(entity);
            supplierPrinterServiceDto.setId(entity.getId());
            addMaterialRecordService(supplierPrinterServiceDto);
        } catch (Exception e) {
            logger.error("insert is error!" + e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }


    /**
     * @param list
     * @param status
     * @throws BizException
     * @throws RepositoryException
     */
    public void batchUpdateStatus(List<Long> list, String status) throws BizException, RepositoryException {
        if (CollectionUtils.isEmpty(list) || StringUtils.isEmpty(status)) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        try {

            supplierPrinterServiceDao.batchUpdateStatus(list, status);
        } catch (Exception e) {
            logger.error("update is error!" + e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }


    /**
     * @param supplierPrinterServiceDto(支持userId订单查询供应商名称)
     * @param pageInfo
     * @return
     * @throws BizException
     * @throws RepositoryException
     */
    public List<SupplierPrinterServiceDto> getSupplierPrintServiceByCondition(SupplierPrinterServiceDto supplierPrinterServiceDto, PageInfo pageInfo)
            throws BizException, RepositoryException {
        if (null == pageInfo) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        try {
            int count = supplierPrinterServiceDao.getCountByCondition(supplierPrinterServiceDto);
            pageInfo.setRowCount(count);
            OrderByInfo o = new OrderByInfo("gmt_create", OrderByInfo.SORT_DIRECTION_DESC);
            List<OrderByInfo> li = new ArrayList<OrderByInfo>();
            li.add(o);
            pageInfo.setOrderByList(li);
            List<SupplierPrinterServiceDto> list = supplierPrinterServiceDao
                    .getSupplierPrintServiceByCondition(supplierPrinterServiceDto, pageInfo);
            return list;
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }

    }

    /**
     * 提供给下单页面使用
     *
     * @param adapterMaterialId
     * @param color
     * @return
     */
    public Set<Object> getCanOrderSupplierPrintByCondition(Long adapterMaterialId, String color)
            throws BizException, RepositoryException {
        if (null == adapterMaterialId) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);
        }
        Set<Object> set = new HashSet<Object>();
        try {
            List<SupplierPrinterServiceDto> list = supplierPrinterServiceDao.getCanOrderSupplierPrintByCondition(adapterMaterialId, color);
            if (list != null && list.size() > 0) {
                for (SupplierPrinterServiceDto s : list) {
                    if (StringUtils.isNotEmpty(color)) {
                        for (String co : s.getPrecisions().split(";")) {
                            set.add(co);
                        }
                    } else {
                        for (String co : s.getColor().split(";")) {
                            set.add(co);
                        }
                    }
                }

            }
        } catch (Exception e) {
            throw new RepositoryException(BizConstant.DATABASE_SYS_ERROR);
        }

        return set;
    }


    /**
     * 提供添加备案的信息,如果没有备案，需要在备案信息中添加记录信息
     *
     * @param supplierPrinterServiceDto
     */
    private void addMaterialRecordService(SupplierPrinterServiceDto supplierPrinterServiceDto) {
        // 传入的形式如：small:101~400um&已备案;middle:101~400um&已备案;
        List<MaterialRecordationServiceEntity> record = new ArrayList<>();
//        if(flag.equals("n")) {
        for (String s : supplierPrinterServiceDto.getPrecisions().split(";")) {
            String filing = s.split("&")[1];
            //材料备案状态未备案的时候需要向备案表插入一条备案信息，已备案或者备案中则不需要
            if (filing.equals(MaterialFilingEnum.NOFILE.getText())) {
                MaterialRecordationServiceEntity entity = new MaterialRecordationServiceEntity();
                entity.setUserId(supplierPrinterServiceDto.getUserId());
                entity.setPrecisions(s.split(":")[1].split("&")[0]);
                entity.setMaterialClassifyId("SupplierAddNotClassify");
                entity.setPrecisionsDegree(s.split(":")[0]);
                entity.setRecordMaterialId(supplierPrinterServiceDto.getAdapterMaterialId());
                entity.setRecordMaterialName(supplierPrinterServiceDto.getAdapterMaterialName());
                entity.setStatus("commit");
                record.add(entity);
                Boolean times = true;
                if (times) {
                    MaterialRecordationServiceDto mrs = new MaterialRecordationServiceDto();
                    mrs.setStatus("commit");
                    mrs.setUserId(supplierPrinterServiceDto.getUserId());
                    mrs.setRecordMaterialId(supplierPrinterServiceDto.getAdapterMaterialId());
                    materialRecordationServiceDao.update(mrs);
                    times = false;
                }

            }
        }
//        }
//        }else{
//            //如果是接单中的时候修改的话，循环所有的状态，添加到材料备案
//            List<MaterialRecordationServiceDto> list  = new ArrayList<>();
//            MaterialRecordationServiceDto m = new MaterialRecordationServiceDto();
//            m.setRecordMaterialId(supplierPrinterServiceDto.getAdapterMaterialId());
//            m.setUserId(supplierPrinterServiceDto.getUserId());
//            m.setStatus("pass");
//            list.add(m);
//            materialRecordationServiceDao.deleteById(list);
//            for (String s : supplierPrinterServiceDto.getPrecisions().split(";")) {
//                String filing = s.split("&")[1];
//                if (!filing.equals(MaterialFilingEnum.FILED.getText())) {
//                    MaterialRecordationServiceEntity entity = new MaterialRecordationServiceEntity();
//                    entity.setUserId(supplierPrinterServiceDto.getUserId());
//                    entity.setPrecisions(s.split(":")[1].split("&")[0]);
//                    entity.setMaterialClassifyId("SupplierAddNotClassify");
//                    entity.setPrecisionsDegree(s.split(":")[0]);
//                    entity.setStatus("commit");
//                    entity.setRecordMaterialId(supplierPrinterServiceDto.getAdapterMaterialId());
//                    entity.setRecordMaterialName(supplierPrinterServiceDto.getAdapterMaterialName());
//                    record.add(entity);
//                }
//            }
//        }
        if (!record.isEmpty()) {
            materialRecordationServiceDao.batchInsert(record);
        }


    }

    /**
     * @param supplierPrinterServiceDto
     * @throws BizException
     * @throws RepositoryException
     */
    public void update(SupplierPrinterServiceDto supplierPrinterServiceDto) throws BizException, RepositoryException {
        if (null == supplierPrinterServiceDto || null == supplierPrinterServiceDto.getId()
                || null == supplierPrinterServiceDto.getSupplierId()
                || StringUtils.isEmpty(supplierPrinterServiceDto.getDeviceAsName())
                || StringUtils.isEmpty(supplierPrinterServiceDto.getUserId())
                || null == supplierPrinterServiceDto.getAdapterMaterialId()
                || StringUtils.isEmpty(supplierPrinterServiceDto.getColor())
                || StringUtils.isEmpty(supplierPrinterServiceDto.getPrecisions())) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR, BizConstant.COMMON_PARAM_MSG_ERROR);

        }
        SupplierPrinterServiceDto s = new SupplierPrinterServiceDto();
        s.setUserId(supplierPrinterServiceDto.getUserId());
        s.setDeviceModelId(supplierPrinterServiceDto.getDeviceModelId());
        s.setAdapterMaterialId(supplierPrinterServiceDto.getAdapterMaterialId());
        List<SupplierPrinterServiceDto> l = supplierPrinterServiceDao.getSupplierPrintServiceByCondition(s, new PageInfo());

        if (!CollectionUtils.isEmpty(l) && l.get(0).getId() != supplierPrinterServiceDto.getId()) {
            throw new BizException(BizConstant.COMMON_RECORD_EXIST_ERROR, BizConstant.COMMON_RECORD_EXIST_MSG_ERROR);
        }

        try {
            SupplierPrinterServiceEntity entity = new SupplierPrinterServiceEntity();

            BeanUtils.copyProperties(entity, supplierPrinterServiceDto);
            supplierPrinterServiceDao.updateByPrimaryKeySelective(entity);
            addMaterialRecordService(supplierPrinterServiceDto);
        } catch (Exception e) {
            logger.error("update is error!" + e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }
}
