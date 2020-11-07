package com.woniu.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class Test {
    @RequestMapping("123")
    public String test(){
        return "OK";
    }
}
