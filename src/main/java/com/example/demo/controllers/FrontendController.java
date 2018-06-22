package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontendController
{
    @GetMapping("/")
    public String index()
    {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<button onclick=\"loginClicked()\">Login</button>\n" +
                "<button onclick=\"registerClicked()\">Register</button>\n" +
                "\n" +
                "<script>\n" +
                "function loginClicked() {\n" +
                "    window.location = \"/login\";\n" +
                "}\n" +
                "function registerClicked() {\n" +
                "    window.location = \"/register\";\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }

    @GetMapping("/login")
    public String login()
    {
        return "asd";
    }

    @GetMapping("/register")
    public String register()
    {
        return "asd";
    }
}
