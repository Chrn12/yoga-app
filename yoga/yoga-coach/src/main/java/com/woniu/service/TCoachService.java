package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.entity.TCoachDto;
import com.woniu.yoga.domain.TCoach;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zjs
 * @since 2020-11-09
 */
public interface TCoachService extends IService<TCoach> {
    public TCoachDto login(TCoachDto dto) throws Exception;

    public Map qqLogin(String code)throws Exception;

    public void register(TCoachDto dto)throws Exception;
}
