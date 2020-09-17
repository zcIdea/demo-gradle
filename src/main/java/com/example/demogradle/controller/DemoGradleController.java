package com.example.demogradle.controller;

import com.example.demogradle.annotation.DemoAnnotation;
import com.example.demogradle.properties.ConfigurationPropertiesDemo;
import com.example.demogradle.service.DemoGradleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangchuan
 */
@RestController
@RequestMapping("demo")
@Slf4j
public class DemoGradleController {

    @Resource
    private DemoGradleService demoGradleService;

    @Resource
    private ConfigurationPropertiesDemo properties;

    /**
     *
     * @return
     */
    @GetMapping("gradle")
    @DemoAnnotation(value = "value",name = "demoAnnotation",num = 10,flag = false)
    public String demo(){

        log.info("username:{}",properties.getUsername());

        log.info("Controller demo 步骤：1");
        demoGradleService.queryDemo(2);
        log.info("Controller demo 步骤：3");

        return "hello gradle";
    }

}
