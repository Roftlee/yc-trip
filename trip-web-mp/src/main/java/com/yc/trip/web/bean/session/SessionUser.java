package com.yc.trip.web.bean.session;

import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.enums.user.UserType;
import lombok.*;

import java.io.Serializable;

/**
 * 会话用户
 *
 * @author 素闲人
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SessionUser implements Serializable {

    private static final long serialVersionUID = 2396036086105438381L;

    /**
     * 微信openid
     */
    private String openId;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型
     */
    private UserType userType;

    /**
     * 小程序用户sessionKey
     */
    private String sessionKey;


    /**
     * 将用户转换成会话用户对象
     *
     * @param user
     * @return
     */
    public static SessionUser from(User user) {
        return SessionUser.builder()
                .openId(user.getOpenId())
                .userId(user.getId())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .name(user.getName())
                .userType(user.getUserType())
                .build();
    }

}
