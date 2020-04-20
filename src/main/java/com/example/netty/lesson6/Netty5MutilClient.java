package com.example.netty.lesson6;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangwj
 * @date 2020/4/4 22:33
 */
public class Netty5MutilClient {
    //服务类
    private Bootstrap bootstrap = new Bootstrap();
    //会话
    private List<Channel> channels = new ArrayList<>();
    //引用计数器
    private final AtomicInteger index = new AtomicInteger();
    //初始化
    public void init(int count){
        EventLoopGroup worker = new NioEventLoopGroup();
        //设置线程池
        bootstrap.group(worker);

        //设置socket工厂
        bootstrap.channel(NioSocketChannel.class);

        //设置管道
        bootstrap.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new StringDecoder());
                channel.pipeline().addLast(new StringEncoder());
                channel.pipeline().addLast(new ClientHandler());
            }
        });
        for (int i =1; i<count; i++){
            //连接服务端
            ChannelFuture connect = bootstrap.connect("127.0.0.1",51503);
            channels.add(connect.channel());
        }

    }

    /**
     * 获取会话
     * @return
     */
    public Channel nextChannel(){
        return getFirstActiveChannel(0);
    }

    private Channel getFirstActiveChannel(int count){
        Channel channel = channels.get(Math.abs(index.getAndIncrement() % channels.size()));
        if(!channel.isActive()){
            //重连
            reconnect(channel);
            if(count >= channels.size()){
                throw new RuntimeException("no can use channel");
            }
            return getFirstActiveChannel(count + 1);
        }
        return channel;

    }

    /**
     * 重连
     * @param channel
     */
    private void reconnect(Channel channel){
        synchronized(channel){
            if(channels.indexOf(channel) == -1){
                return ;
            }
            Channel newChannel = bootstrap.connect("127.0.0.1", 51503).channel();
            channels.set(channels.indexOf(channel), newChannel);
        }
    }


}
