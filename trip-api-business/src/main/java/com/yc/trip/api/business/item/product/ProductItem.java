package com.yc.trip.api.business.item.product;

import lombok.*;

import java.io.Serializable;

/**
 * 小程序端产品Item
 *
 * @author AsiQue
 * @since 2019/1/7 22:15
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductItem implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品简介
     */
    private String briefDesc;

    /**
     * 成交价
     */
    private Double strikePrice;

    /**
     * 售价
     */
    private Double price;

    /**
     * 费用说明
     */
    private String priceDesc;

    /**
     * 图片链接
     */
    private String imageUrl;

    /**
     * 视频链接
     */
    private String videoUrl;

    /**
     * 行程介绍
     */
    private String routeIntroduce;

    /**
     * 行程特色
     */
    private String routeFeature;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

    //-------------------- 扩展属性--------------------------

    /**
     * 客户赚差价
     */
    private Double diffPrice;
}
