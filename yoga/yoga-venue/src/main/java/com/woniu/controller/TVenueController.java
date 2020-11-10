package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.myexception.MyException;
import com.woniu.param.TVenueParam;
import com.woniu.service.TVenueService;
import com.woniu.utils.EmailUtil;
import com.woniu.utils.JsonResult;
import com.woniu.utils.JwtUtil;
import com.woniu.utils.OssUtils;
import com.woniu.yoga.domain.TVenue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

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


    @GetMapping("/login")
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
        redisTemplate.opsForValue().set(eMail,code,3, TimeUnit.MINUTES);
        return new JsonResult("250","验证码发送成功",null,null);
    }

    @RequestMapping("/verify")
    public JsonResult venueRegister (String eMail,String code) throws Exception{
        if(code == null && code.equals("")){
           throw new MyException("250","参数异常");
        }

        if(code.equals(redisTemplate.opsForValue().get(eMail))){
            return new JsonResult("200","success",null,null);
        }else {
            return new JsonResult("250","验证码错误或已过期，请重试",null,null);
        }
    }


    @PostMapping("/register")
    public JsonResult venueRegister (TVenueParam tVenueParam) throws Exception{
        if(tVenueParam==null && tVenueParam.getTVenueMail()==null&& tVenueParam.getTVenueTel()==null){
            throw new MyException("250","参数异常（账户或邮箱不能为空）");
        }
        TVenue tVenue = new TVenue();
        BeanUtils.copyProperties(tVenueParam,tVenue);
        TVenue one = tVenueService.getOne(new QueryWrapper<TVenue>().eq("t_venue_tel", tVenue.getTVenueTel())
                .or().eq("t_venue_mail", tVenue.getTVenueMail()));
        if(one == null || "".equals(one)){
            tVenueService.save(tVenue);
            return new JsonResult("200","注册成功",null,null);
        }else {
            return new JsonResult("250","参数异常（账户或邮箱已存在）",null,null);
        }
    }
    //检查邮箱或者手机号是否存在
    @GetMapping("/checkaccount")
    public JsonResult checkAccount (TVenueParam tVenueParam) throws Exception{
        TVenue tVenue = new TVenue();
        BeanUtils.copyProperties(tVenueParam,tVenue);
        TVenue one = tVenueService.getOne(new QueryWrapper<TVenue>().eq("t_venue_tel", tVenue.getTVenueTel())
                .or().eq("t_venue_mail", tVenue.getTVenueMail()));
        if(one == null || "".equals(one)){

            return new JsonResult("200","success",null,null);
        }else {
            return new JsonResult("250","账户或邮箱已存在",null,null);
        }
    }
    //忘记密码
    @PutMapping("/updatepass")
    public JsonResult updatePassword (TVenueParam tVenueParam) throws Exception{
        if(tVenueParam==null && tVenueParam.getTVenueMail()==null&& tVenueParam.getTVenueTel()==null){
            throw new MyException("250","参数异常（账户或邮箱不能为空）");
        }
        TVenue tVenue = new TVenue();
        BeanUtils.copyProperties(tVenueParam,tVenue);
        TVenue one = tVenueService.getOne(new QueryWrapper<TVenue>().eq("t_venue_tel", tVenue.getTVenueTel())
                .or().eq("t_venue_mail", tVenue.getTVenueMail()));
        one.setTVenuePassword(tVenue.getTVenuePassword());
        tVenueService.updateById(one);
        return new JsonResult("200","修改成功",null,null);
    }

    //修改密码钱检查旧密码是否匹配
    @GetMapping("/eqoldpass")
    public JsonResult checkOldPassword (TVenueParam tVenueParam) throws Exception{
        TVenue tVenue = new TVenue();
        BeanUtils.copyProperties(tVenueParam,tVenue);
        TVenue one = tVenueService.getOne(new QueryWrapper<TVenue>().eq("t_venue_tel", tVenue.getTVenueTel())
                .or().eq("t_venue_mail", tVenue.getTVenueMail()));
        if(tVenueParam.getTVenuePassword().equals(one.getTVenuePassword())){
            return new JsonResult("200","success",null,null);
        }
        return new JsonResult("250","原密码错误",null,null);
    }
    //上传图片
    @PutMapping("/uploadpic")
    public JsonResult completeInfo (MultipartFile file) throws Exception{
        String url = OssUtils.upLoad(file);
        return new JsonResult("200","success",null,url);
    }


}

