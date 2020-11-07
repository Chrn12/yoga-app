package com.woniu.testcontroller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venue")
public class Controller {
    @RequestMapping("/test")
    public void Test1(){
        System.out.println("123123213");
    }
}
