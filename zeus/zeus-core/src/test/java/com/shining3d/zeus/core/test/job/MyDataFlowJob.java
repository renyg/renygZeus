package com.shining3d.zeus.core.test.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fe on 2016/12/7.
 */
public class MyDataFlowJob implements DataflowJob<Foo> {

    public static final Logger logger = LoggerFactory.getLogger(MyDataFlowJob.class);

    @Override
    public List<Foo> fetchData(ShardingContext shardingContext) {
        List<Foo> fooList = new LinkedList<Foo>();
        Foo foo = new Foo();
        foo.setAge(1);
        foo.setName("test");
        fooList.add(foo);
        return fooList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Foo> data) {
        logger.info("processData");
        System.out.println("############### " + data.size() + "," + System.currentTimeMillis());
    }
}
