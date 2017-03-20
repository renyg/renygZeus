package com.shining3d.zeus.repository;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.zeus.client.dto.MaterialDto;
import com.shining3d.zeus.client.dto.MaterialPrecisionDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.dao.MaterialDao;
import com.shining3d.zeus.dao.MaterialPrecisionDao;
import com.shining3d.zeus.entity.MaterialEntity;
import com.shining3d.zeus.entity.MaterialPrecisionEntity;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by renyg on 2017/1/4.
 */
@Repository
public class MaterialRepository {

    private static final Logger logger = LoggerFactory.getLogger(MaterialRepository.class);

    @Resource
    private MaterialDao materialDao;

    @Resource
    private MaterialPrecisionDao materialPrecisionDao;


    public void deleteByPKs(List<String> ids) throws RepositoryException {
        try {
            materialDao.deleteByPK(ids);
            materialPrecisionDao.deleteByObjectIds(ids);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_BIZ_ERROR);
        }
    }
    /**
     * 新增材料信息
     *
     * @param materialDto
     * @param list
     * @throws RepositoryException
     * @throws BizException
     */
    public void insertSelective(MaterialDto materialDto, List<Map<String, String>> list) throws RepositoryException, BizException {

        MaterialDto m = materialDao.selectByNameCn(materialDto.getNameEn());
        if (null != m && m.getNameEn().equals(materialDto.getNameEn())) {
            throw new BizException(BizConstant.COMMON_RECORD_EXIST_ERROR, BizConstant.COMMON_RECORD_EXIST_MSG_ERROR);
        }
        try {
            MaterialEntity materialEntity = new MaterialEntity();
            BeanUtils.copyProperties(materialEntity, materialDto);
            materialDao.insertSelective(materialEntity);
            materialDto.setId(materialEntity.getId());

            batchInsertPrecision(list, materialEntity.getId());
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_SYS_ERROR);
        }

    }

    private void batchInsertPrecision(List<Map<String, String>> list, Long id) {
        List<MaterialPrecisionEntity> finalList = new ArrayList<MaterialPrecisionEntity>();
        for (Map<String, String> map : list) {
            MaterialPrecisionEntity materialPrecisionEntity = new MaterialPrecisionEntity();
            materialPrecisionEntity.setObjectId(id);
            materialPrecisionEntity.setPrecisions(map.get("precisions"));
            materialPrecisionEntity.setPrecisionsName(map.get("precisionsName"));
            materialPrecisionEntity.setPrecisionsRatio(map.get("precisionsRatio"));
            finalList.add(materialPrecisionEntity);
        }
        materialPrecisionDao.batchInset(finalList);
    }

    /**
     * 修改材料信息
     *
     * @param materialDto
     * @param list
     * @throws RepositoryException
     * @throws BizException
     */
    public void updateByPrimaryKeySelective(MaterialDto materialDto, List<Map<String, String>> list) throws RepositoryException, BizException {
        MaterialDto m = materialDao.selectByNameCn(materialDto.getNameEn());
        if (m != null && m.getId() != materialDto.getId()) {
            throw new BizException(BizConstant.COMMON_RECORD_EXIST_ERROR, BizConstant.COMMON_RECORD_EXIST_MSG_ERROR);

        }
        try {
            MaterialEntity materialEntity = new MaterialEntity();
            BeanUtils.copyProperties(materialEntity, materialDto);
            materialDao.updateByPrimaryKeySelective(materialEntity);
            if(!CollectionUtils.isEmpty(list)){
                for (Map<String, String> map : list) {
                    MaterialPrecisionEntity materialPrecisionEntity = new MaterialPrecisionEntity();
                    materialPrecisionEntity.setObjectId(materialEntity.getId());
                    materialPrecisionEntity.setPrecisions(map.get("precisions"));
                    materialPrecisionEntity.setPrecisionsName(map.get("precisionsName"));
                    materialPrecisionEntity.setPrecisionsRatio(map.get("precisionsRatio"));
                    if(StringUtils.isEmpty(map.get("id"))){
                        materialPrecisionDao.inset(materialPrecisionEntity);
                    }else{
                        materialPrecisionEntity.setId(Long.parseLong(map.get("id")));
                        materialPrecisionDao.update(materialPrecisionEntity);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_SYS_ERROR);
        }
    }

    /**
     * @param status
     * @param ids
     * @throws RepositoryException
     * @throws BizException
     */
    public void batchOperateStatus(String status, String ids) throws RepositoryException, BizException {
        try {
            List<String> list = Arrays.asList(ids.split(";"));
            materialDao.batchOperateStatus(status, list);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_SYS_ERROR);
        }
    }

    public int getCount(MaterialDto materialDto) {
        return materialDao.getCount(materialDto);
    }

    /**
     * 通过分页查询所有的材料信息，并支持页面材料备案时显示
     *
     * @param materialDto
     * @param pageInfo
     * @return
     * @throws RepositoryException
     * @throws BizException
     */
    public List<MaterialDto> selectByConditions(MaterialDto materialDto, PageInfo pageInfo) throws RepositoryException, BizException {
        try {
            return materialDao.selectByConditions(materialDto, pageInfo);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_SYS_ERROR);
        }
    }


    /**
     * 提供给材料备案使用
     *
     * @return
     * @throws RepositoryException
     * @throws BizException
     */
    public List<MaterialPrecisionDto> getListByNameAndClassify(String nameEn, String classifyId) throws RepositoryException, BizException {
        try {
            return materialPrecisionDao.getPrecisionByClassifyIdAndNameEn(nameEn, classifyId);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_SYS_ERROR);
        }
    }

    /**
     * 获取精度信息
     *
     * @return
     * @throws RepositoryException
     * @throws BizException
     */
    public List<MaterialPrecisionDto> getPrecisionByCondition(MaterialPrecisionDto materialPrecisionDto) throws RepositoryException, BizException {
        try {
            return materialPrecisionDao.getPrecisionByCondition(materialPrecisionDto);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_SYS_ERROR);
        }
    }

    public List<MaterialDto> getCanOrderMaterialByCondition(String buildSizeRealX,String buildSizeRealY,String buildSizeRealZ)throws RepositoryException, BizException{
        try {
            List<MaterialDto>  materialDtoList = materialDao.getCanOrderMaterialByCondition(new BigDecimal(buildSizeRealX),new BigDecimal(buildSizeRealY),new BigDecimal(buildSizeRealZ));
            return materialDtoList;
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_SYS_ERROR);
        }
    }

    public List<MaterialDto> batchSelectByIds(List<Long> ids)throws RepositoryException, BizException{
        try {
            return materialDao.batchSelectByIds(ids);
        } catch (Exception e) {
            logger.error("", e);
            throw new RepositoryException(BizConstant.COMMON_SYS_ERROR);
        }
    }

}


