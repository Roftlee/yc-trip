package com.yc.trip.web.interceptor;

import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.web.service.session.SessionService;
import org.go.framework.service.AbstractService;
import org.go.framework.util.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 素闲人
 * @Description 权限拦截器
 */
public class AuthInterceptor extends AbstractService implements HandlerInterceptor {

    /**
     * 免登录URL(不需要登录)
     */
    private String[] noLoginUrls;

    @Autowired
    private SessionService sessionService;


    private void writeResponse(HttpServletResponse response, String code, String msg) {
        PrintWriter w = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            w = response.getWriter();
            w.write("{\"rspCd\":\"" + code
                    + "\",\"rspInf\":\"" + msg + "\",\"responseTm\":\""
                    + DateUtil.getDateStringByFormatString(
                    DateUtil.dataFormatyyyyMMddHHmmss)
                    + "\"}");
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } finally {
            if (null != w) {
                w.flush();
                w.close();
            }
        }
    }

    /**
     * 是否需要登录:true需要，false不需要
     *
     * @param reqUrl 请求URL
     * @return
     */
    private boolean needLogin(String reqUrl) {
        if (noLoginUrls == null || noLoginUrls.length == 0) {
            return true;
        }
        for (String url : noLoginUrls) {
            if (reqUrl.contains(url)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
        debug("====preHandle:AuthInterceptor------" + requestUrl + "===");
        //如果不需要登录，则直接返回,无需做后续的权限检查
        if (!needLogin(requestUrl)) {
            info("该请求不需要登录" + requestUrl);
            return true;
        }
        //如果用户未登录，则返回需要登录
        if (!sessionService.isLogin()) {
            info("用户未登录或会话已失效");
            writeResponse(response, ResCode.SESSION_AUTH_FAILED.getCode(), ResCode.SESSION_AUTH_FAILED.getInfo());
            return false;
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    public String[] getNoLoginUrls() {
        return noLoginUrls;
    }

    public void setNoLoginUrls(String[] noLoginUrls) {
        this.noLoginUrls = noLoginUrls;
    }

}
