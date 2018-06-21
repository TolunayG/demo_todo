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
    private Long id;
    
    private String text;
    
    private Boolean status;

    private Long creationDate;

    private Long dueDate;

    //@ManyToOne
    //@Column(name = "user")
    //@JoinColumn(name = "id")
    //private UserEntity user;
    @ManyToOne
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof TodoEntity == false) return false;
        TodoEntity other = (TodoEntity) obj;
        return this.id == other.id;
    }
}