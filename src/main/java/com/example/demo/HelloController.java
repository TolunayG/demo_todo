package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HelloController
{
    @Autowired
    private UserRepository repository;
    
    @GetMapping("/currency-exchange/from/{id}/to/{email}")
    public String index(@PathVariable String id, @PathVariable String email) {
        repository.save(new UserEntity(0L, "asdasd"));
        return "" + repository.count();
    }
}