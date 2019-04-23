package com.yc.trip.api.business.request.store;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcOptional;

import java.util.Date;

/**
 * StoreAddRequest
 *
 * @author: AsiQue
 * @date: 2019.04.23 23:16
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StoreAddRequest extends AbstractBaseRequestDto {

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 关联品牌id
     */
    private Long brandId;

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
     * 营业执照
     */
    @MvcOptional
    private String licenseUrl;

    /**
     * 合同文件
     */
    @MvcOptional
    private String contractUrl;

    /**
     * 购买天数
     */
    private Integer purchaseDates;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(storeName)) {
            return ResCode.PARA_NULL.info("门店名称不能为空");
        }
        if (brandId == null || brandId <= 0) {
            return ResCode.PARA_NULL.info("关联品牌id不能为空");
        }
        if (StringUtils.isBlank(linkMan)) {
            return ResCode.PARA_NULL.info("联系人不能为空");
        }
        if (StringUtils.isBlank(linkNum)) {
            return ResCode.PARA_NULL.info("联系方式不能为空");
        }
        if (StringUtils.isBlank(address)) {
            return ResCode.PARA_NULL.info("门店地址不能为空");
        }
        if (purchaseDates == null || purchaseDates <= 0) {
            return ResCode.PARA_NULL.info("购买天数不能为空");
        }
        return ResCode.success;
    }
}
