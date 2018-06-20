package com.example.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserEntity
{
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    @OneToMany(targetEntity = TodoEntity.class)
    private List<TodoEntity> todoList;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<TodoEntity> getTodoList() { return todoList; }
    public void setTodoList(List<TodoEntity> todoList) { this.todoList = todoList; }
}