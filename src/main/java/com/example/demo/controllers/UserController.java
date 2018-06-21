package com.example.demo.controllers;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.ErrorResponseWrapper;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

class LoginRequestBodyWrapper
{
    private String email;
    private String password;

    void setEmail(String email) {
        this.email = email;
    }

    String getEmail() {
        return email;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getPassword() {
        return password;
    }
}

class LoginResponseBodyWrapper
{
    private long id;

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }
}

class RegisterRequestWrapper
{
    private String email;
    private String password;

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }
}

@RestController
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/user/login")
    public Object login(@RequestBody LoginRequestBodyWrapper body, HttpServletResponse response)
    {
        if (body.getEmail() == null || body.getEmail().length() == 0) {
            response.setStatus(420);
            return new ErrorResponseWrapper("Email cannot be empty.");
        }
        
        UserEntity user = userRepository.findByEmail(body.getEmail());

        if (body.getPassword() == null || body.getPassword().length() == 0) {
            response.setStatus(420);
            return new ErrorResponseWrapper("Password cannot be empty.");
        }
            
        if (user == null) {
            response.setStatus(420);
            return new ErrorResponseWrapper("No such email");
        }
                    
        if (!user.getPassword().equals(body.getPassword())) {
            response.setStatus(420);
            return new ErrorResponseWrapper("Password is incorrect.");
        }
            
        LoginResponseBodyWrapper responseBodyWrapper = new LoginResponseBodyWrapper();
        responseBodyWrapper.setId(user.getId());
        return responseBodyWrapper;
    }

    @PostMapping("/api/user/register")
    public Object register(@RequestBody RegisterRequestWrapper body, HttpServletResponse response)
    {
        String email = body.getEmail();
        String password = body.getPassword();

        if (email == null || email.length() == 0) {
            response.setStatus(420);
            return new ErrorResponseWrapper("email is null or empty");
        }
        if (password == null || password.length() == 0) {
            response.setStatus(420);
            return new ErrorResponseWrapper("password is null or empty");
        }

        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            response.setStatus(420);
            return new ErrorResponseWrapper("email already used");
        }
        
        user = new UserEntity();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return "success";
    }
}