package com.woniu.testcontroller;


import com.woniu.yoga.domain.TCoach;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venue")
public class Controller {
    @RequestMapping("/test")
    public void Test1(){
        System.out.println("123123213");
        TCoach t = new TCoach();
    }
}
