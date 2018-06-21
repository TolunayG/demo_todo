package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TodoEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String text;
    
    private Boolean status;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dueDate;

    @ManyToOne
    private UserEntity assignedUser;

    @JsonIgnore
    public UserEntity getAssignedUser() { return assignedUser; }
    public void setAssignedUser(UserEntity assignedUser) { this.assignedUser = assignedUser; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof TodoEntity == false) return false;
        TodoEntity other = (TodoEntity) obj;
        return this.id == other.id;
    }

    @PrePersist
    public void prePersist(){
        this.creationDate = new Date();
    }
}