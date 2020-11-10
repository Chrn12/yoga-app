package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.param.TVenueParam;
import com.woniu.service.TVenueService;
import com.woniu.utils.JsonResult;
import com.woniu.yoga.domain.TVenue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
        TVenue tVenue = new TVenue();
        BeanUtils.copyProperties(tVenueParam,tVenue);
        if(tVenueParam != null){
            TVenue one = tVenueService.getOne(new QueryWrapper<TVenue>().eq("t_venue_tel", tVenue.getTVenueTel()).or()
                    .eq("t_venue_mail", tVenue.getTVenueMail()));
            if(one != null && one.equals(tVenue.getTVenuePassword())){
                return new JsonResult("200","success",null,null);
            }else {
                return new JsonResult("250","账户名或密码错误",null,null);
            }
        }else{
            return new JsonResult("250","未找到该账户，请先注册",null,null);
        }
    }



}

