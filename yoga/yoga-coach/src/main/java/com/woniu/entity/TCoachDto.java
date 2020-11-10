package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fei
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TCoachDto implements Serializable {
    private static final long serialVersionUID = 1L;

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
