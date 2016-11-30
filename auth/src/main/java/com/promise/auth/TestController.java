package com.promise.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{

    @GetMapping("/")
    public String get()
    {
        System.out.println("-- Hello World! --");
        return "-- Hello World! --";
    }

}
