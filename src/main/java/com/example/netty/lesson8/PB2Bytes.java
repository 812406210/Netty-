package com.example.netty.lesson8;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 * protobuf学习
 * @author yangwj
 * @date 2020/4/5 10:43
 */
public class PB2Bytes {

    public static void main(String[] args) throws InvalidProtocolBufferException {
       byte[] bs =  toBytes();
       toPlayer(bs);
    }

    /**
     * 序列化
     */
    public static byte[] toBytes(){
        //获取一个PBPlayer构造器
       PlayerModule.PBPlayer.Builder builder =  PlayerModule.PBPlayer.newBuilder();
       //设置数据
       builder.setAge(20).setName("yangwj").setPlayerId(1).addSkills(1001);
        //构造出对象
        PlayerModule.PBPlayer player = builder.build();
//        System.out.println(player);
        //序列化成字节数组
        byte[] byteArray = player.toByteArray();
        System.out.println(Arrays.toString(byteArray));
       return byteArray;
    }


    /**
     * 反序列化
     */
    public static void toPlayer(byte[] bs) throws InvalidProtocolBufferException {
        PlayerModule.PBPlayer player = PlayerModule.PBPlayer.parseFrom(bs);
        System.out.println("player:"+player.toString());
    }
}
