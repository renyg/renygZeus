package com.shining3d.zeus.client;

import com.shining3d.common.dto.PageInfo;
import com.shining3d.common.dto.PageResult;
import com.shining3d.common.dto.Result;
import com.shining3d.zeus.client.dto.MaterialRecordationServiceDto;

/**
 * Created by renyg on 2016/12/21.
 */
public interface MaterialRecordationServiceApiService {
    /**
     *删除材料备案
     * @param recordMaterialId
     * @param userId
     * @return
     */
    public Result<Boolean> deleteById(String recordMaterialId,String userId);

    /**
     * @title 新增材料备案
     * @param materialRecordationServiceDto
     * @return
     */
    public Result<MaterialRecordationServiceDto> insert(MaterialRecordationServiceDto materialRecordationServiceDto);

    /**
     * @title 修改材料备案信息
     * @param materialRecordationServiceDto
     * @return
     */
    public Result<MaterialRecordationServiceDto> update(MaterialRecordationServiceDto materialRecordationServiceDto);

    /**
     * @title 查询材料备案信息
     * @param materialRecordationServiceDto
     * @param pageInfo
     * @return
     */
    public PageResult<MaterialRecordationServiceDto> getMaterialRecordationCondition(MaterialRecordationServiceDto materialRecordationServiceDto, PageInfo pageInfo);

}
