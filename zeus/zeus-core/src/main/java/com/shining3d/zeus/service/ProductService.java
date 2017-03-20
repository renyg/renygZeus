package com.shining3d.zeus.service;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.ProductApiService;
import com.shining3d.zeus.client.dto.DataProductDto;
import com.shining3d.zeus.client.dto.PackageProductDto;
import com.shining3d.zeus.client.dto.ProductDto;
import com.shining3d.zeus.client.dto.SingleProductDto;
import com.shining3d.zeus.client.dto.query.*;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.ProductRepository;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fe on 2016/12/23.
 */
@Service
public class ProductService implements ProductApiService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public Result<Boolean> updateProductStateByBops(Long productId, String state) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = productRepository.updateProductStateByBops(productId,state);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Boolean> deleteProductByBops(Long productId) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = productRepository.deleteProductByBops(productId);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Long> saveDataProductByBops(DataProductDto dataProductDto) {
        Result<Long> ret = new Result<Long>();
        try {
            Long result = productRepository.saveDataProductByBops(dataProductDto);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public PageResult<DataProductDto> queryDataProductListByBops(DataProductBopsQueryDto dataProductBopsQueryDto, PageInfo pageInfo) {
        PageResult<DataProductDto> ret = new PageResult<DataProductDto>();
        try {
            List<DataProductDto> result = productRepository.queryDataProductListByBops(dataProductBopsQueryDto, pageInfo);
            ret.setResult(result);
            ret.setPageInfo(pageInfo);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<DataProductDto> queryDataProductDetailByBops(Long productId) {
        Result<DataProductDto> ret = new Result<DataProductDto>();
        try {
            DataProductDto result = productRepository.queryDataProductDetailByBops(productId);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Boolean> updateDataProductByBops(DataProductDto dataProductDto) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = productRepository.updateDataProductByBops(dataProductDto);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public PageResult<DataProductDto> queryDataProductList(DataProductQueryDto dataProductQueryDto, PageInfo pageInfo) {
        PageResult<DataProductDto> ret = new PageResult<DataProductDto>();
        try {
            List<DataProductDto> result = productRepository.queryDataProductList(dataProductQueryDto,pageInfo);
            ret.setResult(result);
            ret.setPageInfo(pageInfo);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Boolean> updateMyDataProduct(Long productId, String userId, BigDecimal sharePrice) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = productRepository.updateMyDataProduct(productId,userId,sharePrice);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Boolean> deleteMyDataProduct(Long productId, String userId) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = productRepository.deleteMyDataProduct(productId,userId);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Long> saveMyDataProduct(DataProductDto dataProductDto) {
        Result<Long> ret = new Result<Long>();
        try {
            Long result = productRepository.saveMyDataProduct(dataProductDto);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Long> saveSingleProductByBops(SingleProductDto singleProductDto) {
        Result<Long> ret = new Result<Long>();
        try {
            Long result = productRepository.saveSingleProductByBops(singleProductDto);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public PageResult<SingleProductDto> querySingleProductListByBops(SingleProductQueryDto singleProductQueryDto, PageInfo pageInfo) {
        PageResult<SingleProductDto> ret = new PageResult<SingleProductDto>();
        try {
            List<SingleProductDto> singleProductDtoList = productRepository.querySingleProductListByBops(singleProductQueryDto,pageInfo);
            ret.setResult(singleProductDtoList);
            ret.setPageInfo(pageInfo);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<SingleProductDto> querySingleProductDetailByBops(Long productId) {
        Result<SingleProductDto> ret = new Result<SingleProductDto>();
        try {
            SingleProductDto result = productRepository.querySingleProductDetailByBops(productId);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Boolean> updateSingleProductByBops(SingleProductDto singleProductDto) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = productRepository.updateSingleProductByBops(singleProductDto);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Long> savePackageProductByBops(PackageProductDto packageProductDto) {
        Result<Long> ret = new Result<Long>();
        try {
            Long result = productRepository.savePackageProductByBops(packageProductDto);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    /**
     * 查询所有的单品商品列表接口
     * @return
     */
    public Result<List<SingleProductDto>> queryAllSingleProductList() {
        Result<List<SingleProductDto>> ret = new Result<List<SingleProductDto>>();
        try {
            List<SingleProductDto> singleProductDtoList = productRepository.queryAllSingleProductList();
            ret.setResult(singleProductDtoList);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    public Result<List<PackageProductDto>> queryAllPackageProductList() {
        Result<List<PackageProductDto>> ret = new Result<List<PackageProductDto>>();
        try {
            List<PackageProductDto> packageProductDtoList = productRepository.queryAllPackageProductList();
            ret.setResult(packageProductDtoList);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public PageResult<PackageProductDto> queryPackageProductListByBops(PackageProductQueryDto packageProductQueryDto, PageInfo pageInfo) {
        PageResult<PackageProductDto> ret = new PageResult<PackageProductDto>();
        try {
            List<PackageProductDto> result = productRepository.queryPackageProductListByBops(packageProductQueryDto,pageInfo);
            ret.setResult(result);
            ret.setPageInfo(pageInfo);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<PackageProductDto> queryPackageProductDetailByBops(Long productId) {
        Result<PackageProductDto> ret = new Result<PackageProductDto>();
        try {
            PackageProductDto result = productRepository.queryPackageProductDetailByBops(productId);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<Boolean> updatePackageProductByBops(PackageProductDto packageProductDto) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = productRepository.updatePackageProductByBops(packageProductDto);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

    @Override
    public Result<ProductDto> queryProductByProductId(Long productId) {
        Result<ProductDto> ret = new Result<ProductDto>();
        try {
            ProductDto result = productRepository.queryProductByProductId(productId);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }
    @Override
    public Result<DataProductDto> queryDataProductDetailByDataId(Long dataId) {
        Result<DataProductDto> ret = new Result<DataProductDto>();
        try {
            DataProductDto result = productRepository.queryDataProductDetailByDataId(dataId);
            ret.setResult(result);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }


    @Override
    public PageResult<ProductDto> queryProductByBops(ProductQueryDto productQueryDto, PageInfo pageInfo) {
        PageResult<ProductDto> ret = new PageResult<ProductDto>();
        try {
            List<ProductDto> result = productRepository.queryProductByBops(productQueryDto,pageInfo);
            ret.setResult(result);
            ret.setPageInfo(pageInfo);
            return ret;
        } catch (RepositoryException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(BizConstant.COMMON_SYS_ERROR);
            ret.setSuccess(false);
        } catch (BizException e) {
            ret.setErrMsg(e.getMessage());
            ret.setErrCode(e.getCode());
            ret.setSuccess(false);
        }
        return ret;
    }

}
