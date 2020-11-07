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
public class TCoach implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_coach_id", type = IdType.AUTO)
    private Integer tCoachId;

    private String tCoachName;

    private String tCoachPassword;

    private String tCoachTel;

    private String tCoachMail;

    private String tCoachOpenid;

    private String tCoachImg;

    private Integer tCoachSex;

    private Integer tCoachAge;

    private String tCoachAddress;

    private Integer tCoachInfoLevel;

    private Double tCoachBalance;

    private Integer tCoachStatus;

    private Integer tCoachTeachStatus;

    private LocalDateTime tCoachCreateTime;

    private String tCoachSpare;

    private String tCoachSect;


}
