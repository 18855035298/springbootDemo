package com.itcast.demo.producedemo.controller;

import com.itcast.demo.producedemo.pojo.Student;
import com.itcast.demo.producedemo.repository.StuRepository;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/student")
public class StuController {
    @Autowired
    private StuRepository stuRepository;

    @GetMapping("/all")
    public List<Student> getAll() {
        List<Student> all = stuRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable("id") String id) throws InterruptedException {
        int i = new Random().nextInt(2000);
        Thread.sleep(i);
        return stuRepository.getOne(id);
    }

    @PutMapping("/add")
    public Student add(Student student) {
        Student save = stuRepository.save(student);
        return save;
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") String id) {
        stuRepository.deleteById(id);
    }

    @PostMapping("/modify")
    public void modifyOne(Student student) {
        stuRepository.save(student);
    }
}
