package com.itcast.demo.consumerdemo.dao;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "produce",path = "student",fallback = UserFeignClientFallBack.class)
public interface RemoteClient {
    @GetMapping("/{id}")
    Object queryById(@PathVariable("id") String id);
}
