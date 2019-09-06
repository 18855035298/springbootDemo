package com.itcast.demo.consumerdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallBack implements RemoteClient{
    @Override
    public Object queryById(String id) {
        System.out.println("fallback");
        return null;
    }
}
