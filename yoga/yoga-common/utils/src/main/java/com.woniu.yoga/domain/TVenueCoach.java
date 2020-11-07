package com.woniu.yoga.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class TVenueCoach implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer tVenueId;

    private Integer tCoachId;

    private String tCoachName;

    private String tSpare;


}
