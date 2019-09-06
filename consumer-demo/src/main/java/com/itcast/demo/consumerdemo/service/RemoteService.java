package com.itcast.demo.consumerdemo.service;

import com.itcast.demo.consumerdemo.dao.RemoteClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteService  {
    @Autowired
    private RemoteClient remoteClient;
    public  Object getById(String id ){
        long start = System.currentTimeMillis();
        Object o = remoteClient.queryById(id);
        long end = System.currentTimeMillis();
        System.out.println("time:"+(end-start));
        return o;
    }
//    @HystrixCommand(fallbackMethod = "fallbackgetAll")
//    public Object getAll(String id) {
//        long start = System.currentTimeMillis();
//        Object forObject = restTemplate.getForObject("http://produce/student/" + id, Object.class);
//        long end = System.currentTimeMillis();
//        System.out.println("time:"+(end-start));
//        return forObject;
//    }
//
//    public Object fallbackgetAll(String id) {
//        System.out.println("----------------------------------------");
//        return null;
//    }
}
