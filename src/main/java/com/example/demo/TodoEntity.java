package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TodoEntity
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    
    @Column(name = "text")
    private String text;
    
    @Column(name = "status")
    private Boolean status;

    @Column(name = "creationDate")
    private Long creationDate;

    @Column(name = "dueDate")
    private Long dueDate;

    @ManyToOne
    @Column(name = "user")
    @JoinColumn(name = "id")
    private UserEntity user;

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public Long getCreationDate() { return creationDate; }
    public void setCreationDate(Long creationDate) { this.creationDate = creationDate; }

    public Long getDueDate() { return dueDate; }
    public void setDueDate(Long dueDate) { this.dueDate = dueDate; }
}