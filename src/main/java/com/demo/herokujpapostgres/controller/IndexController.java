package com.demo.herokujpapostgres.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class IndexController {

    @GetMapping
    public String welcome() {
        return "Welcome to the Demo API";
    }
}