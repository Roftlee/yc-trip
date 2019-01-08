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
     * id
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
     * 所属分类Id
     */
    private Long sortId;

    /**
     * 所属地区Id
     */
    private Long regionId;

    /**
     * 所属地区分类Id
     */
    private Long regionSortId;

    /**
     * 所属供应商Id
     */
    private Long providerId;

    /**
     * 商品成本
     */
    private Double cost;

    /**
     * 成交价
     */
    private Double strikePrice;

    /**
     * 售价
     */
    private Double price;

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
     * 产品描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;
}
