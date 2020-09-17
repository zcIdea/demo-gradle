package com.example.demogradle.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。处理集合类
 * @author zhangChuan
 * @date 2020/9/15 3:28 下午
 */
@Slf4j
public class StreamDemo {

    /**
     * stream()：将集合转换为流
     * filter():根据条件过滤数据
     * collect(Collectors.toList())：将流转为集合
     */
    public void arrayDemo(){
        List<String> arrList= Arrays.asList("133","","rewr","","null","la");
        List<String> stringList = arrList.stream().filter(arr -> !arr.isEmpty()).collect(Collectors.toList());
        stringList.forEach(ss->{
            log.info("输出值：{}",ss);
        });
    }

    public void listDemo(){
        List<String> arrList= Arrays.asList("133","","rewr","","null","la","la");
        List<String> stringList = arrList.stream()
                //过滤条件
              .filter(arr -> !arr.isEmpty())
                //排序，正序
              .sorted()
                //获取指定数量数据
              .limit(21)
                //去重复数据
              .distinct()
                //流转为集合
              .collect(Collectors.toList());

        //直接遍历list集合。通过lambda表达式
        stringList.forEach(ss->{
            log.info("输出值：{}",ss);
        });

        //通过流的方式循环遍历每个元素
        arrList.stream().forEach(a->{
            log.info("forEach 输出值：{}",a);
        });

        //map 方法用于映射每个元素到对应的结果，可以对每个元素进行额外对操作
        arrList.stream().map(a->a+"=-=-=-=-=").forEach(b->log.info("map 输出值：{}",b));

    }

    public static void main(String[] args) {
        StreamDemo streamDemo=new StreamDemo();
//        streamDemo.arrayDemo();
        streamDemo.listDemo();
    }


}
