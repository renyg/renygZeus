package com.shining3d.zeus.service;
import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.DeviceModelSaleApiService;
import com.shining3d.zeus.client.dto.DeviceModelDto;
import com.shining3d.zeus.client.dto.ModelMaterialRelationDto;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.DeviceModelRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "deviceModelService")
public class DeviceModelService implements DeviceModelSaleApiService {

    @Resource
    DeviceModelRepository deviceModelRepository;

    @Override
    public Result<Boolean> batchDeleteByIds(String ids) {

        Result<Boolean> ret  = new Result<Boolean>();
        try{
            deviceModelRepository.batchDeleteByIds(ids);
            ret.setResult(true);
        }catch (BizException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
            ret.setResult(false);
        }catch (RepositoryException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
            ret.setResult(false);
        }
        return ret;
    }

    @Override
    public Result<Boolean> batchUpdateModelStatus(String ids, String status) {

        Result<Boolean> ret  = new Result<Boolean>();
        try{
            deviceModelRepository.batchUpdateModelStatus(ids,status);
            ret.setResult(true);
        }catch (BizException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
            ret.setResult(false);
        }catch (RepositoryException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
            ret.setResult(false);
        }
        return ret;
    }

    /**
     *
     * @param deviceModelDto
     * @return
     */
    @Override
    public Result<DeviceModelDto> insert(DeviceModelDto deviceModelDto, List<ModelMaterialRelationDto> list) {
        Result<DeviceModelDto> ret  = new Result<DeviceModelDto>();
        try{
            deviceModelRepository.insertSelective(deviceModelDto,list);
            ret.setResult(deviceModelDto);
        }catch (BizException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }catch (RepositoryException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }
        return ret;
    }

    @Override
    public Result<DeviceModelDto> update(DeviceModelDto deviceModelDto ,List<ModelMaterialRelationDto> list) {
        Result<DeviceModelDto> ret  = new Result<DeviceModelDto>();
        try{
            deviceModelRepository.updateByPrimaryKeySelective(deviceModelDto,list);
            ret.setResult(deviceModelDto);
        }catch (BizException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }catch (RepositoryException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }
        return ret;
    }

    @Override
    public PageResult<DeviceModelDto> getDeviceModel(DeviceModelDto deviceModelDto, PageInfo pageInfo) {
        PageResult<DeviceModelDto> ret  = new PageResult<DeviceModelDto>();
        try{
            List<DeviceModelDto> list =  deviceModelRepository.getDeviceModel(deviceModelDto,pageInfo);
            ret.setResult(list);
            ret.setPageInfo(pageInfo);
        }catch (BizException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }catch (RepositoryException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }
        return ret;
    }


    @Override
    public PageResult<DeviceModelDto> getDeviceModelList(DeviceModelDto deviceModelDto, PageInfo pageInfo) {
        PageResult<DeviceModelDto> ret  = new PageResult<DeviceModelDto>();
        try{
            List<DeviceModelDto> list =  deviceModelRepository.getDeviceModelList(deviceModelDto,pageInfo);
            ret.setResult(list);
            ret.setPageInfo(pageInfo);
        }catch (BizException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }catch (RepositoryException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }
        return ret;
    }

    /**
     * 提供给添加供应商页面增加时的列表使用
     * @param userId
     * @param modelName
     * @param supplierId
     * @return
     */
    @Override
    public PageResult<DeviceModelDto> getDeviceModelByCondition(String userId, String modelName, Long supplierId) {
        PageResult<DeviceModelDto> ret  = new PageResult<DeviceModelDto>();
        try{
            List<DeviceModelDto> list =  deviceModelRepository.getDeviceModelByCondition(userId, modelName,supplierId);
            ret.setResult(list);
        }catch (BizException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }catch (RepositoryException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }
        return ret;
    }

    /**
     *
     * @param precisionId
     * @param marterialId
     * @return
     */
    public PageResult<ModelMaterialRelationDto> getMateialRealtionByPrecisionIdAndMaterialId(Long precisionId , Long marterialId) {
        PageResult<ModelMaterialRelationDto> ret  = new PageResult<ModelMaterialRelationDto>();
        try{
            List<ModelMaterialRelationDto> list =  deviceModelRepository.getMateialRealtionByPrecisionIdAndMaterialId(precisionId, marterialId);
            ret.setResult(list);
        }catch (BizException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }catch (RepositoryException e){
            ret.setErrCode(e.getMessage());
            ret.setErrMsg(e.getMessage());
        }
        return ret;
    }

}
