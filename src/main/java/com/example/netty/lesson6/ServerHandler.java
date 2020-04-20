package com.example.netty.lesson6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author yangwj
 * @date 2020/4/4 21:41
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("接受的消息："+msg);

        //返回给客户端信息
        ctx.channel().writeAndFlush("服务端返回给你：你好");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //连接时触发
        System.out.println("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //断开时触发
        System.out.println("channelInactive");
    }
}
