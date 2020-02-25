package com.listener;

import com.aliyuncs.exceptions.ClientException;
import com.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/24
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Component
@RabbitListener(queues = "sms")
public class Customer {
    //模板编号
    @Value("${aliyun.sms.template_code}") private String template_code;
    //签名
    @Value("${aliyun.sms.sign_name}") private String sign_name;

    @Autowired
    private SmsUtil smsUtil;

    @RabbitHandler
    private void excuteSms(Map<String, String> map) {
        System.out.println("手机号：" + map.get("mobile"));
        System.out.println("验证码：" + map.get("code"));
       try {
           smsUtil.sendSms(map.get("mobile"),template_code,sign_name," {\"checkcode\":\""+ map.get("code") +"\"}");
       } catch (ClientException e)
       { e.printStackTrace(); }
    }
    }
