package com.shining3d.zeus.service;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.CategoryApiService;
import com.shining3d.zeus.client.dto.CategoryDto;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.exception.RepositoryException;
import com.shining3d.zeus.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fe on 2017/1/16.
 */
@Service
public class CategoryService implements CategoryApiService {
    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public Result<CategoryDto> queryByCode(String code) {
        Result<CategoryDto> ret = new Result<CategoryDto>();
        try {
            CategoryDto result = categoryRepository.queryByCode(code);
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
    public Result<CategoryDto> queryByCategoryId(Long categoryId) {
        Result<CategoryDto> ret = new Result<CategoryDto>();
        try {
            CategoryDto result = categoryRepository.queryByCategoryId(categoryId);
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
