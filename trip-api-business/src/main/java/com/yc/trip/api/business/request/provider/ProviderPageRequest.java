package com.yc.trip.api.business.request.provider;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;
import org.go.framework.base.annotation.MvcOptional;

import java.util.Date;

/**
 * ProviderPageRequest
 *
 * @author: AsiQue
 * @date: 2019.04.22 17:45
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProviderPageRequest extends AbstractBasePageRequestDto {

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
     * 可用子账号个数
     */
    @MvcOptional
    private Integer remainSubCount;

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
