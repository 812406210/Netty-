package com.example.netty.lesson2;

import org.jboss.netty.bootstrap.ServerBootstrap;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangwj
 * @date 2020/4/4 10:52
 */
public class NettyServer {
    public static void main(String[] args) {
        //服务类
        ServerBootstrap bootstrap = new ServerBootstrap();
        //boss相当于大门的门卫，负责通报
        ExecutorService boss =  Executors.newCachedThreadPool();
        //work接收boss门卫的通报，然后进行数据分发
        ExecutorService work =  Executors.newCachedThreadPool();

        //设置nioserver工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,work));

        //设置管道
        bootstrap.setPipelineFactory(()->{
            ChannelPipeline pipeLine = Channels.pipeline();
            //pipeLine可以认为是一个过滤器
            pipeLine.addLast("decoder",new StringDecoder()); //将接受的消息转为String
            pipeLine.addLast("encoder",new StringEncoder()); //将发送的消息转为String
            pipeLine.addLast("helloHandler",new HelloHandler());
            return pipeLine;

        });
        bootstrap.bind(new InetSocketAddress(51503));
        System.out.println("服务端start!");
    }
}
