package com.shining3d.zeus.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.shining3d.zeus.entity.SkuEntity;

import java.util.List;

/**
 * Created by fe on 2016/12/8.
 */
public class SkuJob implements DataflowJob<SkuEntity> {
    @Override
    public List<SkuEntity> fetchData(ShardingContext shardingContext) {
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<SkuEntity> data) {


    }
}
