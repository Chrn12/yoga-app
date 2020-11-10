package com.woniu.controller;


import com.woniu.entity.TStudentDto;
import com.woniu.entity.TStudentParam;
import com.woniu.myexception.MyException;
import com.woniu.service.TStudentService;
import com.woniu.utils.EmailUtil;
import com.woniu.utils.JsonResult;
import com.woniu.utils.JwtUtil;

import com.woniu.utils.OssUtils;
import com.woniu.yoga.domain.TStudent;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;

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
        TStudentDto studentDto = null;
        if(tStudentParam.getTStudentMail() != null || tStudentParam.getTStudentTel() != null){
            if(tStudentParam.getTStudentPassword() == null){
                throw new MyException("404","请输入密码");
            }else {
                studentDto = tStudentService.getLogin(tStudentParam);
                if(tStudentParam.getRememberMe() != null && tStudentParam.getRememberMe()){
                    String token = JwtUtil.createToken(studentDto, 3);
                    studentDto.setToken(token);
                }
            }
        }else {
            throw new MyException("404","没有指定账号");
        }
        return new JsonResult("200","success",null,studentDto);
    }
    @GetMapping("qqLogin")
    public JsonResult qqLogin(String code) throws Exception{
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=101780702&client_secret=be3cf79b89eafe3f0b8f90462aed8065&code="+code+"&redirect_uri=http://www.pawntest.com/qqlogin";
        RestTemplate restTemplate = new RestTemplate();
        String value = restTemplate.getForObject(url, String.class);
        String token = value.substring(value.indexOf("=")+1,value.indexOf("&"));
        url = "https://graph.qq.com/oauth2.0/me?access_token="+token;
        value = restTemplate.getForObject(url, String.class);
        String openId = value.substring(value.lastIndexOf(":")+2,value.lastIndexOf("\""));
        url = "https://graph.qq.com/user/get_user_info?access_token="+token+"&oauth_consumer_key=101780702&openid="+openId;
        String forObject = restTemplate.getForObject(url, String.class);
        TStudentDto studentDto = tStudentService.qqLogin(openId);
        return new JsonResult("200","success",null,studentDto);
    }
    @PostMapping("qqRegister")
    public JsonResult qqRegister() throws Exception{
        return new JsonResult();
    }
    @PostMapping("register")
    public JsonResult register() throws Exception{
        return new JsonResult();
    }
    @GetMapping("/code")
    public JsonResult code(String email) throws Exception{
        EmailUtil.sendCode(email);
        return new JsonResult();
    }

    //个人中心
    @GetMapping("userCenter")
    public JsonResult userCenter(String token) throws Exception{
        Claims claims = JwtUtil.parseToken(token);
        String tStudentId = claims.get("tStudentId").toString();
        TStudent tStudent = tStudentService.getById(Integer.parseInt(tStudentId));
        return new JsonResult("","",null,tStudent);
    }
    //更改头像
    @PutMapping("alterPhoto")
    public JsonResult updatePhoto(String token, MultipartFile file) throws Exception{
        Claims claims = JwtUtil.parseToken(token);
        String tStudentId = claims.get("tStudentId").toString();
        String tStudentImg = OssUtils.upLoad(file);

        return new JsonResult("","",null,tStudentImg);
    }
}

