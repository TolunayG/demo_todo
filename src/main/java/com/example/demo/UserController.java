package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private UserRepository userRepository;

    @PostMapping("/api/user/register")
    public Object register(@RequestBody RegisterRequestWrapper body, HttpServletResponse response)
    {
        String email = body.getEmail();
        String password = body.getPassword();

        if (email == null) return new ErrorResponseWrapper("email is null");
        if (password == null) return new ErrorResponseWrapper("password is null");

        UserEntity user = userRepository.findByEmail(email);
        if (user != null)
            return new ErrorResponseWrapper("email already used");
        
        user = new UserEntity();
        user.setId(0L);
        user.setEmail(body.getEmail());
        user.setPassword(body.getPassword());

        userRepository.save(user);

        return "{}";
    }
}