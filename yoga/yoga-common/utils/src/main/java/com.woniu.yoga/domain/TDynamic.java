package com.woniu.yoga.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author fei
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TDynamic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_dynamic_id", type = IdType.AUTO)
    private Integer tDynamicId;

    private Integer tDynamicAuthor;

    /**
     * 1学生，2场馆，3教练
     */
    private Integer tDynamicRole;

    private String tDynamicContext;

    private String tDynamicImg;

    private LocalDateTime tDynamicTime;

    private String tSpare;


}
