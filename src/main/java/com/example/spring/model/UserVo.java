package com.example.spring.model;

import javax.persistence.*;

@Entity// JPA가 관리하는 객체와 오브젝트를 매핑해준다 ORM
public class UserVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
