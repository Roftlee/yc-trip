package com.yc.trip.api.business.request.common;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.ArrayUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * Id列表请求参数
 *
 * @author chenzw
 * @date 2018-09-14 16:50
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IdsRequest extends AbstractBaseRequestDto {

    private static final long serialVersionUID = -6478272112642451493L;

    /**
     * id列表
     */
    private Long[] ids;

    @Override
    public ResBean validateParam() {
        if (ArrayUtils.isEmpty(ids)) {
            return ResCode.paramNull.info("id列表不能为空");
        }
        return ResCode.success;
    }
}
