package com.example.netty.lesson11.serviceSeparate;

import com.example.netty.lesson11.serviceSeparate.annotation.SocketCommand;
import com.example.netty.lesson11.serviceSeparate.annotation.SocketModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 扫描器
 * @author yangwj
 * @date 2020/4/6 16:16
 */
@Component
public class Scanner implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<? extends Object> clazz =  bean.getClass();
        Class<?>[] interfaces =  clazz.getInterfaces();
        if(interfaces != null && interfaces.length >0 ){
            //扫描所有的接口
            for(Class<?> interFace : interfaces){
                SocketModule socketModule = interFace.getAnnotation(SocketModule.class);
                if(socketModule == null){
                    continue;
                }

                Method[] methods = interFace.getMethods();
                if(methods!=null && methods.length>0){
                    for (Method method:methods) {
                        SocketCommand socketCommand = method.getAnnotation(SocketCommand.class);
                        if(socketCommand == null){
                            continue;
                        }
                        short module = socketModule.module();
                        short cmd = socketCommand.cmd();
                        Invoker invoker = Invoker.valueOf(bean,method);
                        if(InvokerHolder.getInvoker(module,cmd) == null){
                            InvokerHolder.addInvoker(module,cmd,invoker);
                        }else {
                            System.out.println("重复注册执行器module:"+ module +"---cmd:"+cmd);
                        }
                    }
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
