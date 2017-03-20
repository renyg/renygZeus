package com.shining3d.zeus.repository;

import com.shining3d.common.dto.OrderByInfo;
import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.MaterialRecordationServiceDto;
import com.shining3d.zeus.client.dto.SupplierPrinterServiceDto;
import com.shining3d.zeus.client.dto.query.MaterialAttachmentQueryDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.MaterialRecordationServiceDao;
import com.shining3d.zeus.dao.SupplierPrinterServiceDao;
import com.shining3d.zeus.entity.MaterialRecordationServiceEntity;
import com.shining3d.zeus.entity.SupplierPrinterServiceEntity;
import com.shining3d.zeus.enums.MaterialTypeEnum;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by renyg on 2017/1/4.
 */
@Repository
public class MaterialRecordationServiceRepository {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MaterialRepository.class);

    @Resource
    private MaterialRecordationServiceDao materialRecordationServiceDao;

    @Resource
    private SupplierPrinterServiceDao supplierPrinterServiceDao;

    /**
     * @param record
     * @throws BizException
     * @throws RepositoryException
     */
    public void insert(MaterialRecordationServiceDto record) throws BizException, RepositoryException {
        try {

            MaterialRecordationServiceDto queryDto = new MaterialRecordationServiceDto();
            queryDto.setUserId(record.getUserId());
            queryDto.setRecordMaterialId(record.getRecordMaterialId());
            List<MaterialRecordationServiceDto> l = materialRecordationServiceDao.getMaterialRecordationCondition(queryDto,new PageInfo());
            if(!CollectionUtils.isEmpty(l)){
                for(MaterialRecordationServiceDto m : l){
                    if(!m.getStatus().equals(record.getStatus())){
                        queryDto.setStatus(record.getStatus());
                        materialRecordationServiceDao.update(queryDto);
                        break;
                    }
                }
            }
            List<MaterialRecordationServiceEntity> list = new ArrayList<MaterialRecordationServiceEntity>();
            //注意：选择打印精度传递方式：普通:100~200;精细:100~200;超精细:100~200;
            //一条备案材料如果选择多个精度，对应就是多条记录
            for (String s : record.getPrecisions().split(";")) {
                MaterialRecordationServiceEntity materialRecordationServiceEntity = new MaterialRecordationServiceEntity();
                materialRecordationServiceEntity.setUserId(record.getUserId());
                materialRecordationServiceEntity.setPrecisions(s.split(":")[1]);
                materialRecordationServiceEntity.setPrecisionsDegree(s.split(":")[0]);
                materialRecordationServiceEntity.setStatus(record.getStatus());
                materialRecordationServiceEntity.setMaterialClassifyId(record.getMaterialClassifyId());
                materialRecordationServiceEntity.setRecordMaterialId(record.getRecordMaterialId());
                materialRecordationServiceEntity.setRecordMaterialName(record.getRecordMaterialName());
                list.add(materialRecordationServiceEntity);
            }
            materialRecordationServiceDao.batchInsert(list);
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
    public void update(MaterialRecordationServiceDto record) throws BizException, RepositoryException {
        try {
            materialRecordationServiceDao.update(record);
            //如果状态不是提交中的时候都要同步给服务商的状态,保持状态一致,当材料备案状态发生变化，需要更新到服务商的服务能力表上
            SupplierPrinterServiceDto s = new SupplierPrinterServiceDto();
            s.setUserId(record.getUserId());
            s.setAdapterMaterialId(record.getRecordMaterialId());
            List<SupplierPrinterServiceDto> l = supplierPrinterServiceDao.getSupplierPrintServiceByCondition(s, new PageInfo());
            if (!CollectionUtils.isEmpty(l)) {
                SupplierPrinterServiceEntity supplierPrinterServiceEntity = new SupplierPrinterServiceEntity();
                supplierPrinterServiceEntity.setAdapterMaterialId(record.getRecordMaterialId());
                supplierPrinterServiceEntity.setUserId(record.getUserId());
                supplierPrinterServiceEntity.setStatus(record.getStatus());
                //页面传入的参数
                String tempIn = record.getPrecisionsAudit()+";";
                List<String> lout = new ArrayList<>();
                //large:40~80μm&已备案;middle:20~40μm&已备案;small:1~20μm&已备案
                //要输出的输出
                String tempOut[] = l.get(0).getPrecisions().split(";");
                StringBuffer sb = new StringBuffer();
                for (String so : tempOut) {
                    lout.add(so.split("&")[0]);
                }
                for (String so : lout) {
                   if( tempIn.indexOf(so) > -1){
                       sb.append(tempIn.substring(tempIn.indexOf(so),so.length() + tempIn.indexOf(so)+ 5));
                   }
                }
                supplierPrinterServiceEntity.setPrecisions(sb.toString());
                supplierPrinterServiceDao.updateStatus(supplierPrinterServiceEntity);
            }
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }

    }

    /**
     * @param recordMaterialId
     * @param userId
     * @throws BizException
     * @throws RepositoryException
     */
    public void deleteBatchById(String recordMaterialId, String userId) throws BizException, RepositoryException {
        try {
            List<MaterialRecordationServiceDto> list = new ArrayList<>();
            MaterialRecordationServiceDto m = new MaterialRecordationServiceDto();
            m.setRecordMaterialId(Long.parseLong(recordMaterialId));
            m.setUserId(userId);
            list.add(m);
            materialRecordationServiceDao.deleteById(list);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }

    /**
     * @param materialRecordationServiceDto
     * @param pageInfo
     * @return
     * @throws BizException
     * @throws RepositoryException
     */
    public List<MaterialRecordationServiceDto> getMaterialRecordationCondition(MaterialRecordationServiceDto materialRecordationServiceDto, PageInfo pageInfo)
            throws BizException, RepositoryException {
        try {
            int count = materialRecordationServiceDao.getCountMaterialRecordationCondition(materialRecordationServiceDto);
            OrderByInfo o = new OrderByInfo("gmt_create", OrderByInfo.SORT_DIRECTION_DESC);
            List<OrderByInfo> li = new ArrayList<OrderByInfo>();
            li.add(o);
            pageInfo.setOrderByList(li);
            List<MaterialRecordationServiceDto> list = materialRecordationServiceDao.getMaterialRecordationCondition(materialRecordationServiceDto, pageInfo);
            return list;
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }


    public int countPermissionMaterialAttachment(MaterialAttachmentQueryDto queryDto)
            throws BizException, RepositoryException {
        try {
            return materialRecordationServiceDao.countPermissionMaterialAttachment(queryDto);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }
}
