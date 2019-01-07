package com.yc.trip.web.spi;

import com.google.gson.Gson;
import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.web.bean.session.SessionUser;
import com.yc.trip.web.constants.SessionKey;
import org.go.framework.core.exception.PendingException;
import org.go.framework.web.aop.spi.IInjectFieldFetcher;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * MVC字段注入的Fetcher实现类
 */
@SuppressWarnings("Duplicates")
public class InjectFieldFetcherImpl implements IInjectFieldFetcher {

    @Override
    public Object getValue(HttpServletRequest request, String fieldName) throws PendingException {
        // 获取会话用户信息
        SessionUser sessionUser = getSessionUser(request);
        // 如果会话用户不存在
        if (sessionUser == null) {
            throw ResCode.SESSION_AUTH_FAILED.toException();
        }

        try {
            // 获取对应属性值
            Field field = SessionUser.class.getDeclaredField(fieldName);

            field.setAccessible(true);

            return field.get(sessionUser);

        } catch (Exception ex) {
            throw ResCode.injectFieldInvalid.toException();
        }
    }

    /**
     * 获取用户会话信息
     *
     * @param request
     * @return
     */
    private SessionUser getSessionUser(HttpServletRequest request) {
        String jsonStr = (String) request.getSession().getAttribute(SessionKey.SESSION_KEY);
        return jsonStr == null ? null : new Gson().fromJson(jsonStr, SessionUser.class);
    }
}
