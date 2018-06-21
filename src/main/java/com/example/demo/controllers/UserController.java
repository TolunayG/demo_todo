package com.example.demo;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

class LoginRequestBodyWrapper
{
    String email;
    String password;
}

class LoginResponseBodyWrapper
{
    long id;
}

class RegisterRequestWrapper
{
    String email;
    String password;
}

@RestController
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/user/login")
    public Object login(@RequestBody LoginRequestBodyWrapper body, HttpServletResponse response)
    {
        if (body.email == null || body.email.length() == 0) {
            response.setStatus(420);
            return new ErrorResponseWrapper("Email cannot be empty.");
        }
        
        UserEntity user = userRepository.findByEmail(body.email);

        if (body.password == null || body.password.length() == 0) {
            response.setStatus(420);
            return new ErrorResponseWrapper("Password cannot be empty.");
        }
            
        if (user == null) {
            response.setStatus(420);
            return new ErrorResponseWrapper("No such email");
        }
                    
        if (!user.getPassword().equals(body.password)) {
            response.setStatus(420);
            return new ErrorResponseWrapper("Password is incorrect.");
        }
            
        LoginResponseBodyWrapper responseBodyWrapper = new LoginResponseBodyWrapper();
        responseBodyWrapper.id = user.getId();
        return responseBodyWrapper;
    }

    @PostMapping("/api/user/register")
    public Object register(@RequestBody RegisterRequestWrapper body, HttpServletResponse response)
    {
        String email = body.email;
        String password = body.password;

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
        user.setId(0L);
        user.setEmail(body.email);
        user.setPassword(body.password);
        userRepository.save(user);

        return "success";
    }
}