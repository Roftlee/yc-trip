package com.yc.trip.api.business.request.common;

import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.core.enums.Nullable;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;
import org.go.framework.base.annotation.MvcIgnore;

/**
 * 分页查询请求
 *
 * @author AsiQue
 * @since 2019/1/7 22:23
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PageRequest extends AbstractBasePageRequestDto {

    /**
     * 占位，无实际作用
     */
    @MvcIgnore
    private Nullable nullable;

    @Override
    public ResBean validateParam() {
        if (isPageParamNull()) {
            return ResCode.PARA_NULL.info("缺少必要的分页参数");
        }
        return ResCode.success;
    }
}
