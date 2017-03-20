package com.shining3d.zeus.service;

import com.shining3d.common.dto.OrderByInfo;
import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.DeviceSupplierApiService;
import com.shining3d.zeus.client.dto.DeviceSupplierDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.repository.DeviceSupplierRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "deviceSupplierService")
public class DeviceSupplierService implements DeviceSupplierApiService {
    private Logger logger = Logger.getLogger(getClass());

    @Resource
    DeviceSupplierRepository deviceSupplierRepository;

    /***
     *批量删除
     * @param ids
     * @return
     */
    @Override
    public Result<Boolean> deleteBatchByIds(String ids) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            if (StringUtils.isEmpty(ids)) {
                ret.setErrMsg("参数错误");
                ret.setErrCode(BizConstant.COMMON_PARAM_ERROR);
                return ret;
            }
            List<Long> list = new ArrayList<Long>();

            for (String id : ids.split(";")) {
                list.add(Long.parseLong(id));
            }
            deviceSupplierRepository.deleteBatchByIds(list);
        } catch (Exception e) {
            logger.error("DeviceSupplierService.deleteBatchByIds is error!");
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return ret;
    }

    /**
     *
     * @param deviceSupplierDto
     * @return
     */
    @Override
    public Result<DeviceSupplierDto> insert(DeviceSupplierDto deviceSupplierDto) {
        Result<DeviceSupplierDto> ret = new Result<DeviceSupplierDto>();
        if (null == deviceSupplierDto || StringUtils.isEmpty(deviceSupplierDto.getName())) {
            ret.setErrMsg("参数错误");
            ret.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            return ret;
        }
        try {
            deviceSupplierRepository.insertSelective(deviceSupplierDto);
            ret.setResult(deviceSupplierDto);
        } catch (Exception e) {
            logger.error("DeviceSupplierService.insert is error!");
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return ret;

    }

    /**
     *
     * @param deviceSupplierDto
     * @return
     */
    @Override
    public Result<DeviceSupplierDto> update(DeviceSupplierDto deviceSupplierDto) {
        Result<DeviceSupplierDto> ret = new Result<DeviceSupplierDto>();
        if (null == deviceSupplierDto || deviceSupplierDto.getId() <= 0 || StringUtils.isEmpty(deviceSupplierDto.getName())) {
            ret.setErrMsg("参数错误");
            ret.setErrCode(BizConstant.COMMON_PARAM_ERROR);
            return ret;
        }
        try {
            deviceSupplierRepository.updateByPrimaryKeySelective(deviceSupplierDto);
            ret.setResult(deviceSupplierDto);

        } catch (Exception e) {
            logger.error("DeviceSupplierService.update is error!");
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return ret;
    }

    @Override
    public PageResult<DeviceSupplierDto> queryDeviceSupplierByCondition(DeviceSupplierDto deviceSupplierDto, PageInfo pageInfo) {
        PageResult<DeviceSupplierDto> ret  = new PageResult<DeviceSupplierDto>();
        try{
            if(null == deviceSupplierDto || pageInfo == null ){
                ret.setErrMsg("参数错误");
                ret.setErrCode(BizConstant.COMMON_PARAM_ERROR);
                return ret;
            }
            int count = deviceSupplierRepository.countDeviceSupplierByCondition(deviceSupplierDto);
            pageInfo.setRowCount(count);
            OrderByInfo o = new OrderByInfo("gmt_create", OrderByInfo.SORT_DIRECTION_DESC);
            List<OrderByInfo> li = new ArrayList<OrderByInfo>();
            li.add(o);
            pageInfo.setOrderByList(li);
            List<DeviceSupplierDto> list = deviceSupplierRepository.queryDeviceSupplierByCondition(deviceSupplierDto,pageInfo);
            ret.setResult(list);
            ret.setPageInfo(pageInfo);
        }catch (Exception e){
            ret.setErrCode(BizConstant.DATABASE_SYS_ERROR);
            ret.setErrMsg(e.getMessage());


        }
        return ret;
    }


}
