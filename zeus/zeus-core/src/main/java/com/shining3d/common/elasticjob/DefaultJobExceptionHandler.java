package com.shining3d.common.elasticjob;

import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;
import com.shining3d.common.NetWorkUtils;
import com.shining3d.common.SpringContextUtil;
import com.shining3d.common.VelocityUtils;
import com.shining3d.zeus.ZeusConfig;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by fe on 2016/12/8.
 */
public class DefaultJobExceptionHandler implements JobExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(DefaultJobExceptionHandler.class);

    @Override
    public void handleException(String jobName, Throwable cause) {
        try {
            JavaMailSenderImpl senderImpl = (JavaMailSenderImpl) SpringContextUtil.getBean("mailSender");
            ZeusConfig zeusConfig = (ZeusConfig) SpringContextUtil.getBean("zeusConfig");

            // 建立邮件消息,发送简单邮件和html邮件的区别
            MimeMessage mailMessage = senderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
            // 设置收件人，寄件人
            String[] sendTos = buildSendTo(zeusConfig.getJobMailReceivers());

            if (sendTos != null && sendTos.length > 0) {
                messageHelper.setTo(sendTos);
                String nick = javax.mail.internet.MimeUtility.encodeText(zeusConfig.getJobMailSender());
                messageHelper.setFrom(new InternetAddress(nick + "<" + senderImpl.getUsername() + ">"));
                messageHelper.setSubject("应用:[" + zeusConfig.getJobMailProject() + "],环境:[" + zeusConfig.getJobMailEnv() + "],hostName : [" + NetWorkUtils.getHostName() + "], jobName : [" + jobName + "] 异常!");
                // true 表示启动HTML格式的邮件
                VelocityContext velocityContext = new VelocityContext();
                velocityContext.put("env",zeusConfig.getJobMailEnv());
                velocityContext.put("ip",NetWorkUtils.getHostIp());
                velocityContext.put("hostName",NetWorkUtils.getHostName());
                velocityContext.put("jobName",jobName);
                velocityContext.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                velocityContext.put("exception",transform(cause));
                String html = VelocityUtils.getContent("vm/job_exception_mail.vm",velocityContext);
                messageHelper.setText(html, true);
                senderImpl.send(mailMessage);
            }
        } catch (Exception e) {
            logger.error("error , e : {}",e);
        }

    }

    private String[] buildSendTo(String receivers) {
        String[] buildTos = receivers.split(",");
        return buildTos;
    }

    public String transform(final Throwable cause) {
        if (null == cause) {
            return "";
        }
        StringWriter result = new StringWriter();
        PrintWriter writer = new PrintWriter(result);
        cause.printStackTrace(writer);
        return result.toString();
    }
}
