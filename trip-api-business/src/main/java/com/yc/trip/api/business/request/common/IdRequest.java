package com.yc.trip.api.business.request.common;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcInject;

/**
 * Id请求参数
 *
 * @author chenzw
 * @date 2018-09-14 16:50
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IdRequest extends AbstractBaseRequestDto {

    private static final long serialVersionUID = -2706782438957936765L;

    /**
     * id
     */
    private Long id;

    /**
     * 角色ID
     */
    @MvcInject
    private Long roleId;

    /**
     * 用户ID
     */
    @MvcInject
    private Long userId;


    @Override
    public ResBean validateParam() {
        if (id == null || id <= 0) {
            return ResCode.PARA_NULL.info("id不能为空");
        }
        if (roleId == null || roleId <= 0) {
            return ResCode.PARA_NULL.info("角色ID不能为空");
        }
        if (userId == null || userId <= 0) {
            return ResCode.PARA_NULL.info("用户ID不能为空");
        }
        return ResCode.success;
    }
}
