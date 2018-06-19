package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity
{
    @Id
    private Long id;

    @Column(name="email")
    private String email;

    public UserEntity(Long id, String email)
    {
        this.id = id;
        this.email = email;
    }
}