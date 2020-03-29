package com.stephen.learning.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.stephen.learning.kafka.model.EmailMessageBO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jack
 * @description
 * @date 2020/3/28
 */
@RestController
public class MessageController {
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/kafka")
    public String testKafka() {
        EmailMessageBO emailMessageBO = new EmailMessageBO();
        emailMessageBO.setAddress("1475323502@qq.com");
        emailMessageBO.setCode("10020001");
        emailMessageBO.setLocale("zh_CN");
        emailMessageBO.setSubject("测试邮件");
        kafkaTemplate.send("sms", JSON.toJSONString(emailMessageBO));
        return "success";
    }

    @KafkaListener(topics = "sms")
    public void receive(ConsumerRecord<?, ?> consumer) {
        logger.info("{} - {}:{}", consumer.topic(), consumer.key(), consumer.value());
    }
}
