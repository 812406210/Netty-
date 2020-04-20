package com.example.netty.lesson11.serviceSeparate.service.serviceImpl;

import com.example.netty.lesson11.serviceSeparate.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author yangwj
 * @date 2020/4/6 16:15
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public void login() {
        System.out.println("login");
    }

    @Override
    public void getInfo() {
        System.out.println("getInfo");
    }
}
