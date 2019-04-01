package com.yc.trip.api.business.request.product;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;
import org.go.framework.base.annotation.MvcOptional;

/**
 * ProductPageRequest
 *
 * @author: AsiQue
 * @date: 2019.04.01 21:09
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductPageRequest extends AbstractBasePageRequestDto {

    /**
     * 所属分类Id
     */
    @MvcOptional
    private Long sortId;

    /**
     * 所属地区分类Id
     */
    @MvcOptional
    private Long regionSortId;

    /**
     * 所属供应商Id
     */
    @MvcOptional
    private Long providerId;

    /**
     * 关键字
     */
    @MvcOptional
    private String keywords;

    @Override
    public ResBean validateParam() {
        if (isPageParamNull()) {
            return ResCode.PARA_NULL.info("缺少必要的分页参数");
        }
        return ResCode.success;
    }
}
