package com.example.netty.lesson11.packet;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * @author yangwj
 * @date 2020/4/6 10:48
 */
public class MyDecoder extends FrameDecoder {
    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer buffer) throws Exception {
        if(buffer.readableBytes() >4){
            //标记
            buffer.markReaderIndex();
            //长度
            int length = buffer.readInt();
            if(buffer.readableBytes() < length){
                buffer.resetReaderIndex();
                //缓存当前剩余的buffer数据，等待剩下的数据到来
                return null;
            }
            //读数据
            byte[] bytes = new byte[length];
            buffer.readBytes(bytes);
            //往下传递 ,和sendUpstream类似
            return  new String(bytes);
        }
        //缓存当前剩余的buffer数据，等待剩下的数据到来
        return null;
    }
}
