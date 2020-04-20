package com.example.netty.lesson11.serviceSeparate.service.serviceImpl;

import com.example.netty.lesson11.serviceSeparate.service.PersonService;
import org.springframework.stereotype.Component;

/**
 * @author yangwj
 * @date 2020/4/20 19:46
 */
@Component
public class PersonServiceImpl implements PersonService {
    @Override
    public void name() {
        System.out.println("name");
    }

    @Override
    public void age() {
        System.out.println("age");
    }
}
