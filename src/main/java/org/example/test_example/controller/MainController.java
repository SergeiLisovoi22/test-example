package org.example.test_example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/main")
public class MainController {

    @GetMapping
    public String hello() {
        return "Hello World!";
    }
}
