package com.example.netty.lesson1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.*;

/**
 * @author yangwj
 * @date 2020/4/4 9:42
 */
public class OioServer {
    public static void main(String[] args) throws IOException {
        ExecutorService newCacheThreadPool = newCachedThreadPool();
        //创建Socket服务，监听10000端口
        ServerSocket server = new ServerSocket(51503);
        System.out.println("服务启动");
        while (true){
            //获取一个套接字(阻塞)
            final Socket socket = server.accept();
            System.out.println("来了一个客户端");
            newCacheThreadPool.execute((Runnable)()-> {
                    handler(socket);
            });
        }
    }

    private static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true){
                //读取数据(阻塞)
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("socket关闭");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
