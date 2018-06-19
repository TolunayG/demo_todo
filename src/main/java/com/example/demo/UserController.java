package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

class LoginRequestWrapper
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

class LoginResponseWrapper
{
    private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
    }
    public LoginResponseWrapper(Long id) {
        this.id = id;
    }
}

@RestController
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "Hello";
    }

    @PostMapping("/login")
    public @ResponseBody LoginResponseWrapper login(@RequestBody LoginRequestWrapper object)
    {
        return new LoginResponseWrapper(5L);
    }
}