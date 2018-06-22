package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class FrontendController
{
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

}
