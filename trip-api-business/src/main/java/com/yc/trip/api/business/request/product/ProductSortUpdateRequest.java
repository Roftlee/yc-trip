package com.yc.trip.api.business.request.product;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * 产品分类更新请求
 *
 * @author AsiQue
 * @since 2019/1/9 19:59
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductSortUpdateRequest extends AbstractBaseRequestDto {

    /**
     * id
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    @Override
    public ResBean validateParam() {
        if (id == null || id <= 0) {
            return ResCode.PARA_NULL.info("id不能为空");
        }
        if (StringUtils.isBlank(name)) {
            return ResCode.PARA_NULL.info("分类名不能为空");
        }
        if (StringUtils.isBlank(icon)) {
            return ResCode.PARA_NULL.info("图标不能为空");
        }
        return ResCode.success;
    }
}
