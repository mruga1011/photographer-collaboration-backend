package com.photographer.app.test;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class test {

    @GetMapping("/hello")
    public String hello() {
        return "Spring Boot is working 🚀";
    }
}