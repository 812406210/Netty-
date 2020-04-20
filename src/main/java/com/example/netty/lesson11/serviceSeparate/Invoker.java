package com.example.netty.lesson11.serviceSeparate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 执行器
 * @author yangwj
 * @date 2020/4/6 16:36
 */
public class Invoker {
    /**
     * 目标对象
     */
    private  Object target;

    /**
     * 方法
     */
    private Method method;


    public static Invoker valueOf(Object target,Method method){
        Invoker invoker = new Invoker();
        invoker.setTarget(target);
        invoker.setMethod(method);
        return invoker;
    }

    public Object invoke(Object[] args){
        try {
            method.invoke(target,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
