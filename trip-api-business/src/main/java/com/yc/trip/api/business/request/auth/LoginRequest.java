package com.yc.trip.api.business.request.auth;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * 登录请求
 *
 * @author: chenzw
 * @create: 2018/11/26 14:35
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoginRequest extends AbstractBaseRequestDto {

    private static final long serialVersionUID = -6062451405184150814L;
    /**
     * 用户名
     */
    private String loginName;

    /**
     * 公钥
     */
    private String password;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(loginName)) {
            return ResCode.PARA_NULL.info("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return ResCode.PARA_NULL.info("登陆密码不能为空");
        }
        return ResCode.success;
    }
}
