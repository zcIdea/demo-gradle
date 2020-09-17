package com.example.demogradle.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description: 定时任务使用实例
 * @author zhangChuan
 * @date 2020/9/14 6:03 下午
 */
@Component
@Slf4j
public class TimingTask {
    /**
     *   0/5 10 18 * * ? ==> 每天下午6点10分执行,0秒开始，每隔五秒执行一次
     *
     *   *  表示所有值。 例如:在分的字段上设置 *,表示每一分钟都会触发。
     *
     *   ?  表示不指定值。使用的场景为不需要关心当前设置这个字段的值。例如:要在每月的10号触发一个操作，但不关心是周几，所以需要周位置的那个字段设置为”?” 具体设置为 0 0 0 10 * ?
     *
     *   -  表示区间。例如 在小时上设置 “10-12”,表示 10,11,12点都会触发。
     *
     *   , 表示指定多个值，例如在周字段上设置 “MON,WED,FRI” 表示周一，周三和周五触发
     *
     *   /  用于递增触发。如在秒上面设置”5/15” 表示从5秒开始，每增15秒触发(5,20,35,50)。 在月字段上设置’1/3’所示每月1号开始，每隔三天触发一次。
     */
    @Scheduled(cron = "0/5 13 18 * * ?")
    public void demoTask(){
        log.info("定时任务正在执行。。。。。。。。。。。。");
    }


}
