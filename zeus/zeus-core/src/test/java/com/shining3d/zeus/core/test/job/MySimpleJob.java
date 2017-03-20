package com.shining3d.zeus.core.test.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Created by fe on 2016/12/7.
 */
public class MySimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext context) {
        int i = 1/0;
        switch (context.getShardingItem()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
}
