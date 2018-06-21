package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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