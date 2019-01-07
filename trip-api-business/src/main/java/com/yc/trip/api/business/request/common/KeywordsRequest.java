package com.yc.trip.api.business.request.common;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
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
public class KeywordsRequest extends AbstractBaseRequestDto {

    private static final long serialVersionUID = 2801332044915832298L;

    /**
     * 关键字
     */
    @MvcOptional
    private String keywords;

    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }
}
