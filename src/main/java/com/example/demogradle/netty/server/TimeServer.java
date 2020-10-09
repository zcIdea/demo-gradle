package com.example.demogradle.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.catalina.Server;

/**
 * @author zhangChuan
 * @description: netty服务端
 * @date 2020/9/27 4:32 下午
 */
public class TimeServer {


    public void startServerAndBind(int port){

        //声明Boss线程组
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        // 声明Worker线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup).
                    channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG,1024).
                    childHandler(new ChildChannelHandler());

            //绑定端口，同步等待成功
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
