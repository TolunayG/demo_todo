package com.example.demo;

import javax.persistence.*;

@Entity
public class TodoEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String text;
    
    private Boolean status;

    private Long creationDate;

    private Long dueDate;

    //@ManyToOne
    //@Column(name = "assignedUser")
    //@JoinColumn(name = "id")
    //private UserEntity assignedUser;
    @ManyToOne
    private UserEntity assignedUser;

    public UserEntity getAssignedUser() { return assignedUser; }
    public void setAssignedUser(UserEntity assignedUser) { this.assignedUser = assignedUser; }

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