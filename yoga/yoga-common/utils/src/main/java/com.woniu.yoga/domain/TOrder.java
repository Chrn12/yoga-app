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
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_order_id", type = IdType.AUTO)
    private Integer tOrderId;

    private LocalDateTime tOrderTime;

    private Double tOrderMoney;

    /**
     * 0提交1通过2拒绝3付款4退款
     */
    private Integer tOrderStatus;

    /**
     * 0好评1中评2差评
     */
    private Integer tOrderComment;

    private Integer tOrderSid;

    private Integer tOrderVid;

    private Integer tOrderCid;

    private LocalDateTime tOrderStart;

    private LocalDateTime tOrderEnd;

    private String tOrderDuration;

    private String tSpare;


}
