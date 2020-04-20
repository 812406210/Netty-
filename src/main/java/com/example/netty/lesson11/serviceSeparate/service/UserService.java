package com.example.netty.lesson11.serviceSeparate.service;

import com.example.netty.lesson11.serviceSeparate.annotation.SocketCommand;
import com.example.netty.lesson11.serviceSeparate.annotation.SocketModule;

/**
 * @author yangwj
 * @date 2020/4/6 16:12
 */
@SocketModule(module = 1)
public interface UserService {
    /**
     * 登陆
     */
    @SocketCommand(cmd = 1)
    public void login();

    @SocketCommand(cmd = 2)
    public void getInfo();
}
