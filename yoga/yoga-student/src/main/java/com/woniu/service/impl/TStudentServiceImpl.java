package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.TStudentDto;
import com.woniu.entity.TStudentParam;
import com.woniu.mapper.TStudentMapper;
import com.woniu.service.TStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.utils.JwtUtil;
import com.woniu.yoga.domain.TStudent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tx
 * @since 2020-11-09
 */
@Service
public class TStudentServiceImpl extends ServiceImpl<TStudentMapper, TStudent> implements TStudentService {
    @Resource
    private TStudentMapper tStudentMapper;
    public TStudentDto getLogin(TStudentParam tStudentParam) throws Exception {
        TStudent tStudent = new TStudent();
        BeanUtils.copyProperties(tStudentParam,tStudent);
        TStudent student = null;
        QueryWrapper<TStudent> wrapper = new QueryWrapper();
        if(tStudent.getTStudentMail() != null){
            wrapper.eq("t_student_mail",tStudent.getTStudentMail());
            wrapper.eq("t_student_password",tStudent.getTStudentPassword());
            student = tStudentMapper.selectOne(wrapper);
        }else if(tStudent.getTStudentTel() != null){
            wrapper.eq("t_student_tel",tStudent.getTStudentTel());
            wrapper.eq("t_student_password",tStudent.getTStudentPassword());
            student = tStudentMapper.selectOne(wrapper);
        }
        String token = JwtUtil.createToken(student, 3);
        TStudentDto tStudentDto = new TStudentDto();
        BeanUtils.copyProperties(student,tStudentDto);
        return tStudentDto;
    }
}
