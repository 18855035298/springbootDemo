package com.itcast.demo.producedemo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO )
    private String id;
    private String name;
    private String teacherId;
}
