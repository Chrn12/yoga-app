package com.woniu.controller;


import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.TCoachDto;
import com.woniu.entity.TCoachParam;
import com.woniu.myexception.MyException;
import com.woniu.service.TCoachService;
import com.woniu.utils.EmailUtil;
import com.woniu.utils.JsonResult;
import com.woniu.utils.JwtUtil;
import com.woniu.utils.OssUtils;
import com.woniu.yoga.domain.TCoach;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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

    //发送邮箱验证码
    @GetMapping("code")
    public JsonResult getCode(String mail)throws Exception{
        String code = EmailUtil.sendCode(mail);
        //存入redis
        template.opsForValue().set(mail,code,30, TimeUnit.SECONDS);
        return new JsonResult("200","success",null,code);
    }
    //邮箱验证码校验
    @GetMapping("check")
    public JsonResult checkCode(String UserCode,String mail)throws Exception{
        String code = (String) template.opsForValue().get(mail);
        if(code==null||"".equals(code)){
            throw new MyException("308","验证码已过期");
        }
        if(!code.equals(UserCode)){
            throw new MyException("309","验证码错误");
        }
        template.delete(mail);
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

    //密码找回
    @GetMapping("recovery")
    public JsonResult recovery(TCoachParam param)throws Exception{
        TCoach coach = new TCoach();
        BeanUtils.copyProperties(param,coach);
        QueryWrapper<TCoach> wrapper = new QueryWrapper<>();
        wrapper.eq("t_coach_mail",coach.getTCoachMail());
        TCoach tCoach = tCoachService.getOne(wrapper);
        tCoach.setTCoachPassword(param.getTCoachPassword());
        tCoachService.updateById(tCoach);
        return new JsonResult("200","success",null,null);
    }

    //个人中心
    @GetMapping("userCenter")
    public JsonResult userCenter(String token)throws Exception{
        if("".equals(token)||token==null){
            throw new MyException("500","请先登录");
        }
        Claims claims = JwtUtil.parseToken(token);
        int id = (int) claims.get("tCoachId");
        TCoach coach = tCoachService.getById(id);
        return new JsonResult("200","success",null,coach);
    }

    //上传头像
    @PostMapping("uploadImg")
    public JsonResult upload(MultipartFile file)throws Exception{
        String url = OssUtils.upLoad(file);
        return new JsonResult("200","success",null,url);
    }
    //修改头像
    @PutMapping("updateImg")
    public JsonResult updateImg(String token,String url)throws Exception{
        Claims claims = JwtUtil.parseToken(token);
        int id = (int) claims.get("tCoachId");
        TCoach coach = tCoachService.getById(id);
        coach.setTCoachImg(url);
        return new JsonResult("200","success",null,null);
    }
    //完善信息
    @PostMapping("coachInfo")
    public JsonResult coachInfo(TCoachParam param)throws Exception{
        String token = param.getToken();
        Claims claims = JwtUtil.parseToken(param.getToken());
        int id = (int) claims.get("tCoachId");
        TCoach coach = new TCoach();
        BeanUtils.copyProperties(param,coach);
        coach.setTCoachId(id);
        tCoachService.updateById(coach);
        return new JsonResult("200","success",null,null);
    }

    //查看余额
    @GetMapping("queryTake")
    public JsonResult queryTake(String token)throws Exception{
        Claims claims = JwtUtil.parseToken(token);
        int id = (int) claims.get("tCoachId");
        TCoach coach = tCoachService.getById(id);
        return new JsonResult("200","success",null,coach);
    }


}

