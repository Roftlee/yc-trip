package com.yc.trip.api.business.request.region;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * RegionSortUpdateRequest
 *
 * @author AsiQue
 * @since 2019/1/9 20:26
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegionSortUpdateRequest extends AbstractBaseRequestDto {

    /**
     * id
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;

    @Override
    public ResBean validateParam() {
        if (id == null || id <= 0) {
            return ResCode.PARA_NULL.info("id不能为空");
        }
        if (StringUtils.isBlank(name)) {
            return ResCode.PARA_NULL.info("分类名不能为空");
        }
        return ResCode.success;
    }
}
