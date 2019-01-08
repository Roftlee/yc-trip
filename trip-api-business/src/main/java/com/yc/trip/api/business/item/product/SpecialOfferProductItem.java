package com.yc.trip.api.business.item.product;

import lombok.*;

import java.io.Serializable;

/**
 * 优惠活动产品Item
 *
 * @author AsiQue
 * @since 2019/1/8 23:41
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecialOfferProductItem implements Serializable {

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
     * 成交价
     */
    private Double strikePrice;

    /**
     * 图片链接
     */
    private String imageUrl;
}
