package com.example.netty.lesson2;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangwj
 * @date 2020/4/4 12:38
 */
public class NettyClient {
    public static void main(String[] args) {
        //服务类
        ClientBootstrap bootstrap = new ClientBootstrap();
        //线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService work = Executors.newCachedThreadPool();

        //管道工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss,work));
        bootstrap.setPipelineFactory(()->{
            ChannelPipeline pipeLine = Channels.pipeline();
            pipeLine.addLast("decoder",new StringDecoder());
            pipeLine.addLast("encoder",new StringEncoder());
            pipeLine.addLast("hiHandler",new HiHandler());
            return pipeLine;
        });
        //连接服务端
        ChannelFuture connect =  bootstrap.connect(new InetSocketAddress("127.0.0.1",51503));
        Channel channel = connect.getChannel();
        System.out.println("客户端start");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入：");
            channel.write(scanner.next());
        }

    }
}
