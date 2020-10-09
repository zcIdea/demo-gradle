package com.example.demogradle;

import com.example.demogradle.netty.client.TimeClient;
import com.example.demogradle.netty.server.TimeServer;
import com.example.demogradle.properties.ConfigurationPropertiesDemo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
@SpringBootTest
public class DemoGradleApplicationTests {


    @Autowired
    private ConfigurationPropertiesDemo properties;

//    @Value("${demo.username}")
//    private String username;


    @Test
    public void contextLoads() {
        System.out.println("username:" + properties.getUsername());
//        System.out.println("@Value->username:"+username);
    }


    /**
     * netty服务测试端
     */
    @Test
    public void startNettyServer1() throws Exception {
        new TimeServer().startServerAndBind(8808);
    }
    /**
     * 访问测试端
     */
    @Test
    public void startNettyClient() throws Exception {
        new TimeClient().connect(8808, "127.0.0.1");
    }
}
