package com.example.netty.lesson6;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 单客户端多连接
 * @author yangwj
 * @date 2020/4/4 22:49
 */
public class Netty5MutilClientStart {
    public static void main(String[] args) {
        Netty5MutilClient client = new Netty5MutilClient();

        client.init(5);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                System.out.println("请输入：");
                String msg = bufferedReader.readLine();
                client.nextChannel().writeAndFlush(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
