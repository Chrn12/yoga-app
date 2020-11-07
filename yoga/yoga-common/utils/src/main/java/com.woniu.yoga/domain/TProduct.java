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
public class TProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_product_id", type = IdType.AUTO)
    private Integer tProductId;

    private String tProductName;

    private Integer tProductCount;

    private Integer tProductSold;

    private Integer tProductValidity;

    private String tProductRules;

    private String tProductDetails;

    private String tSpare;

    private Integer tVenueId;


}
