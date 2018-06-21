package com.example.demo;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

class AddRequestBodyWrapper
{
    private String text;
    private Long dueDate;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Long getDueDate() { return dueDate; }
    public void setDueDate(Long dueDate) { this.dueDate = dueDate; }
}

class EditRequestBodyWrapper
{
    private Long id;
    private String text;
    private Long dueDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
	public void setText(String text) { this.text = text; }

    public Long getDueDate() { return dueDate; }
    public void setDueDate(Long dueDate) { this.dueDate = dueDate; }

}

class DeleteRequestBodyWrapper
{
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}

class CheckRequestBodyWrapper
{
    private Long id;
    private boolean status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}

@RestController
public class TodoController
{
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    @PutMapping("/api/todo/add/{id}")
    public Object add(@PathVariable("id") long id, @RequestBody AddRequestBodyWrapper body, HttpServletResponse response)
    {
        Optional<UserEntity> user = userRepository.findById(id);

        if (user == null || !user.isPresent() || user.get() == null) {
            response.setStatus(420);
            return new ErrorResponseWrapper("User does not exist. (wrong id)");
        }

        if (body.getDueDate() < System.currentTimeMillis()) {
            response.setStatus(420);
            return new ErrorResponseWrapper("Due date cannot be a past date");
        }

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setText(body.getText());
        todoEntity.setDueDate(body.getDueDate());
        todoEntity.setCreationDate(System.currentTimeMillis());
        todoEntity.setStatus(false);
        todoEntity = todoRepository.save(todoEntity);

        UserEntity userEntity = user.get();
        userEntity.addTodo(todoEntity);
        userRepository.save(userEntity);

        return "success";
    }

    @PostMapping("/api/todo/edit")
    public Object edit(long id, @RequestBody EditRequestBodyWrapper body, HttpServletResponse response)
    {
        Optional<UserEntity> user = userRepository.findById(id);
        Optional<TodoEntity> todo = todoRepository.findById(body.getId());

        if (!user.isPresent())
        {
            response.setStatus(420);
            return new ErrorResponseWrapper("User does not exist. (wrong id)");
        }

        if (!todo.isPresent())
        {
            response.setStatus(420);
            return new ErrorResponseWrapper("Todo item does not exist. (wrong id)");
        }
        
        if (body.getDueDate() < System.currentTimeMillis())
        {
            response.setStatus(420);
            return new ErrorResponseWrapper("Due date cannot be a past date");
        }

        TodoEntity editedTodoEntity = todo.get();
        
        if (body.getText() != null)
            editedTodoEntity.setText(body.getText());
        if (body.getDueDate() != null)
            editedTodoEntity.setDueDate(body.getDueDate());

        todoRepository.save(editedTodoEntity);

        return "success";
    }

    @DeleteMapping("/api/todo/delete")
    public Object delete(Long id, @RequestBody DeleteRequestBodyWrapper body, HttpServletResponse response)
    {
        Optional<UserEntity> user = userRepository.findById(id);
        Optional<TodoEntity> todo = todoRepository.findById(id);

        if (!user.isPresent())
        {
            response.setStatus(420);
            return new ErrorResponseWrapper("User does not exist. (wrong id)");
        }

        if (!todo.isPresent())
        {
            response.setStatus(420);
            return new ErrorResponseWrapper("Todo item does not exist. (wrong id)");
        }

        todoRepository.deleteById(body.getId());

        UserEntity userEntity = user.get();
        userEntity.getTodoList().removeIf((x) -> x.getId().equals(body.getId()));
        userRepository.save(userEntity);

        return "success";
    }

    @PostMapping("/api/todo/check")
    public Object check(Long id, @RequestBody CheckRequestBodyWrapper body, HttpServletResponse response)
    {
        if (userRepository.findById(id) == null)
        {
            response.setStatus(420);
            return new ErrorResponseWrapper("User does not exist. (wrong id)");
        }

        if (todoRepository.findById(body.getId()) == null)
        {
            response.setStatus(420);
            return new ErrorResponseWrapper("Todo item does not exist. (wrong id)");
        }

        TodoEntity editedTodoEntity = todoRepository.findById(body.getId()).get();
        editedTodoEntity.setStatus(body.getStatus());
        todoRepository.save(editedTodoEntity);

        return "success";
    }

    @GetMapping("/api/todo/list")
    public Object list(Long id, Long from, Long to, HttpServletResponse response)
    {
        if (userRepository.findById(id) == null)
        {
            response.setStatus(420);
            return new ErrorResponseWrapper("User does not exist. (wrong id)");
        }

        if (to < from)
        {
            response.setStatus(420);
            return new ErrorResponseWrapper("To < from");
        }

        return userRepository.findById(id).get().getTodoList();
    }
}