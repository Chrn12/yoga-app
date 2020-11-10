package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.TCoachDto;
import com.woniu.mapper.TCoachMapper;
import com.woniu.myexception.MyException;
import com.woniu.service.TCoachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.utils.JsonResult;
import com.woniu.utils.JwtUtil;
import com.woniu.yoga.domain.TCoach;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zjs
 * @since 2020-11-09
 */
@Service("coachService")
public class TCoachServiceImpl extends ServiceImpl<TCoachMapper,TCoach> implements TCoachService {
    @Resource
    private TCoachMapper mapper;
    @Override
    public TCoachDto login(TCoachDto dto) throws Exception {
        QueryWrapper<TCoach> wrapper = new QueryWrapper<>();
        //邮箱登录
        if(dto.getTCoachMail()!=null&&dto.getTCoachTel()==null){
            wrapper.eq("t_coach_mail",dto.getTCoachMail());
            wrapper.eq("t_coach_password",dto.getTCoachPassword());
        }else if(dto.getTCoachTel()!=null&&dto.getTCoachMail()==null){
            //电话登录
            wrapper.eq("t_coach_tel",dto.getTCoachTel());
            wrapper.eq("t_coach_password",dto.getTCoachPassword());
        }else{
            throw new MyException("302","参数异常");
        }
        TCoach coach = this.getOne(wrapper);
        if(coach==null){
            throw new MyException("301","账号密码不匹配");
        }
        TCoachDto tCoachDto = new TCoachDto();
        BeanUtils.copyProperties(coach,tCoachDto);
        return tCoachDto;
    }

    @Override
    public Map qqLogin(String code) throws Exception {
        //返回值路径
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=101780702&client_secret=be3cf7" +
                "9b89eafe3f0b8f90462aed8065&code="+code+"&redirect_uri=http://www.pawntest.com/qqlogin";
        //获取临时令牌
        String value = new RestTemplate().getForObject(url,String.class);
        //获取访问令牌
        String token = value.substring(value.indexOf("=")+1,value.indexOf("&"));
        //获取openid
        url="https://graph.qq.com/oauth2.0/me?access_token="+token;
        value = new RestTemplate().getForObject(url,String.class);
        String openid = value.substring(value.lastIndexOf(":")+2,value.lastIndexOf("\""));
        url="https://graph.qq.com/user/get_user_info?access_token="+token+"&oauth_consumer_key=101780702&openid="+openid;
        value = new RestTemplate().getForObject(url,String.class);
        //根据openid查找用户
        QueryWrapper<TCoach> wrapper = new QueryWrapper<>();
        wrapper.eq("t_coach_openid",openid);
        TCoach coach = this.getOne(wrapper);
        HashMap<String,Object> map = new HashMap<>();
        if(coach!=null){
            String loginToken = JwtUtil.createToken(coach,3);
            map.put("token",loginToken);
            map.put("coach",coach);
            return map;
        }
        coach = new TCoach();
        coach.setTCoachOpenid(openid);
        String loginToken = JwtUtil.createToken(coach,3);
        map.put("token",loginToken);
        map.put("coach",null);
        return map;
    }

    @Override
    public void register(TCoachDto dto) throws Exception {
        //判断用户名是否存在
        QueryWrapper<TCoach> wrapper = new QueryWrapper<>();
        wrapper.eq("t_coach_mail",dto.getTCoachMail());
        TCoach coach = this.getOne(wrapper);
        if(coach!=null){
            throw new MyException("305","该邮箱已被注册");
        }
        wrapper.clear();
        wrapper.eq("t_coach_tel",dto.getTCoachTel());
        coach = this.getOne(wrapper);
        if(coach!=null){
            throw new MyException("306","该电话已被注册");
        }
        //数据存入数据库
        TCoach tCoach = new TCoach();
        BeanUtils.copyProperties(dto,tCoach);
        int result = mapper.insert(tCoach);
        if(result==0){
            throw new MyException("500","服务器异常");
        }
    }
}
