package com.woniu.yoga.domain;

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
public class TAdvertising implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer tAdvertisingVid;

    private String tAdvertisingUrl;

    private String tAdvertisingText;

    private LocalDateTime tAdvertisingTime;

    private String tSpare;


}
