package com.example.demogradle.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 自定义线程池
 * @author zhangchuan
 * @date 2020=09-14
 */
@Configuration
public class AsyncConfig {

    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 50;
    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 20;

    /**
     * 初始化线程池
     * @return AsyncTaskExecutor对象
     */
    public AsyncTaskExecutor asyncTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setThreadNamePrefix("async-task-thread-pool");
        taskExecutor.initialize();
        return taskExecutor;
    }


}
