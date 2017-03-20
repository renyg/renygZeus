package com.shining3d.zeus.mq.listener;

import com.rabbitmq.client.Channel;
import com.shining3d.common.Assert;
import com.shining3d.common.FastJson;
import com.shining3d.zeus.dao.ProductDao;
import com.shining3d.zeus.entity.ProductEntity;
import com.shining3d.zeus.enums.StateEnum;
import com.shining3d.zeus.exception.BizException;
import com.shining3d.zeus.mq.message.ProductMessage;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * Created by fe on 2017/01/05.
 * 商品状态定时修改队列监听
 */
@Component("productStateModifyListener")
public class ProductStateModifyListener implements ChannelAwareMessageListener {

    public static final Logger logger = LoggerFactory.getLogger(ProductStateModifyListener.class);

    @Resource
    private ProductDao productDao;

    @Override
    public void onMessage(Message message, Channel channel) {
        String productMessageJson = new String(message.getBody());
        logger.info("商品状态定时修改： {}", productMessageJson);

        try {
            Optional.ofNullable(productMessageJson).orElseThrow(() -> {
               return new BizException("message获取异常！");
            });

            ProductMessage pm = FastJson.fromJson(productMessageJson,ProductMessage.class);

            Long productId = pm.getProductId();
            String state = pm.getState();
            Long executeTime = pm.getExecuteTime();
            ProductEntity productEntity = productDao.selectByPrimaryKey(productId);

            if (productEntity != null) {
                Date dbOnlineTime = productEntity.getOnlineTime();
                Date dbOfflineTime = productEntity.getOfflineTime();

                Date checkDate = null;
                if (state.equals(StateEnum.ONLINE.getCode())) checkDate = dbOnlineTime;

                if (state.equals(StateEnum.OFFLINE.getCode())) checkDate = dbOfflineTime;

                Assert.assertNotNull(checkDate,"state异常!");

                //TODO 队列数据时间  和 db时间需要做一次check 防止同一个数据提交多次
                if (executeTime.longValue() == checkDate.getTime()) {
                    ProductEntity updateProductEntity = new ProductEntity();
                    updateProductEntity.setId(productId);
                    updateProductEntity.setGmtModified(new Date());
                    updateProductEntity.setState(state);

                    productDao.updateByPrimaryKeySelective(updateProductEntity);
                }
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            logger.error("product状态修改异常", e);
        }

    }
}
