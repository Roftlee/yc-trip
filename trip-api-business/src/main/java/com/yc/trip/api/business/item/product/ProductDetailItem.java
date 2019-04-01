package com.yc.trip.api.business.item.product;

import lombok.*;

import java.io.Serializable;

/**
 * ProductDetailItem
 *
 * @author: AsiQue
 * @date: 2019.04.01 21:36
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductDetailItem implements Serializable {

    /**
     * 产品id
     */
    private Long id;

    /**
     * 产品名称(标题)
     */
    private String name;

    /**
     * 产品简介
     */
    private String briefDesc;

    /**
     * 所属分类Id
     */
    private Long sortId;

    /**
     * 所属地区Id
     */
    private Long regionId;

    /**
     * 所属供应商Id
     */
    private Long providerId;

    /**
     * 商品成本
     */
    private Double cost;

    /**
     * 售价
     */
    private Double price;

    /**
     * 费用说明
     */
    private String priceDesc;

    /**
     * 门店分成比例(百分比)
     */
    private Integer commissionRate;

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
}
