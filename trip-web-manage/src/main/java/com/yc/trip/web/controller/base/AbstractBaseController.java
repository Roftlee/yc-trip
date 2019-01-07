package com.yc.trip.web.controller.base;

import com.yc.trip.web.bean.session.SessionUser;
import com.yc.trip.web.service.session.SessionService;
import org.go.api.core.dto.AbstractBasePageRequestDto;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.core.exception.PendingException;
import org.go.framework.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 素闲人
 */
public class AbstractBaseController extends BaseController {

    @Autowired
    private SessionService sessionService;//会话服务

    protected SessionUser getSessionUser() {
        return sessionService.getSessionUser();
    }

    protected void setSessionUser(SessionUser sessionUser) {
        sessionService.setSessionUser(sessionUser);
    }

    protected void validateThrow(AbstractBaseRequestDto dto) throws PendingException {
        AbstractBaseRequestDto.validateThrow(dto);
    }

    protected void validateThrow(AbstractBasePageRequestDto dto) throws PendingException {
        AbstractBaseRequestDto.validateThrow(dto);
    }


}
