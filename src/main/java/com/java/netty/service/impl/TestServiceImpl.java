package com.java.netty.service.impl;

import com.java.netty.config.cache.ChannelMap;
import com.java.netty.service.TestService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void sendMessage() {
        ChannelHandlerContext ctx = ChannelMap.ctx.get("ctx");
        String msg = "A message111";
        byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
        ctx.write(buf);
        ctx.flush();
    }
}
