package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.entity.TStudentDto;
import com.woniu.entity.TStudentParam;
import com.woniu.yoga.domain.TStudent;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tx
 * @since 2020-11-09
 */
public interface TStudentService extends IService<TStudent> {
    public TStudentDto getLogin(TStudentParam tStudentParam)throws Exception;
    public TStudentDto qqLogin(String tStudentOpenid) throws Exception;
    public void updatePhoto(String tStudentId,String tStudentImg)throws Exception;
}
