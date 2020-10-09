package com.example.demogradle.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @description:  I/O事件处理类,初始化
 * @author zhangChuan
 * @date 2020/9/27 4:48 下午
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ch.pipeline()
                // region 解决粘包/拆包问题相关代码
                .addLast(new LineBasedFrameDecoder(1024))
                // 将接收到的对象转成字符串
                .addLast(new StringDecoder())
                .addLast(new DemoServerHandler());
    }
}
