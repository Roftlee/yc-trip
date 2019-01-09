package com.yc.trip.api.business.request.region;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * 地区分类新增请求
 *
 * @author AsiQue
 * @since 2019/1/9 20:25
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegionSortAddRequest extends AbstractBaseRequestDto {

    /**
     * 分类名
     */
    private String name;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(name)) {
            return ResCode.PARA_NULL.info("分类名不能为空");
        }
        return ResCode.success;
    }
}
