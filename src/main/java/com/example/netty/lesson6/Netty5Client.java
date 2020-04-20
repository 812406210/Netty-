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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yangwj
 * @date 2020/4/4 21:59
 */
public class Netty5Client {
    public static void main(String[] args) {
        //服务类
        Bootstrap bootstrap = new Bootstrap();

        //woker
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
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
        //连接服务端
        ChannelFuture connect = bootstrap.connect("127.0.0.1",51503);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
                System.out.println("请输入：");
                String msg = null;

                msg = bufferedReader.readLine();
                connect.channel().writeAndFlush(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
        }
    }
}
