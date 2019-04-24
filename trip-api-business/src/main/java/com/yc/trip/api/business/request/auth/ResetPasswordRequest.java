package com.yc.trip.api.business.request.auth;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * 重置密码请求
 *
 * @author: AsiQue
 * @date :2018.04.20 10:12
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResetPasswordRequest extends AbstractBaseRequestDto {

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 验证码
     */
    private String vertifyCode;

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
        if (StringUtils.isBlank(phone)) {
            return ResCode.PARA_NULL.info("手机号码不能为空");
        }
        if (StringUtils.isBlank(vertifyCode)) {
            return ResCode.PARA_NULL.info("验证码不能为空");
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
