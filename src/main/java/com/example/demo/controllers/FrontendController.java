package com.example.demo.controllers;

import com.example.demo.RowBuilder;
import com.example.demo.entities.TodoEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class FrontendController
{
    @Autowired
    UserRepository userRepository;

    private static String getHtml(String file)
    {
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            return lines.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return e.toString();
        }
    }

    @GetMapping("/")
    public String index()
    {
        return getHtml("src\\main\\resources\\static\\home.html");
    }

    @GetMapping("/login")
    public String login()
    {
        return getHtml("src\\main\\resources\\static\\login.html");
    }

    @GetMapping("/register")
    public String register()
    {
        return getHtml("src\\main\\resources\\static\\register.html");
    }

    @GetMapping("/list")
    public String list(long id)
    {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            StringBuilder sb = new StringBuilder();
            for (TodoEntity todo : user.get().getTodoList()) {
                sb.append(RowBuilder.generateRow(todo));
                sb.append("\n");
            }

            String htmlString = getHtml("src\\main\\resources\\list.html");

            htmlString = htmlString.replace("{{{REPLACE_ME}}}", sb.toString());
            return htmlString;
        }

        else
            return "User not found";
    }
}
