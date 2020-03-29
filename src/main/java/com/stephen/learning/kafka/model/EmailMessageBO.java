package com.stephen.learning.kafka.model;

import lombok.Data;

import java.util.Map;

/**
 * @author jack
 * @description
 * @date 2020/3/29
 */
@Data
public class EmailMessageBO {
    private String code;
    private String locale;
    private String address;
    private String subject;
    private Map<String,Object> params;
    private String content;
}
