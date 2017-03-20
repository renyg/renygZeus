package com.shining3d.zeus.client;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.CategoryDto;

import java.util.List;

/**
 * Created by fe on 2016/12/20.
 */
public interface CategoryApiService {

    /**
     * 根据code查询category
     * @param code
     * @return
     */
    public Result<CategoryDto> queryByCode(String code);

    /**
     * 根据主键id查找category
     * @param categoryId
     * @return
     */
    public Result<CategoryDto> queryByCategoryId(Long categoryId);

}
