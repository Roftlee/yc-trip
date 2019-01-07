package com.yc.trip.web.service.session;

import com.google.gson.Gson;
import com.yc.trip.web.bean.session.SessionUser;
import com.yc.trip.web.constants.SessionKey;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 会话相关服务
 *
 * @author 素闲人
 */
@Component
public class SessionService {


    /**
     * 设置会话信息
     *
     * @param sessionUser
     */
    public void setSessionUser(SessionUser sessionUser) {
        getHttpRequest().getSession().setAttribute(SessionKey.SESSION_KEY, new Gson().toJson(sessionUser));
    }

    /**
     * 移除会话信息
     *
     * @param
     */
    public void removeSessionUser() {
        getHttpRequest().getSession().setAttribute(SessionKey.SESSION_KEY, null);
    }

    /**
     * 获得会话用户信息
     *
     * @return
     */
    public SessionUser getSessionUser() {
        String jsonStr = (String) getHttpRequest().getSession().getAttribute(SessionKey.SESSION_KEY);
        return jsonStr == null ? null : new Gson().fromJson(jsonStr, SessionUser.class);
    }

    /**
     * 是否登录s
     *
     * @return
     */
    public Boolean isLogin() {
        return getHttpRequest().getSession().getAttribute(SessionKey.SESSION_KEY) != null;
    }

    private HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}
