package com.example.netty.lesson2;

import org.jboss.netty.channel.*;

/**
 * @author yangwj
 * @date 2020/4/4 12:44
 */
public class HiHandler extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("打印server的信息："+e.getMessage());
//        ChannelBuffer message = (ChannelBuffer) e.getMessage();
//        String msg = new String(message.array());
        String msg = (String) e.getMessage();
        System.out.println("打印server的信息："+msg);

        ctx.getChannel().write("hi,你好！！");
        super.messageReceived(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    /**
     * 连接时触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        //可以设置黑名单
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    /**
     * 断开时首先触发这个
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
     * channelDisconnected执行完，才会触发这个
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}
