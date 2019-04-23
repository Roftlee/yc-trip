package com.yc.trip.api.business.request.store;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;
import org.go.framework.base.annotation.MvcOptional;

import java.util.Date;

/**
 * StorePageRequest
 *
 * @author: AsiQue
 * @date: 2019.04.23 23:05
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StorePageRequest extends AbstractBasePageRequestDto {

    /**
     * 品牌id
     */
    @MvcOptional
    private Long brandId;

    /**
     * 开始时间
     */
    @MvcOptional
    private Date queryStartTime;

    /**
     * 结束时间
     */
    @MvcOptional
    private Date queryEndTime;

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
