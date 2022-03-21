package com.fedorchenko.testTask.dao;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}