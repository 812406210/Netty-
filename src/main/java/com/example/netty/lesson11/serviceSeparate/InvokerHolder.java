package com.example.netty.lesson11.serviceSeparate;

import java.util.HashMap;
import java.util.Map;

/**
 * 执行器管理者
 * @author yangwj
 * @date 2020/4/6 16:41
 */
public class InvokerHolder {
    public static Map<Short, Map<Short,Invoker>> invokers = new HashMap<>();


    /**
     * 添加一个执行器
     * @param module
     * @param cmd
     * @param invoker
     */
    public static void addInvoker(short module,short cmd ,Invoker invoker){
        Map<Short,Invoker> map = invokers.get(module);
        if(map == null){
            map = new HashMap<>();
            invokers.put(module,map);
        }
        map.put(cmd,invoker);
    }

    public static Invoker getInvoker(short module,short cmd){
        Map<Short,Invoker> map = invokers.get(module);
        if(map != null){
            return map.get(cmd);
        }
        return null;
    }
}
