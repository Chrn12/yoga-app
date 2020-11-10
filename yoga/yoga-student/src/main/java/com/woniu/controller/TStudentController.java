package com.woniu.controller;


import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;
    @GetMapping("login")
    public JsonResult login(TStudentParam tStudentParam) throws Exception{
        TStudentDto studentDto = null;
        if(tStudentParam.getTStudentMail() != null || tStudentParam.getTStudentTel() != null){
            if(tStudentParam.getTStudentPassword() == null){
                throw new MyException("404","请输入密码");
            }else {
                studentDto = tStudentService.getLogin(tStudentParam);
                String token = JwtUtil.createToken(studentDto, 3);
                studentDto.setToken(token);
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
    public JsonResult qqRegister(TStudentParam tStudentParam) throws Exception{
        if(tStudentParam.getTStudentMail() == null && tStudentParam.getTStudentTel() == null){
            throw new MyException("500","没有输入电话号码或邮箱");
        }
        return new JsonResult();
    }
    @PostMapping("register")
    public JsonResult register(String code , TStudentParam tStudentParam) throws Exception{
        if(tStudentParam != null && tStudentParam.getTStudentMail() != null && tStudentParam.getTStudentTel() != null && tStudentParam.getTStudentPassword() != null){
            Object code1 = redisTemplate.opsForHash().get("code", tStudentParam.getTStudentMail());
            if(code1 != null && code1.toString().equals(code)){
                TStudentDto studentDto = new TStudentDto();
                BeanUtils.copyProperties(tStudentParam,studentDto);
                tStudentService.savaStudent(studentDto);
                redisTemplate.opsForHash().delete("code",tStudentParam.getTStudentMail());
            }else {
                throw new MyException("","验证码错误或已过期");
            }
        }else {
            throw new MyException("500","参数不足");
        }
        return new JsonResult("200","success",null,null);
    }
    @GetMapping("/code")
    public JsonResult code(String email) throws Exception{
        String code = EmailUtil.sendCode(email);
        redisTemplate.opsForHash().put("code",email,code);
        redisTemplate.expire("code",60, TimeUnit.SECONDS);
        return new JsonResult("200","success",null,code);
    }
    //找回密码
    @PutMapping("foundPassword")
    public JsonResult FoundPassword(String code , TStudentParam tStudentParam) throws Exception{
        if(tStudentParam != null && tStudentParam.getTStudentMail() != null && tStudentParam.getTStudentPassword() != null){
            Object code1 = redisTemplate.opsForHash().get("code", tStudentParam.getTStudentMail());
            if(code1 != null && code1.toString().equals(code)){
                TStudentDto studentDto = new TStudentDto();
                BeanUtils.copyProperties(tStudentParam,studentDto);
                tStudentService.updatePassword(studentDto);
            }else {
                throw new MyException("","验证码错误或已过期");
            }
        }else {
            throw new MyException("500","参数不足");
        }
        return new JsonResult("200","success",null,null);
    }
    //个人中心
    @GetMapping("userCenter")
    public JsonResult userCenter(String token) throws Exception{
        if("".equals(token) && token == null){
            throw new MyException("500","请先登录");
        }
        Claims claims = JwtUtil.parseToken(token);
        String tStudentId = claims.get("tStudentId").toString();
        TStudent tStudent = tStudentService.getById(Integer.parseInt(tStudentId));
        return new JsonResult("200","success",null,tStudent);
    }
    //更改头像
    @PutMapping("alterPhoto")
    public JsonResult updatePhoto(String token, MultipartFile file) throws Exception{
        if("".equals(token) && token == null){
            throw new MyException("500","请先登录");
        }
        Claims claims = JwtUtil.parseToken(token);
        String tStudentId = claims.get("tStudentId").toString();
        String tStudentImg = OssUtils.upLoad(file);
        tStudentService.updatePhoto(tStudentId,tStudentImg);
        return new JsonResult("200","success",null,tStudentImg);
    }
    //完善个人信息
    @PutMapping("studentInfo")
    public JsonResult StudentInfo(TStudentParam tStudentParam) throws Exception{
        if("".equals(tStudentParam.getToken()) && tStudentParam.getToken() == null){
            throw new MyException("500","请先登录");
        }
        String token = tStudentParam.getToken();
        Claims claims = JwtUtil.parseToken(token);
        Object tStudentId = claims.get("tStudentId");
        if(tStudentId == null){
            throw new MyException("500","请登录");
        }
        TStudent student = new TStudent();
        BeanUtils.copyProperties(tStudentParam,student);
        student.setTStudentId(Integer.parseInt(tStudentId.toString()));
        tStudentService.updateById(student);
        return new JsonResult("200","success",null,null);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/pay",produces = "text/html; charset=UTF-8",method= RequestMethod.POST)
    public void pay(int totalAmount, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String token = request.getHeader("token");
        Claims claims = JwtUtil.parseToken(token);
        Object tStudentId = claims.get("tStudentId");
        TStudent tStudent = tStudentService.getById(Integer.parseInt(tStudentId.toString()));
        UpdateWrapper<TStudent> wrapper = new UpdateWrapper<>();
        wrapper.eq("t_student_id",Integer.parseInt(tStudentId.toString()));
        wrapper.set("t_student_balance",tStudent.getTStudentBalance()+totalAmount);
        tStudentService.update(wrapper);
        //Config options = AlipayConfig.getOptions();
        Factory.setOptions(AlipayConfig.getOptions());
        String body = null;
        try {
            String totalAmounts = totalAmount+"";
            AlipayTradePagePayResponse pay = Factory.Payment.Page().pay("...", UUID.randomUUID().toString(), totalAmounts, AlipayConfig.return_url);
            //System.out.print(pay);
            body = pay.getBody();
            PrintWriter writer = response.getWriter();
            System.out.println("body2:"+pay.body);
            String head = "<html><head><meta http-equiv='Content-Type' content='text/html;charset=UTF-8'></head>";
            String bottom = "<body></body></html>";
            response.setContentType("text/html;charset=UTF-8");
            writer.write(head + body + bottom);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("getMoney")
    public JsonResult getMoney(String token)throws Exception{
        if(token == null || "".equals(token)){
            throw new MyException("500","令牌不存在");
        }
        Claims claims = JwtUtil.parseToken(token);
        Object tStudentId = claims.get("tStudentId");
        TStudent byId = tStudentService.getById(Integer.parseInt(tStudentId.toString()));
        return new JsonResult("200","success",null,null);
    }
}

