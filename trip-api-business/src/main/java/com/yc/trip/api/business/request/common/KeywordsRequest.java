package com.yc.trip.api.business.request.common;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcOptional;

/**
 * 关键字请求
 *
 * @author AsiQue
 * @since 2019/1/7 21:53
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class KeywordsRequest extends AbstractBaseRequestDto {

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
