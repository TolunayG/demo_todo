package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

class LoginRequestBodyWrapper {

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

class LoginResponseBodyWrapper {

    private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

@RestController
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public Object login(@RequestBody LoginRequestBodyWrapper body){
        if (body.getEmail() == null)
            return new ErrorResponseWrapper("Email cannot be empty.");
        
        UserEntity user = userRepository.findByEmail(body.getEmail());

        if (body.getPassword() == null)
            return new ErrorResponseWrapper("Password cannot be empty.");

        if (user == null)
            return new ErrorResponseWrapper("No such email");
        
        if (user.getPassword().equals(body.getPassword()))
            return new ErrorResponseWrapper("Password is incorrect.");

        LoginResponseBodyWrapper responseBodyWrapper = new LoginResponseBodyWrapper();
        responseBodyWrapper.setId(user.getId());
        return responseBodyWrapper;
    }
}