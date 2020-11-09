package com.woniu.controller;


import com.woniu.entity.TStudentDto;
import com.woniu.entity.TStudentParam;
import com.woniu.myexception.MyException;
import com.woniu.service.TStudentService;
import com.woniu.utils.JsonResult;
import com.woniu.yoga.domain.TStudent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tx
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/tStudent")
public class TStudentController {
    @Resource
    private TStudentService tStudentService;
    @GetMapping("login")
    public JsonResult login(TStudentParam tStudentParam) throws Exception{
        if(tStudentParam.getTStudentMail() != null || tStudentParam.getTStudentTel() != null){
            if(tStudentParam.getTStudentPassword() == null){
                throw new MyException("404","请输入密码");
            }else {
                TStudentDto studentDto = tStudentService.getLogin(tStudentParam);

            }
        }else {
            throw new MyException("404","没有指定账号");
        }
        return new JsonResult();
    }
}

