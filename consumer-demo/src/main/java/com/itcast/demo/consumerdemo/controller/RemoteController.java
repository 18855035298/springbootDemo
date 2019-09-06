package com.itcast.demo.consumerdemo.controller;

import com.itcast.demo.consumerdemo.dao.RemoteDao;
import com.itcast.demo.consumerdemo.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/remote")
public class RemoteController {
    @Autowired
    private RemoteDao remoteDao;
    @Autowired
    private RemoteService remoteService;
//    @GetMapping("/getAll")
//    public Object getAll() {
//        return restTemplate.getForObject("http://produce/student/all", Object.class);
//    }
    @GetMapping("/getAllByRibbon/{id}")
    public Object getAll1(@PathVariable("id") String id) {
//        return remoteService.getAll(id);
        return null;
    }
    @GetMapping("/getAllByfeign/{id}")
    public Object getAll2(@PathVariable("id") String id) {
        return remoteService.getById(id);
    }

}
