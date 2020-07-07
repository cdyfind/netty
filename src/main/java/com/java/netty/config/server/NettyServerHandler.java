package com.java.netty.config.server;

import com.java.netty.config.cache.ChannelMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server received: "+in.toString(CharsetUtil.UTF_8));

    }

    //表示连接时活动状态，客户端主动连接服务端，保存ChannelHandlerContext到缓存
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelMap.ctx.put("ctx",ctx);
    }



}
