package com.shining3d.zeus.client;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.PropDto;

import java.util.List;

/**
 * Created by fe on 2017/1/5.
 */
public interface PropApiService {

    /**
     * 根据类目id查询属性列表接口
     * @param categoryId
     * @return
     */
    public Result<List<PropDto>> queryPropListByCategoryId(Long categoryId);


}
