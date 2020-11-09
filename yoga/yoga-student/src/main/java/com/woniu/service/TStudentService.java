package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
    public TStudent getLogin(TStudent tStudent)throws Exception;
}
