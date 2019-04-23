package com.yc.trip.api.business.request.brand;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcInject;
import org.go.framework.base.annotation.MvcOptional;

/**
 * BrandAddRequest
 *
 * @author: AsiQue
 * @date: 2019.04.24 00:04
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BrandAddRequest extends AbstractBaseRequestDto {

    /**
     * 品牌名
     */
    private String name;

    /**
     * 品牌logo
     */
    @MvcOptional
    private String logo;

    /**
     * 创建人用户id
     */
    @MvcInject
    private Long userId;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(name)) {
            return ResCode.PARA_NULL.info("品牌名不能为空");
        }
        if (userId == null || userId <= 0) {
            return ResCode.PARA_NULL.info("创建人用户id不能为空");
        }
        return ResCode.success;
    }
}
