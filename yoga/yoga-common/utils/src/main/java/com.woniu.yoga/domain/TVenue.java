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
public class TVenue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_venue_id", type = IdType.AUTO)
    private Integer tVenueId;

    private String tVenueName;

    private String tVenuePassword;

    private String tVenueTel;

    private String tVenueMail;

    private String tVenueOpenid;

    private String tVenueImg;

    private String tVenueAddress;

    private String tVenueDescribe;

    private Double tVenueBalance;

    private Integer tVenueStatus;

    private LocalDateTime tVenueCreateTime;

    private String tVenueSpare;


}
