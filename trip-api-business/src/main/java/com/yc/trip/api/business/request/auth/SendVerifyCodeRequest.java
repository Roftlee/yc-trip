package com.yc.trip.api.business.request.auth;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * 发送验证码请求
 *
 * @author: AsiQue
 * @date :2018.11.06 16:54
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SendVerifyCodeRequest extends AbstractBaseRequestDto {

    /**
     * 手机号码
     */
    private String phone;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(phone)) {
            return ResCode.PARA_NULL.info("手机号码不能为空");
        }
        return ResCode.success;
    }
}
