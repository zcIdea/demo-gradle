package com.example.demogradle.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @description: 签名算法
 * @author zhangChuan
 * @date 2020/10/9 11:09 上午
 */
public class Md5Demo {


    /**
     * sign 签名 （参数名按ASCII码从小到大排序（字典序）+key+MD5+转小写签名）
     * @return
     */
    public static String createSign(Map params, String key) {

        StringBuffer sbkey = new StringBuffer();
        // entrySet 所有参与传参的参数按照accsii排序（升序）
        Set es = params.entrySet();
        Iterator it = es.iterator();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                sbkey.append(k + "=" + v + "&");
            }
        }

        sbkey = sbkey.append("key=" + key);
        System.out.println(sbkey);
        //MD5加密,结果转换为小写字符
        String sign = encodeByMD5(sbkey.toString()).toLowerCase();
        return sign;
    }

    /**
     * 对字符串进行MD5加密
     *
     * @param str 需要加密的字符串
     * @return 小写MD5字符串 32位
     */
    public static String encodeByMD5(String str) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getParam(){
        SortedMap<String, Object> params = new TreeMap<>();
        params.put("opening_type", 0);
        params.put("user_id", "110");
        params.put("front_url", "www.baidu.com");
        params.put("ip", "192.168.16.187");

        return createSign(params, "zcy@0808");
    }

    public static void main(String[] args) {
        SortedMap<String, Object> params = new TreeMap<>();
        params.put("user_id", "110");
//        params.put("app", 1024);
//        params.put("time", 1545964668);
//        params.put("case", "");
//        params.put("updateTime", null);
//        params.put("sign", createSign(params, "zcy@0808"));

        System.out.println("sign："+createSign(params, "zcy@0808"));
        System.out.println("sign："+getParam());
    }



}
