package com.yc.trip.api.business.request.common;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * id分页请求
 *
 * @author AsiQue
 * @since 2019/1/7 21:49
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IdPageRequest extends AbstractBasePageRequestDto {

    /**
     * id
     */
    private Long id;

    @Override
    public ResBean validateParam() {
        if (isPageParamNull()) {
            return ResCode.PARA_NULL.info("缺少必要的分页参数");
        }
        if (id == null || id <= 0) {
            return ResCode.PARA_NULL.info("id不能为空");
        }
        return ResCode.success;
    }
}
