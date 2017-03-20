package com.shining3d.zeus.helper;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.shining3d.common.HttpClientUtils;
import com.shining3d.zeus.ZeusConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengpanwang on 2017/2/8.
 */
@Component
public class BearyChatHelper {
    @Resource
    private ZeusConfig zeusConfig;


    public void sendMsg(String title, String msg) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("text", title);
        param.put("markdown",true);

        Map<String, Object> attachments = Maps.newHashMap();
        attachments.put("title", "异常");
        attachments.put("text", msg);
        attachments.put("color","#ffa500");
        Map<String,String> urlMap = new HashMap<String,String>();
        urlMap.put("url","http://a0.att.hudong.com/77/31/20300542906611142174319458811.jpg");
        attachments.put("images", Arrays.asList(urlMap));

        param.put("attachments", Arrays.asList(attachments));

        HttpClientUtils.post(zeusConfig.getBearyChatWarnUrl(), JSON.toJSONString(param));
    }

}
