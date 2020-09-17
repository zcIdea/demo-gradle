package com.example.demogradle.test;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @description: lambda表达式，把方法体（实现接口对唯一对方法）当成参数
 * @author zhangChuan
 * @date 2020/9/15 4:01 下午
 */
@Slf4j
public class LambdaDemo {

    public void action(String msg,LambdaInterface lambdaInterface){
        lambdaInterface.action(msg);
    }
    public void action1(LambdaInterface1 lambdaInterface){
        lambdaInterface.action();
    }

    /**
     * 方法的引用
     * 方法引用通过方法的名字来指向一个方法。
     * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
     * 方法引用使用一对冒号 :: 。
     */
    public void methodDemo(){
        final Car car = new Car();
        final List< Car > cars = Arrays.asList( car );

        cars.forEach( Car::collide );
        cars.forEach( Car::repair );

    }



    public static void main(String[] args) {

        List<String> users = new ArrayList<>();
        users.add("张三");
        users.add("李四");
        users.add("王五");

        users.forEach((String ss) -> {log.info("{}",ss);});
        users.forEach((ss) -> log.info("{}",ss));

        //方法引用
        users.forEach(System.out::println);

        Map<String,String > map=new HashMap<>(16);
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        map.put("key5","value5");

        map.forEach((k,v)->{
            log.info("输出值 key:{};value:{}",k,v);
        });

        LambdaDemo lambdaDemo = new LambdaDemo();

        //使用自定义lambda表达式,有参数，一个参数括号可以不加，没有参数和多个参数括号必须加的
        lambdaDemo.action("hello lambda!",dd -> {
            log.info(dd);
        });
        //使用自定义lambda表达式，无参数，一个空对括号
        lambdaDemo.action1( () -> {
            log.info("无参数 hello lambda!");
        });

        //线程lambda的使用
        Thread thread  =new Thread(() -> {
            log.info("当前线程名字：{}",Thread.currentThread().getName());
        });
        thread.start();

        lambdaDemo.methodDemo();


    }


}
