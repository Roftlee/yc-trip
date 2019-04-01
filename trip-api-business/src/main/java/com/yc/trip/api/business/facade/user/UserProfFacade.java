package com.yc.trip.api.business.facade.user;

import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.request.user.WxUserBindRequest;
import org.go.framework.core.exception.PendingException;

/**
 * UserProfFacade
 *
 * @author: AsiQue
 * @date: 2019.03.27 21:05
 */
public interface UserProfFacade {

    /**
     * 微信用户绑定
     * @param request
     * @return
     * @throws PendingException
     */
    User bindWxUser(WxUserBindRequest request) throws PendingException;
}
