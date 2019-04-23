package com.yc.trip.api.business.request.store;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcOptional;

/**
 * StoreUpdateRequest
 *
 * @author: AsiQue
 * @date: 2019.04.23 23:35
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StoreUpdateRequest extends AbstractBaseRequestDto {

    /**
     * 门店id
     */
    private Long id;

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

    @Override
    public ResBean validateParam() {
        if (id == null || id <= 0) {
            return ResCode.PARA_NULL.info("门店id不能为空");
        }
        if (StringUtils.isBlank(storeName)) {
            return ResCode.PARA_NULL.info("门店名称不能为空");
        }
        if (StringUtils.isBlank(avatar)) {
            return ResCode.PARA_NULL.info("头像不能为空");
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
        return ResCode.success;
    }
}
