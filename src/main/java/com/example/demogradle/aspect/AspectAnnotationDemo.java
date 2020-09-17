package com.example.demogradle.aspect;

import com.example.demogradle.annotation.DemoAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * aop注解的使用实例 @Aspect和@Annotation
 * @author zhangchuan
 * @date 2020-09-14
 */
@Aspect
@Component
@Slf4j
public class AspectAnnotationDemo {


    /**
     * 统一切点,对com.example.demogradle.controller及其子包中所有的类的所有方法切面
     */
    @Pointcut("@annotation(com.example.demogradle.annotation.DemoAnnotation)")
    public void pointcut(){
    }

    /**
     * 前置通知
     * @param joinPoint 切点
     */
    @Before("pointcut()")
    public void beforeMethod(JoinPoint joinPoint){
        log.info("@annotation @Before 调用了前置通知！");

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String methodName = methodSignature.getName();
        log.info("@Before：方法名称：" + methodName);
        //参数名称
        String[] parameterNames = methodSignature.getParameterNames();
        log.info("@Before：参数名称：" + Arrays.toString(parameterNames));
        log.info("@Before：参数值为：" + Arrays.toString(joinPoint.getArgs()));

    }
    /**
     * 后置通知
     * @param joinPoint 切点
     */
    @After("pointcut()")
    public void afterMethod(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("参数【"+i+"】："+args[i]);
        }
        log.info("@annotation @After 调用了后置通知！");
    }

    /**
     * 返回通知 result为返回内容 @AfterRunning
     * @param joinPoint 切点
     * @param result 返回结果
     */
    @AfterReturning(value="pointcut()",returning="result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        log.info("@annotation @AfterReturning 调用了返回通知：{}",result);
    }
    /**
     * 异常通知 @AfterThrowing
     * @param joinPoint 切点
     * @param e 异常对象
     */
    @AfterThrowing(value="pointcut()",throwing="e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e){
        log.info("@annotation @AfterThrowing 调用了异常通知:{}",e.getMessage());
    }

    /**
     * 环绕通知
     * 获取注解中的参数：@annotation(demoAnnotation)，argNames属性不加也可以（参数值 必须与方法中的参数名相同）
     * @param point 参数
     * @param demoAnnotation 注解对象
     * @return Object
     */
    @Around(value = "pointcut() && @annotation(demoAnnotation)", argNames = "point, demoAnnotation")
//    @Around(value = "Pointcut() && @annotation(demoAnnotation)")
    public Object aroundMethod(ProceedingJoinPoint point, DemoAnnotation demoAnnotation){
        log.info("@annotation around执行方法之前");

        log.info("注解的参数--》 name:{}; num:{}",demoAnnotation.name(),demoAnnotation.num());
        log.info(String.format("注解的参数--》name：%s，num：%s", demoAnnotation.name(),demoAnnotation.num()));

        Object object = null;
        try {
            object = point.proceed();
        } catch (Throwable throwable) {
            log.error("@Around @annotation error message:{}",throwable.getMessage());
        }
        log.info("@annotation around执行方法之后--返回值：" +object);
        return object;
    }

    public static void main(String[] args) {
        //String.format的使用 ：%s 字符串
        log.info(String.format("注解的参数--》name：%s，num：%s", "demoAnnotation","demoAnnotation.num()"));

    }

}
