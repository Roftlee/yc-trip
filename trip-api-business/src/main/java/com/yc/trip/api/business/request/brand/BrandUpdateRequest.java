package com.yc.trip.api.business.request.brand;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcOptional;

/**
 * BrandUpdateRequest
 *
 * @author: AsiQue
 * @date: 2019.04.24 00:06
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BrandUpdateRequest extends AbstractBaseRequestDto {

    /**
     * 品牌id
     */
    private Long id;

    /**
     * 品牌名
     */
    private String name;

    /**
     * 品牌logo
     */
    @MvcOptional
    private String logo;

    @Override
    public ResBean validateParam() {
        if (id == null || id <= 0) {
            return ResCode.PARA_NULL.info("品牌id不能为空");
        }
        if (StringUtils.isBlank(name)) {
            return ResCode.PARA_NULL.info("品牌名不能为空");
        }
        return ResCode.success;
    }
}
