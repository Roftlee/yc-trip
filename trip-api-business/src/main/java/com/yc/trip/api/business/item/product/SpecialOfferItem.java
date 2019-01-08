package com.yc.trip.api.business.item.product;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 优惠活动Item
 *
 * @author AsiQue
 * @since 2019/1/8 23:42
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecialOfferItem implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 活动图片
     */
    private String imageUrl;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 优惠活动产品列表
     */
    private List<SpecialOfferProductItem> specialOfferProductItems;
}
