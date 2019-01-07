package com.yc.trip.api.business.request.common;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;
import org.go.framework.base.annotation.MvcOptional;

/**
 * 关键字请求参数
 *
 * @author chenzw
 * @date 2018-09-14 16:50
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class KeywordsPageRequest extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = 2801332044915832298L;

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
