package com.yc.trip.web.bean.session;

import com.yc.trip.api.business.dto.user.User;
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
     * 用户Id
     */
    private Long userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 临期事项数目
     */
    private Integer advanceNum;

    /**
     * 超期事项数目
     */
    private Integer overdueNum;

    /**
     * 将用户转换成会话用户对象
     *
     * @param user
     * @return
     */
    public static SessionUser from(User user) {
        return SessionUser.builder()
                .userId(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }
}
