package com.shining3d.zeus.mq;

import com.shining3d.zeus.ZeusConfig;
import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by fe on 2017/1/5.
 */
@Component
public class MQHelper {

    public static final Logger logger = LoggerFactory.getLogger(MQHelper.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    @Resource
    private ZeusConfig zeusConfig;



    public void sendMq(String routeKey, String jsonMsg, long expiration) {
        logger.info("send msg to mq ，routeKey:{}, msg : {}, expiration : {}", routeKey, jsonMsg, expiration);
        try {
            MessageProperties properties = new MessageProperties();
            properties.setExpiration(String.valueOf(expiration));
            Message message = new Message(jsonMsg.getBytes(), properties);
            amqpTemplate.convertAndSend(zeusConfig.getZeusDirectExchange(), routeKey, message);

        } catch (Exception e) {
            logger.error("send mq error , e : {}",e);
            throw new BizException(BizConstant.COMMON_BIZ_ERROR,"mq队列异常");
        }
    }
}
