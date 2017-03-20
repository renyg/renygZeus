package com.shining3d.zeus.client;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.PropValueDto;

import java.util.List;

/**
 * Created by fe on 2016/12/26.
 */
public interface PropValueApiService {

    /**
     * 根据属性查询属性值列表
     * @param categoryId -1查询通用属性
     * @param propName 属性名称
     * @return
     */
    public Result<List<PropValueDto>> queryPropValueListByPropName(Long categoryId,String propName);

}
