package com.example.netty.lesson6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author yangwj
 * @date 2020/4/4 21:29
 */
public class Netty5Server {
    public static void main(String[] args) {
        //服务类
        ServerBootstrap bootstrap = new ServerBootstrap();
        //boss worker----其实就是线程池
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            //设置线程池
            bootstrap.group(boss,worker);

            //设置socket工厂
            bootstrap.channel(NioServerSocketChannel.class);

            //设置管道工厂
            bootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new StringDecoder());
                    channel.pipeline().addLast(new StringEncoder());
                    channel.pipeline().addLast(new ServerHandler());
                }
            });
            //设置参数
            bootstrap.option(ChannelOption.SO_BACKLOG,2048);//表示可以接受2048连接(连接缓冲池设置)
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);//维持连接的活跃，清除死连接
            bootstrap.childOption(ChannelOption.TCP_NODELAY,true);//关闭延迟发送
            //绑定端口
            ChannelFuture future = bootstrap.bind(51503);
            //等待服务端关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
