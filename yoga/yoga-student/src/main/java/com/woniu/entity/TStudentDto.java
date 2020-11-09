package com.woniu.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TStudentDto {
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

    private String token;

    private Boolean rememberMe;
}
