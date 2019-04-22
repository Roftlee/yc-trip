package com.yc.trip.api.business.request.provider;

import com.yc.trip.api.business.dto.user.MerchantAccount;
import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcOptional;

import java.util.Date;
import java.util.List;

/**
 * ProviderUpdateRequest
 *
 * @author: AsiQue
 * @date: 2019.04.22 21:26
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProviderUpdateRequest extends AbstractBaseRequestDto {

    /**
     * 供应商id
     */
    private Long id;

    /**
     * 供应商名称
     */
    private String providerName;

    /**
     * 头像
     */
    @MvcOptional
    private String avatar;

    /**
     * 联系人
     */
    @MvcOptional
    private String linkMan;

    /**
     * 联系方式
     */
    private String linkNum;

    /**
     * 供应商地址
     */
    @MvcOptional
    private String address;

    /**
     * 子账号个数
     */
    private Integer subCount;

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
     * 关联品牌id列表
     */
    private List<Long> brandIds;

    /**
     * 子账号列表
     */
    @MvcOptional
    private List<MerchantAccount> accounts;

    @Override
    public ResBean validateParam() {
        if (id == null || id <= 0) {
            return ResCode.PARA_NULL.info("供应商id不能为空");
        }
        if (StringUtils.isBlank(providerName)) {
            return ResCode.PARA_NULL.info("供应商名称不能为空");
        }
        if (StringUtils.isBlank(linkNum)) {
            return ResCode.PARA_NULL.info("联系方式不能为空");
        }
        if (subCount == null || subCount <= 0) {
            return ResCode.PARA_NULL.info("子账号个数不能为空");
        }
        if (CollectionUtils.isEmpty(brandIds)) {
            return ResCode.PARA_NULL.info("关联品牌id列表不能为空");
        }
        return ResCode.success;
    }
}
