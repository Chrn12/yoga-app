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
public class TStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_student_id", type = IdType.AUTO)
    private Integer tStudentId;

    private String tStudentName;

    private String tStudentPassword;

    private String tStudentTel;

    private String tStudentMail;

    private String tStudentOpenid;

    private String tStudentImg;

    private Integer tStudentSex;

    private Integer tStudentAge;

    private Integer tStudentInfoLevel;

    private Double tStudentBalance;

    private Integer tStudentStatus;

    private LocalDateTime tStudentCreateTime;

    private String tStudentSpare;


}
