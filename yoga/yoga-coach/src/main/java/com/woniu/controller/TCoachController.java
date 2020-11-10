package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.TCoachDto;
import com.woniu.entity.TCoachParam;
import com.woniu.myexception.MyException;
import com.woniu.service.TCoachService;
import com.woniu.utils.EmailUtil;
import com.woniu.utils.JsonResult;
import com.woniu.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zjs
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/tCoach")
public class TCoachController {
    @Resource(name = "coachService")
    private TCoachService tCoachService;
    @Resource
    private RedisTemplate<Object,Object> template;
    //登录接口
    @GetMapping("login")
    public JsonResult login(TCoachParam param)throws Exception {
        TCoachDto dto = new TCoachDto();
        BeanUtils.copyProperties(param,dto);
        dto = tCoachService.login(dto);
        String token = JwtUtil.createToken(dto,3);
        HashMap<String,Object> map = new HashMap<>();
        map.put("coach",dto);
        map.put("token",token);
        return new JsonResult("200","success",null,map);
    }

    //qq登录
    @RequestMapping("qqlogin")
    public JsonResult qqlogin(String code)throws Exception{
        Map<String,Object> map = tCoachService.qqLogin(code);
        return new JsonResult("200","success",null,map);
    }

    //发送注册验证码
    @GetMapping("code")
    public JsonResult getCode(String mail)throws Exception{
        String code = EmailUtil.sendCode(mail);
        //存入redis
        template.opsForValue().set(mail,code,30, TimeUnit.SECONDS);
        return new JsonResult("200","success",null,code);
    }
    //验证码校验
    @GetMapping("check")
    public JsonResult checkCode(String UserCode,String mail)throws Exception{
        String code = (String) template.opsForValue().get(mail);
        if(code==null||"".equals(code)){
            throw new MyException("308","验证码已过期");
        }
        if(!code.equals(UserCode)){
            throw new MyException("309","验证码错误");
        }
        return new JsonResult("200","success",null,null);
    }

    //注册
    @PostMapping("register")
    public JsonResult register(TCoachParam param)throws Exception{
        TCoachDto dto = new TCoachDto();
        BeanUtils.copyProperties(param,dto);
        tCoachService.register(dto);
        return new JsonResult("200","success",null,null);
    }
}

