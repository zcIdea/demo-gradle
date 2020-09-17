package com.example.demogradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 使用gradle构建项目
 * 启用异步调用：@EnableAsync
 * @author zhangchuan
 * @date 2020-09-09
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class DemoGradleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoGradleApplication.class, args);
    }

}
