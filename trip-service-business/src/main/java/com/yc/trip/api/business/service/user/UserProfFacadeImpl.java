package com.yc.trip.api.business.service.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.facade.user.UserFacade;
import com.yc.trip.api.business.facade.user.UserProfFacade;
import com.yc.trip.api.business.request.user.WxUserBindRequest;
import com.yc.trip.api.core.constants.R;
import org.go.api.core.integration.AbstractDubboIntegrationService;
import org.go.framework.base.annotation.RpcClass;
import org.go.framework.base.annotation.RpcMethod;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserProfFacadeImpl
 *
 * @author: AsiQue
 * @date: 2019.03.27 21:06
 */
@Service(version = "1.0.0")
@RpcClass(R.userProfFacade)
public class UserProfFacadeImpl extends AbstractDubboIntegrationService implements UserProfFacade {

    /**
     * 用户服务
     */
    @Autowired
    private UserFacade userFacade;

    @Override
    @RpcMethod("绑定微信用户")
    public User bindWxUser(WxUserBindRequest request) throws PendingException {

        // 查询用户信息
        User user = userFacade.mustGet(User.builder().id(request.getUserId()).build());

        // 更新用户信息
        userFacade.updateUser(User.builder()
                .id(request.getUserId())
                .phone(request.getPhone())
                .build());

        return user;
    }
}
