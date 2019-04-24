package com.yc.trip.api.business.service.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Maps;
import com.yc.gateway.api.sms.facade.SendSmsProfFacade;
import com.yc.gateway.api.sms.request.SendSmsRequest;
import com.yc.trip.api.business.facade.sms.SmsProfFacade;
import com.yc.trip.api.business.request.sms.VerifyCodeSmsSendRequest;
import com.yc.trip.api.core.constants.R;
import com.yc.trip.api.core.util.RandomUtils;
import org.go.api.core.integration.AbstractDubboIntegrationService;
import org.go.framework.base.annotation.RpcClass;
import org.go.framework.base.annotation.RpcMethod;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * 短信高级服务实现类
 *
 * @author 素还真
 * @since 2018-04-22
 */
@RpcClass(R.smsProfFacade)
@Service(version = "1.0.0")
public class SmsProfFacadeImpl extends AbstractDubboIntegrationService implements SmsProfFacade {

    public static final Integer VERIFY_CODE = 1;

    @Reference(version = "1.0.0")
    private SendSmsProfFacade sendSmsProfFacade;// 短信发送服务

    @Value("${sms.sign.id:5}")
    private Integer signNameId;

    @Override
    @RpcMethod("短信验证码发送")
    public String sendVerifyCodeSms(VerifyCodeSmsSendRequest request) throws PendingException {
        Map<String, String> map = Maps.newHashMap();
        String verifyCode = genVerifyCode();
        map.put("code", verifyCode);
        //获取短信发送内容
        sendSmsProfFacade.sendSms(SendSmsRequest.builder()
                .phone(request.getPhone())
                .signNameId(signNameId)
                .templateId(VERIFY_CODE)
                .params(map)
                .build());
        return verifyCode;
    }

    /**
     * 获取4位随机验证码
     *
     * @return
     */
    private String genVerifyCode() {
        return RandomUtils.getCode(4, 0);
    }
}
