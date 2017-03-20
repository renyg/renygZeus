package com.shining3d.zeus.service;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.SupplierPrinterServiceApiService;
import com.shining3d.zeus.client.dto.SupplierPrinterServiceDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.repository.SupplierPrinterServiceRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service(value = "supplierPrinterService")
public class SupplierPrinterService implements SupplierPrinterServiceApiService {

    private Logger logger = Logger.getLogger(getClass());

    @Resource
    SupplierPrinterServiceRepository supplierPrinterServiceRepository;

    /**
     * 通过ID删除打印机管理信息
     * @param id
     * @return
     */
    @Override
    public Result<Boolean> deleteById(Long id) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            supplierPrinterServiceRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("SupplierPrinterService.deleteById is error!", e);
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return ret;
    }

    /**
     * 新增打印机管理信息
     * @param supplierPrinterServiceDto
     * @return
     */
    @Override
    public Result<SupplierPrinterServiceDto> insert(SupplierPrinterServiceDto supplierPrinterServiceDto) {
        Result<SupplierPrinterServiceDto> ret = new Result<SupplierPrinterServiceDto>();
        try {
            supplierPrinterServiceRepository.insert(supplierPrinterServiceDto);
        } catch (Exception e) {
            logger.error("SupplierPrinterService.insert is error!", e);
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return ret;
    }

    /**
     * 新增打印机管理信息
     * @param supplierPrinterServiceDto
     * @return
     */
    @Override
    public Result<SupplierPrinterServiceDto> update(SupplierPrinterServiceDto supplierPrinterServiceDto) {
        Result<SupplierPrinterServiceDto> ret = new Result<SupplierPrinterServiceDto>();
        try {
            supplierPrinterServiceRepository.update(supplierPrinterServiceDto);
        } catch (Exception e) {
            logger.error("SupplierPrinterService.insert is error!", e);
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return ret;
    }


    /**
     * 修改打印机管理信息
     * @param list
     * @param status
     * @return
     */
    @Override
    public Result<SupplierPrinterServiceDto> batchUpdateStatus(List<Long> list,String status) {
        Result<SupplierPrinterServiceDto> ret = new Result<SupplierPrinterServiceDto>();
        try {
            supplierPrinterServiceRepository.batchUpdateStatus(list,status);
        } catch (Exception e) {
            logger.error("SupplierPrinterService.batchUpdateStatus is error!", e);
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return ret;
    }

    /**
     * 查询打印机管理信息
     * @param supplierPrinterServiceDto
     * @param pageInfo
     * @return
     */
    @Override
    public PageResult<SupplierPrinterServiceDto> getSupplierPrintServiceByCondition(SupplierPrinterServiceDto supplierPrinterServiceDto, PageInfo pageInfo) {
        PageResult<SupplierPrinterServiceDto> ret = new PageResult<SupplierPrinterServiceDto>();
        try {
            List<SupplierPrinterServiceDto> list = supplierPrinterServiceRepository
                    .getSupplierPrintServiceByCondition(supplierPrinterServiceDto, pageInfo);
            ret.setResult(list);
            ret.setPageInfo(pageInfo);
        } catch (Exception e) {
            logger.error("SupplierPrinterService.getSupplierPrintServiceByCondition is error!", e);
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return ret;
    }

    /**
     * 提供给下单查询列表页
     * @param adapterMaterialId
     * @param color
     * @return
     */
    @Override
    public Result<Set> getCanOrderSupplierPrintByCondition(Long adapterMaterialId, String color) {
        Result<Set> ret = new Result<Set>();
        try {
            Set<Object> set = supplierPrinterServiceRepository
                    .getCanOrderSupplierPrintByCondition(adapterMaterialId,color);
            ret.setResult(set);
        } catch (Exception e) {
            logger.error("SupplierPrinterService.getCanOrderSupplierPrintByCondition is error!", e);
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
        }
        return ret;
    }
}
