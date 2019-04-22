package com.yc.trip.api.business.request.wx;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcOptional;

/**
 * 小程序登录时的code解析接口的请求参数
 *
 * @author
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxCodeRequest extends AbstractBaseRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 小程序登录返回的code
     */
    private String code;

    /**
     * 昵称
     */
    @MvcOptional
    private String nickName;

    /**
     * 头像
     */
    @MvcOptional
    private String headImgUrl;

    /**
     * 微信appId
     */
    private String wxAppId;

    /**
     * 邀请人id
     */
    @MvcOptional
    private Long inviterId;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(code)) {
            return ResCode.PARA_NULL.info("小程序登录返回的code不能为空");
        }
        if (StringUtils.isBlank(wxAppId)) {
            return ResCode.PARA_NULL.info("微信appId不能为空");
        }
        return ResCode.success;
    }
}
