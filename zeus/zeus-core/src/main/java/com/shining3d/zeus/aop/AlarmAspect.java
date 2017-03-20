package com.shining3d.zeus.aop;

import com.shining3d.common.FastJson;
import com.shining3d.common.NetWorkUtils;
import com.shining3d.common.VelocityUtils;
import com.shining3d.zeus.helper.BearyChatHelper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.velocity.VelocityContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by fe on 2017/2/8.
 */
@Component
@Aspect
public class AlarmAspect {

    public static final Logger logger = LoggerFactory.getLogger(AlarmAspect.class);

    @Resource
    private BearyChatHelper bearyChatHelper;

    @Pointcut("execution(* com.shining3d.zeus.repository.*.*(..))")
    public void pointCut(){}

    /**
     * @param joinPoint
     * @param error
     */
    @AfterThrowing(pointcut="pointCut()",throwing="error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error){
        String env = System.getProperty("disconf.env");
        String app = System.getProperty("disconf.app");
        String time = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        String methodSignature = joinPoint.getSignature().toString();
        String args = FastJson.toJson(joinPoint.getArgs());
        String errorMsg = error.getMessage();
        String stackTrace = ExceptionUtils.getStackTrace(error);
        if (stackTrace.length() > 3000) stackTrace = stackTrace.substring(0,3000);
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("env",env);
        paramMap.put("app",app);
        paramMap.put("hostName", NetWorkUtils.getHostName());
        paramMap.put("time",time);
        paramMap.put("methodSignature",methodSignature);
        paramMap.put("args",args);
        paramMap.put("errorMsg",errorMsg);
        paramMap.put("stackTrace", stackTrace);
        alarm(paramMap);
    }

    private void alarm(Map<String,Object> paramMap) {
        try {
            String content = VelocityUtils.getContent("vm/alarm.vm",new VelocityContext(paramMap));
            bearyChatHelper.sendMsg(content,"异常报警,请及时处理！");
        } catch (Exception e) {
            logger.error("alarm error ,e : {}", e);
        }
    }
}
