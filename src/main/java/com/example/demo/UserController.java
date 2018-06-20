package com.example.demo;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

class LoginRequestBodyWrapper
{
    private String email;
    private String password;

	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

class LoginResponseBodyWrapper
{
    private long id;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}

class RegisterRequestWrapper
{
    private String email;
    private String password;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
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
        if (body.getEmail() == null) {
            response.setStatus(420);
            return new ErrorResponseWrapper("Email cannot be empty.");
        }
        
        UserEntity user = userRepository.findByEmail(body.getEmail());

        if (body.getPassword() == null) {
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
        user.setId(0L);
        user.setEmail(body.getEmail());
        user.setPassword(body.getPassword());
        userRepository.save(user);

        return "success";
    }
}