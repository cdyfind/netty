package com.java.netty.config.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class NettyServer {
    /**
     * boss 线程组用于处理链接工作
     */
    private EventLoopGroup boss = new NioEventLoopGroup();

    /**
     * worl 线程组用户处理数据工作
     */
    private EventLoopGroup work = new NioEventLoopGroup();

    @Value("${netty.port}")
    private Integer port;

    /**
     * 启动Netty Server
     *
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException{
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss,work)
                //指定Channel
                .channel(NioServerSocketChannel.class)
                //使用指定的端口号设置套接字地址
                .localAddress(new InetSocketAddress(port))
                //服务端可连接队列数，对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG,1024)
                //设置TCP长连接，一般如果两个小时内没有数据通信时，tcp会自动发一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                //将小的数据包包装成跟更大的帧进行传送，提高网络负载
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childHandler(new NettyServerHandlerInitializer());
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()){
            log.info("启动 Netty Server");
        }
    }

    @PreDestroy
    public void destory() throws InterruptedException{
        boss.shutdownGracefully().sync();
        boss.shutdownGracefully().sync();
        log.info("关闭Netty");
    }

}