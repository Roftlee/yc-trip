package com.yc.trip.api.business.item.user;

import com.yc.trip.api.business.enums.user.UserType;
import lombok.*;

import java.io.Serializable;

/**
 * UserInfoItem
 *
 * @author: AsiQue
 * @date: 2019.04.22 16:44
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserInfoItem implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户类型
     */
    private UserType userType;

    /**
     * 用户所属门店id
     */
    private Long storeId;

    /**
     * 用户邀请人id
     */
    private Long inviterId;

}
