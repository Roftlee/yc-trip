package com.yc.trip.api.business.request.auth;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcInject;

/**
 * 修改密码请求
 *
 * @author: AsiQue
 * @date :2018.11.06 16:41
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ModifyPasswordRequest extends AbstractBaseRequestDto {

    /**
     * 用户Id
     */
    @MvcInject
    private Long userId;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认密码
     */
    private String confirmPassword;

    @Override
    public ResBean validateParam() {
        if (userId == null || userId <= 0) {
            return ResCode.PARA_NULL.info("用户Id不能为空");
        }
        if (StringUtils.isBlank(oldPassword)) {
            return ResCode.PARA_NULL.info("旧密码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            return ResCode.PARA_NULL.info("新密码不能为空");
        }
        if (StringUtils.isBlank(confirmPassword)) {
            return ResCode.PARA_NULL.info("确认密码不能为空");
        }
        return ResCode.success;
    }
}
