package com.shining3d.zeus;


import com.alibaba.dubbo.rpc.*;
import com.shining3d.common.FastJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Created by fe on 2016/12/8.
 */
public class ZeusFilter  implements Filter {
    public static final Logger logger = LoggerFactory.getLogger(ZeusFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            String methodName = invocation.getMethodName();
            String arguments = FastJson.toJson(invocation.getArguments());
            String uuid = UUID.randomUUID().toString().replace("-","");
            MDC.put("uuid",uuid);
            MDC.put("methodName",methodName);
            MDC.put("arguments",arguments);
            long start = System.currentTimeMillis();
            Result result = invoker.invoke(invocation);
            long end = System.currentTimeMillis();
            logger.info("result : {}, used : {} ms",FastJson.toJson(result.getValue()),end-start);
            return result;
        }  finally {
            MDC.remove("uuid");
            MDC.remove("methodName");
            MDC.remove("arguments");
        }

    }
}
