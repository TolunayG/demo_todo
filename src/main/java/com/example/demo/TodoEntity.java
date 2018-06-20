package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TodoEntity
{
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    private boolean status;

    private Long creationDate;

    private Long dueDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public Long getCreationDate() { return creationDate; }
    public void setCreationDate(Long creationDate) { this.creationDate = creationDate; }

    public Long getDueDate() { return dueDate; }
    public void setDueDate(Long dueDate) { this.dueDate = dueDate; }
}