package com.example.demogradle.service.impl;

import com.example.demogradle.service.DemoGradleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: zhangChuan
 * @createDate: 2020/9/14 5:19 下午
 */
@Service
@Slf4j
public class DemoGradleServiceImpl implements DemoGradleService {


    /**
     * 异步方法的使用：@Async，利用线程池的方式 ，启动类要添加注释：@EnableAsync
     * @param num 号码
     * @return
     */
    @Async
    @Override
    public String queryDemo(int num) {
        log.info("当前线程：{}",Thread.currentThread().getName());
        log.info("侬好啊！ 我是异步的方法；num：{}",num);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("DemoGradleServiceImpl.queryDemo error:{}",e.getMessage());
        }
        return "侬好啊！";
    }
}
