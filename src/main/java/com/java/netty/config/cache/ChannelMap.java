package com.java.netty.config.cache;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChannelMap {
    public static Map<String, ChannelHandlerContext> ctx = new HashMap<>();

}
