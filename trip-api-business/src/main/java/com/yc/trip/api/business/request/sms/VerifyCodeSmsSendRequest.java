package com.yc.trip.api.business.request.sms;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * 短信验证码发送请求
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VerifyCodeSmsSendRequest extends AbstractBaseRequestDto {

    private static final long serialVersionUID = -3111257564837718249L;

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
