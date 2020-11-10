package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.param.TVenueParam;
import com.woniu.service.TVenueService;
import com.woniu.utils.EmailUtil;
import com.woniu.utils.JsonResult;
import com.woniu.utils.JwtUtil;
import com.woniu.yoga.domain.TVenue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liushaoqi
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/tVenue")
public class TVenueController {
    @Autowired
    private TVenueService tVenueService;


    @RequestMapping("/login")
    public JsonResult venueLogin(TVenueParam tVenueParam) throws Exception{
        //转换类型
        TVenue tVenue = new TVenue();
        BeanUtils.copyProperties(tVenueParam,tVenue);
        if(tVenueParam != null){
            //判断是否有电话或邮箱与传入对象相同的数据库实体
            TVenue one = tVenueService.getOne(new QueryWrapper<TVenue>().eq("t_venue_tel", tVenue.getTVenueTel())
                    .or().eq("t_venue_mail", tVenue.getTVenueMail()));
            //如果有切密码相同 判断用户是否选择3天免登录服务
            if(one != null && one.getTVenuePassword().equals(tVenue.getTVenuePassword()) ){
                if(tVenue.getTVenueStatus() == 3){
                    String token = JwtUtil.createToken(one, 3);
                    one.setTVenueSpare(token);
                    return new JsonResult("200","success",null,one);
                }else{
                    return new JsonResult("200","success",null,one);
                }
            }else {
                return new JsonResult("250","账户名或密码错误",null,null);
            }
        }else{
            return new JsonResult("250","未找到该账户，请先注册",null,null);
        }
    }
    @Autowired
    RedisTemplate redisTemplate;
    //发送验证码
    @RequestMapping("/sendcode")
    public JsonResult venueRegister (String eMail) throws Exception{
        String code = EmailUtil.sendCode(eMail);
        redisTemplate.opsForValue().set(eMail,code,3, TimeUnit.SECONDS);
        return new JsonResult("250","验证码发送成功",null,null);
    }

    @RequestMapping("/register")
    public void venueRegister (TVenueParam tVenueParam) throws Exception{
        redisTemplate.opsForValue().get(tVenueParam.getTVenueMail());

    }
}

