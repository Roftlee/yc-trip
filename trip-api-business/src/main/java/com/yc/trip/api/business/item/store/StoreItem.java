package com.yc.trip.api.business.item.store;

import com.yc.trip.api.core.enums.YesNoStatus;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * StoreItem
 *
 * @author: AsiQue
 * @date: 2019.04.23 23:07
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StoreItem implements Serializable {

    /**
     * 门店id
     */
    private Long id;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 关联品牌id
     */
    private Long brandId;

    /**
     * 关联品牌名
     */
    private String brandName;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系方式
     */
    private String linkNum;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 到期时间
     */
    private Date endTime;

    /**
     * 注册时间
     */
    private Date createdTime;
}
