package com.promise.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{

    @GetMapping("/")
    public String get()
    {
        return "This is server project.";
    }

}
