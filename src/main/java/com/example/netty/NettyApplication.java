package com.example.netty;

import com.example.netty.lesson11.serviceSeparate.Invoker;
import com.example.netty.lesson11.serviceSeparate.InvokerHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
        Invoker invoker = InvokerHolder.getInvoker((short)2, (short)1);
        invoker.invoke(null);
        Invoker invoker1 = InvokerHolder.getInvoker((short)1, (short)1);
        invoker1.invoke(null);
    }

}
