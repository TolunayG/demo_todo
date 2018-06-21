package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

    @OneToMany(mappedBy = "assignedUser")
    private List<TodoEntity> todoList = new ArrayList();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<TodoEntity> getTodoList() { return todoList; }
    public void setTodoList(List<TodoEntity> todoList) { this.todoList = todoList; }

    public void addTodo(TodoEntity todo) {
        this.todoList.add(todo);
    }

    public void removeTodo(TodoEntity todo) {
        this.todoList.remove(todo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof UserEntity == false) return false;
        UserEntity other = (UserEntity) obj;
        return this.id == other.id;
    }
}