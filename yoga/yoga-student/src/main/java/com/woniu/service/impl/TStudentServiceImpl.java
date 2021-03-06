package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.entity.TStudentDto;
import com.woniu.entity.TStudentParam;
import com.woniu.mapper.TStudentMapper;
import com.woniu.myexception.MyException;
import com.woniu.service.TStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.utils.JwtUtil;

import com.woniu.yoga.domain.TStudent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(propagation = Propagation.REQUIRED)
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
        if(student == null){
            throw new MyException("404","账号不存在");
        }
        TStudentDto tStudentDto = new TStudentDto();
        BeanUtils.copyProperties(student,tStudentDto);
        return tStudentDto;
    }

    @Override
    public TStudentDto qqLogin(String tStudentOpenid) throws Exception {
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("t_student_openid",tStudentOpenid);
        TStudent student = tStudentMapper.selectOne(wrapper);
        TStudentDto studentDto = new TStudentDto();
        BeanUtils.copyProperties(student,studentDto);
        return studentDto;
    }

    @Override
    public void updatePhoto(String tStudentId, String tStudentImg) throws Exception {
        UpdateWrapper<TStudent> wrapper = new UpdateWrapper<>();
        wrapper.eq("t_student_id", tStudentId);
        wrapper.set("t_student_img",tStudentImg);
        int update = tStudentMapper.update(new TStudent(), wrapper);
        if(update < 1){
            throw new MyException("500","修改失败");
        }
    }

    @Override
    public void savaStudent(TStudentDto tStudentDto) throws Exception {
        QueryWrapper<TStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("t_student_mail",tStudentDto.getTStudentMail());
        TStudent student = tStudentMapper.selectOne(wrapper);
        if(student != null){
            throw new MyException("300","该邮箱已被注册");
        }
        QueryWrapper<TStudent> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("t_student_tel",tStudentDto.getTStudentTel());
        TStudent student1 = tStudentMapper.selectOne(wrapper1);
        if(student1 != null){
            throw new MyException("300","电话号码已被注册");
        }
        TStudent student2 = new TStudent();
        BeanUtils.copyProperties(tStudentDto,student2);
        tStudentMapper.insert(student2);
    }

    @Override
    public void updatePassword(TStudentDto tStudentDto) throws Exception {
        UpdateWrapper<TStudent> wrapper = new UpdateWrapper<>();
        wrapper.eq("t_student_mail",tStudentDto.getTStudentMail());
        wrapper.set("t_student_password",tStudentDto.getTStudentPassword());
        tStudentMapper.update(new TStudent(),wrapper);
    }
}
