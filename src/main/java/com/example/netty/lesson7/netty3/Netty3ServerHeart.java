package com.example.netty.lesson7.netty3;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
/**
 * @author yangwj
 * @date 2020/4/4 23:06
 */
public class Netty3ServerHeart {

    public static void main(String[] args) {

        //服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //boss线程监听端口，worker线程负责数据读写
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //设置niosocket工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));

        final HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
        //设置管道的工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {

                ChannelPipeline pipeline = Channels.pipeline();
                //IdleStateHandler设置心跳时间
                pipeline.addLast("idle", new IdleStateHandler(hashedWheelTimer, 5, 5, 10));
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("helloHandler", new HelloHandler());
                return pipeline;
            }
        });

        bootstrap.bind(new InetSocketAddress(51503));

        System.out.println("start!!!");

    }

}

