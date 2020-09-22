package com.example.demogradle.test;

import com.alibaba.fastjson.JSON;

/**
 * fastjson关于is开头的字段名的序列化问题
 * @author zhangchuan
 */
public class TestBean {

    /**
     * 非boolean类型， is开头字段， get方法使用getIsXXX
     */
    private String isFlag;
    /**
     * 非boolean类型， is开头字段， get方法使用isXXX
     */
    private String isName;
    /**
     * boolean类型， is开头字段， get方法使用getIsXXX
     */
    private boolean isAge;
    /**
     * boolean类型， is开头字段， get方法使用isXXX
     */
    private boolean isSuccess;

    public String getIsFlag() {
        return isFlag;
    }

    public String isName() {
        return isName;
    }

    public boolean getIsAge() {
        return isAge;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag;
    }

    public void setIsName(String isName) {
        this.isName = isName;
    }

    public void setAge(boolean age) {
        isAge = age;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }


    /**
     * 总结
     * is开头的非boolean类型字段，使用getIsXXX方法，序列化后字段名不变
     * is开头的非boolean类型字段，使用isXXX方法，序列化之后消失
     * is开头的boolean类型字段，使用getIsXXX方法，序列化之后字段名不变
     * is开头的boolean类型字段，使用isXXX方法，序列化之后字段名前的is被去除
     */
    public static void main(String[] args) {
        TestBean bean = new TestBean();
        bean.setIsFlag("true");
        bean.setIsName("true");
        bean.setAge(true);
        bean.setSuccess(true);
        System.out.println(JSON.toJSONString(bean));
    }

}