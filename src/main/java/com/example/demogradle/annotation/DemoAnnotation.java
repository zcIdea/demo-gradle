package com.example.demogradle.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @author zhangchuan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface DemoAnnotation {

    /**
     * 默认值
     * @return
     */
    String value() default "";

    /**
     * 名称
     * @return
     */
    String name() default "";

    /**
     * 数字
     * @return
     */
    int num() default 0;

    /**
     * 是否
     * @return
     */
    boolean flag() default true;
}
