package com.yc.trip.api.business.request.user;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcInject;

/**
 * 微信用户绑定请求
 *
 * @author: AsiQue
 * @date: 2019.03.27 20:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserBindRequest extends AbstractBaseRequestDto {

    /**
     * 用户id
     */
    @MvcInject
    private Long userId;

    /**
     * 手机号
     */
    private String phone;

    @Override
    public ResBean validateParam() {

        if (StringUtils.isBlank(phone)) {
            return ResCode.paramNull.info("手机号不能为空");
        }

        return ResCode.success;
    }
}
