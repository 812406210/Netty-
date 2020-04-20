package com.example.netty.lesson11.packet;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class MyHandler1 extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

//        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
//
//        byte[] array = buffer.array();
//        String message = new String(array);
        String message = (String) e.getMessage();
        System.out.println("handler1:" + message);

    }
}
