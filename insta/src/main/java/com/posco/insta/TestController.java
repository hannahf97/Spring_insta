package com.posco.insta;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/")
    public String test(){
        return "test123";
    }

    @GetMapping("/{id}")
    public String testId(@PathVariable String id){
        return id;
    }

    @PostMapping("/")
    public String testPost(){
        return "test";
    }

    @DeleteMapping("/")
    public String testDelete(){
        return "hello world";
    }

    @DeleteMapping("/{id}")
    public String testDelete(@PathVariable String id){
        return id;
    }

    @PutMapping("/")
    public String testPut(){
        return "hello world";
    }
}
