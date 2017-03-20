package com.shining3d.zeus.service;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.ShoppingCartApiService;
import com.shining3d.zeus.client.dto.ShoppingCartDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fe on 2016/12/8.
 */
@Service
public class ShoppingCartService implements ShoppingCartApiService {

    @Resource
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public Result<Long> saveSkuToShoppingCart(String userId, Long skuId, Integer num) {
        Result<Long> ret = new Result<Long>();
        try {
            Long result = shoppingCartRepository.saveSkuToShoppingCart(userId,skuId,num);
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
    public Result<List<ShoppingCartDto>> queryMyAllShoppingCart(String userId) {
        Result<List<ShoppingCartDto>> ret = new Result<List<ShoppingCartDto>>();
        try {
            List<ShoppingCartDto> result = shoppingCartRepository.queryMyAllShoppingCart(userId);
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
    public Result<Boolean> deleteSkuFromShoppingCart(Long shoppingCartId, String userId) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = shoppingCartRepository.deleteSkuFromShoppingCart(shoppingCartId,userId);
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
    public Result<Boolean> updateShoppingCartSkuNum(String userId, Long skuId, Integer num) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = shoppingCartRepository.updateShoppingCartSkuNum(userId,skuId,num);
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
    public Result<Boolean> clearShoppingCart(String userId) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = shoppingCartRepository.clearShoppingCart(userId);
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
    public Result<Boolean> clearShoppingCart(String userId,List<Long> skuIdList) {
        Result<Boolean> ret = new Result<Boolean>();
        try {
            Boolean result = shoppingCartRepository.clearShoppingCart(userId,skuIdList);
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
}
