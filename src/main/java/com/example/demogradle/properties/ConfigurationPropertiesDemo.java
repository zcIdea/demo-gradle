package com.example.demogradle.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: TODO
 * @author zhangChuan
 * @date 2020/9/17 2:49 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "demo")
@PropertySource(value = {"classpath:application.properties"},encoding = "utf-8")
public class ConfigurationPropertiesDemo {

    /**
     * 姓名
     */
    private String username;
    /**
     * 密码
     */
    private String password ;
    /**
     * 地址
     */
    private String remoteAddress;
    /**
     * 地址
     */
    private String phone;
    /**
     * 年龄
     */
    private int age;

}
