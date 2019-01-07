package com.yc.trip.web.spi;

import com.yc.trip.web.constants.SessionKey;
import org.go.framework.web.interceptor.spi.ISessionUserInjector;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 会话用户注入的实现
 *
 * @author 素闲人
 */
public class SessionUserInjectorImpl implements ISessionUserInjector {

    @Override
    public String getSessionUserJson(HttpServletRequest request) {
        return (String) getHttpRequest().getSession().getAttribute(SessionKey.SESSION_KEY);
    }

    private HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


}
