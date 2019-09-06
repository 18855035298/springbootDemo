package com.itcast.demo.producedemo.repository;

import com.itcast.demo.producedemo.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StuRepository  extends JpaRepository<Student,String> {
}
