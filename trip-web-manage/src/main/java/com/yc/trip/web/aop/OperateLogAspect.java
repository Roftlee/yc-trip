package com.yc.trip.web.aop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.oss.constants.ResCode;
import com.yc.trip.api.business.dto.system.OperateLog;
import com.yc.trip.api.business.facade.system.OperateLogFacade;
import com.yc.trip.api.core.enums.OperTargetType;
import com.yc.trip.api.core.util.GsonUtil;
import com.yc.trip.web.annotation.LogConfiguration;
import com.yc.trip.web.bean.session.SessionUser;
import com.yc.trip.web.service.session.SessionService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.go.framework.service.AbstractService;
import org.go.framework.util.common.StringUtil;
import org.go.framework.util.common.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author dawei.liu
 * @Description 操作日志切面
 * @date 2017年2月19日 下午10:00:42
 */
@SuppressWarnings("Duplicates")
@Aspect
@Component
public class OperateLogAspect extends AbstractService {

    /**
     * 日志服务
     */
    @Reference(version = "1.0.0")
    private OperateLogFacade operateLogFacade;
    /**
     * 会话服务
     */
    @Autowired
    private SessionService sessionService;

    // Controller层切点
    @Pointcut("@annotation(com.yc.trip.web.annotation.LogConfiguration)")
    public void controllerAspect() {
    }


    @AfterReturning(pointcut = "controllerAspect()", returning = "returnValue")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue) {
        try {
            HttpServletRequest request = getRequest();
            SessionUser sessionUser = sessionService.getSessionUser();
            OperateLog operateLog = new OperateLog();
            if (sessionUser == null) {
                throw ResCode.SESSION_AUTH_FAILED.toException();
            }
            operateLog.setUserId(sessionUser.getUserId());
            operateLog.setUserName(sessionUser.getName());
            // 设置操作信息
            setOperInfo(joinPoint, operateLog);
            // 请求URL
            operateLog.setRequestUrl(request.getRequestURI().replace(request.getContextPath(), ""));
            // 获取用户IP
            operateLog.setRemoteIp(WebUtils.getClientIp(request));
            operateLogFacade.add(operateLog);
        } catch (Exception ex) {
            error("记录操作日志信息失败", ex);
        }
    }

    private void setOperInfo(JoinPoint joinPoint, OperateLog operateLog) throws Exception {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] arguments = joinPoint.getArgs();
        // 获取注解
        LogConfiguration logConfiguration = method.getAnnotation(LogConfiguration.class);
        setParamsInfo(method.getParameters(), arguments, logConfiguration, operateLog);
        operateLog.setPlatformType(logConfiguration.platformType());
        operateLog.setOperTargetType(logConfiguration.operTargetType());
        operateLog.setOperType(logConfiguration.operType());
    }

    /**
     * 处理请求参数
     */
    private void setParamsInfo(Parameter[] params, Object[] arguments, LogConfiguration logConfiguration, OperateLog operateLog) throws Exception {
        for (int i = 0; i < params.length; i++) {
            Parameter param = params[i];
            if (param.isAnnotationPresent(org.springframework.web.bind.annotation.RequestBody.class)) {
                Class<?> paramClass = param.getType();
                // 设置操作对象描述信息
                setObjDesc(paramClass, arguments[i], logConfiguration, operateLog);
                // 请求数据
                operateLog.setRequestData(GsonUtil.getDateFormatGson().toJson(arguments[i]));
                break;
            }
        }
    }

    private void setObjDesc(Class<?> paramClass, Object obj, LogConfiguration logConfiguration, OperateLog operateLog) throws Exception {
        // 如果displayProp不为空，则直接选择此属性
        OperTargetType operTargetType = logConfiguration.operTargetType();
        if (StringUtil.isNotBlank(logConfiguration.description())) {
            operateLog.setOperDesc(logConfiguration.description());
        }
        if (StringUtil.isBlank(logConfiguration.description())) {
            operateLog.setOperDesc(operateLog.getOperType().getName() + "-" + operTargetType.getName());
        }
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
