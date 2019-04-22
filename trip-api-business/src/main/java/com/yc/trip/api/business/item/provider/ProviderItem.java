package com.yc.trip.api.business.item.provider;

import com.yc.trip.api.business.dto.brand.Brand;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ProviderItem
 *
 * @author: AsiQue
 * @date: 2019.04.22 17:51
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProviderItem implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     * 供应商名称
     */
    private String providerName;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系方式
     */
    private String linkNum;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 到期时间
     */
    private Date endTime;

    /**
     * 子账号个数
     */
    private Integer subCount;

    /**
     * 可用子账号个数
     */
    private Integer remainSubCount;

    /**
     * 已使用子账号
     */
    private Integer usedSubCount;

    /**
     * 营业执照
     */
    private String licenseUrl;

    /**
     * 合同文件
     */
    private String contractUrl;

    /**
     * 注册时间
     */
    private Date createdTime;

    /**
     * 关联品牌列表
     */
    private List<Brand> brandList;
}
