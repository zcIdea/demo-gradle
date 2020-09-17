package com.example.demogradle.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Aspect aop注解的使用实例
 * @author zhangchuan
 * @date 2020-09-14
 */
//@Aspect
//@Component
@Slf4j
public class AspectDemo {


    /**
     * 统一切点,对com.example.demogradle.controller及其子包中所有的类的所有方法切面
     */
    @Pointcut("execution(public * com.example.demogradle.controller.*.*(..))")
    public void Pointcut(){
    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("Pointcut()")
    public void beforeMethod(JoinPoint joinPoint){
        log.info("调用了前置通知！");
    }
    /**
     * 后置通知
     * @param joinPoint
     */
    @After("Pointcut()")
    public void afterMethod(JoinPoint joinPoint){
        log.info("调用了后置通知！");
    }

    /**
     * @AfterRunning: 返回通知 rsult为返回内容
     */
    @AfterReturning(value="Pointcut()",returning="result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        log.info("调用了返回通知");
    }
    /**
     * @AfterThrowing: 异常通知
     */
    @AfterThrowing(value="Pointcut()",throwing="e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e){
        log.info("调用了异常通知");
    }

    /**
     * 环绕通知
     * @param point 参数
     * @return Object
     */
    @Around("Pointcut()")
    public Object aroundMethod(ProceedingJoinPoint point){
        log.info("around执行方法之前");
        Object object = null;
        try {
            object = point.proceed();
        } catch (Throwable throwable) {
            log.error("@Around error message:{}",throwable.getMessage());
        }
        log.info("around执行方法之后--返回值：" +object);
        return object;
    }



}
