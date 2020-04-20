package com.example.netty.lesson11.serviceSeparate.service;

import com.example.netty.lesson11.serviceSeparate.annotation.SocketCommand;
import com.example.netty.lesson11.serviceSeparate.annotation.SocketModule;

/**
 * @author yangwj
 * @date 2020/4/20 19:45
 */
@SocketModule(module = 2)
public interface PersonService {
    /**
     * 登陆
     */
    @SocketCommand(cmd = 1)
    public void name();

    @SocketCommand(cmd = 2)
    public void age();
}
