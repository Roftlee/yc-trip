package com.yc.trip.api.business.facade.sms;

import com.yc.trip.api.business.request.sms.VerifyCodeSmsSendRequest;
import org.go.framework.core.exception.PendingException;


/**
 * 短信高级服务
 */
public interface SmsProfFacade {

    /**
     * 发送验证码短信
     *
     * @param request
     * @return
     */
    String sendVerifyCodeSms(VerifyCodeSmsSendRequest request) throws PendingException;
}
