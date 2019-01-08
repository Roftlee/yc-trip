package com.yc.trip.api.business.request.common;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * id请求
 *
 * @author AsiQue
 * @since 2019/1/7 21:49
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IdRequest extends AbstractBaseRequestDto {

    /**
     * id
     */
    private Long id;

    @Override
    public ResBean validateParam() {
        if (id == null || id <= 0) {
            return ResCode.PARA_NULL.info("id不能为空");
        }
        return ResCode.success;
    }
}
