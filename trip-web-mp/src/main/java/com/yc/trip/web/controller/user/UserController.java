package com.yc.trip.web.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.facade.user.UserProfFacade;
import com.yc.trip.api.business.request.user.WxUserBindRequest;
import com.yc.trip.web.bean.session.SessionUser;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.go.api.core.dto.ResDto;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author: AsiQue
 * @date :2018.08.15 16:00
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractBaseController {

    /**
     * 用户高级服务
     */
    @Reference(version = "1.0.0")
    private UserProfFacade userProfFacade;

    /**
     * 绑定微信用户
     *
     * @param request 微信用户绑定请求
     * @return
     * @throws PendingException
     */
    @RequestMapping("/bindUser.do")
    @MvcValidate
    public ResDto<SessionUser> bindUser(@RequestBody WxUserBindRequest request) throws PendingException {

        User user = userProfFacade.bindWxUser(request);
        SessionUser sessionUser = SessionUser.from(user);
        setSessionUser(sessionUser);

        return new ResDto<>(sessionUser);
    }
}
