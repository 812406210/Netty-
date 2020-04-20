package com.example.netty.lesson6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author yangwj
 * @date 2020/4/4 22:08
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("客户端收到的消息："+msg);
    }
}
