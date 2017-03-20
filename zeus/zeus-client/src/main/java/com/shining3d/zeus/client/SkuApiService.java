package com.shining3d.zeus.client;

import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.SkuDto;

/**
 * Created by fe on 2016/12/19.
 */
public interface SkuApiService {

    /**
     * 根据skuId查询sku
     * @param skuId skuId
     * @return
     */
    public Result<SkuDto> querySkuBySkuId(Long skuId);


}
