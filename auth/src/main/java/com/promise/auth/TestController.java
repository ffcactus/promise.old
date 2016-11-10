package com.promise.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
    @RequestMapping("/test")
    public String getScope()
    {
        System.out.println("Hello World.");
        return "Hello World!";
    }

    @GetMapping("/")
    public String get()
    {
        System.out.println("-- Hello World! --");
        return "-- Hello World! --";
    }

}
