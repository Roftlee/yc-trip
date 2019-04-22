package com.yc.trip.web.controller.wx;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.mp.api.request.auth.MpCodeRequest;
import com.yc.mp.api.request.security.MpDecryptRequest;
import com.yc.mp.api.response.auth.MpCodeResponse;
import com.yc.mp.api.service.auth.MpAuthService;
import com.yc.mp.api.service.security.MpSecurityService;
import com.yc.trip.api.business.dto.store.StoreCustomer;
import com.yc.trip.api.business.dto.store.StoreSales;
import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.dto.wx.WxApp;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.store.StoreCustomerFacade;
import com.yc.trip.api.business.facade.store.StoreSalesFacade;
import com.yc.trip.api.business.facade.user.UserFacade;
import com.yc.trip.api.business.facade.wx.WxAppFacade;
import com.yc.trip.api.business.request.auth.LoginRequest;
import com.yc.trip.api.business.request.wx.WxCodeRequest;
import com.yc.trip.api.business.request.wx.WxDecryptRequest;
import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.core.enums.Sex;
import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.web.bean.session.SessionUser;
import com.yc.trip.web.controller.base.AbstractBaseController;
import com.yc.trip.web.service.session.SessionService;
import com.yc.trip.web.wx.Code2SessionResponse;
import org.go.api.core.dto.ResDto;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信用户权限相关的控制器
 *
 * @author 素闲人
 */
@RestController
@RequestMapping("/wx")
public class WxAuthController extends AbstractBaseController {

    @Reference(version = "1.0.0")
    private UserFacade userFacade;// 用户服务

    @Autowired
    private SessionService sessionService;//会话服务

    @Reference(version = "1.0.0")
    private MpAuthService mpAuthService;// 小程序权限服务

    @Reference(version = "1.0.0")
    private MpSecurityService mpSecurityService;//手机号码解密服务

    @Reference(version = "1.0.0")
    private WxAppFacade wxAppFacade;//微信小程序服务

    @Reference(version = "1.0.0")
    private StoreCustomerFacade storeCustomerFacade;// 门店游客服务

    @Reference(version = "1.0.0")
    private StoreSalesFacade storeSalesFacade;// 门店销售服务

    /**
     * 微信小程序Code登录接口
     *
     * @param request
     * @param wxCodeRequest
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/code2session.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<Code2SessionResponse> code2session(HttpServletRequest request, @RequestBody WxCodeRequest wxCodeRequest) throws PendingException {

        //获取微信小程序信息
        WxApp wxApp = wxAppFacade.mustGet(WxApp.builder().wxAppId(wxCodeRequest.getWxAppId()).build());

        //对code进行处理，并返回SessionToken
        MpCodeResponse mpCodeResponse = mpAuthService.getMpCode(MpCodeRequest.builder()
                .appId(wxCodeRequest.getWxAppId())
                .jsCode(wxCodeRequest.getCode())
                .secret(wxApp.getWxAppSecret())
                .build());

        // 获取用户信息
        User user = userFacade.getUser(User.builder().openId(mpCodeResponse.getOpenId()).build());
        User inviter = null;
        if (wxCodeRequest.getInviterId() != null && wxCodeRequest.getInviterId() != 0) {
            inviter = userFacade.mustGet(User.builder().id(wxCodeRequest.getInviterId()).build());
        }

        // 用户不存在，新增用户信息
        if (user == null) {
            user = userFacade.addUser(User.builder()
                    .avatar(wxCodeRequest.getHeadImgUrl())
                    .openId(mpCodeResponse.getOpenId())
                    .name(wxCodeRequest.getNickName())
                    .phone("0")// 默认手机号为0
                    .sex(Sex.Male)
                    .userType(UserType.CUSTOMER)// 默认游客身份
                    .isDelete(YesNoStatus.NO)
                    .build());

            // 通过邀请人分享链接进入小程序的用户，处理邀请人信息
            if (inviter != null) {

            }
        } else {// TODO：已注册用户通过邀请人分享的链接进入小程序时如何处理？
            StoreSales storeSales = storeSalesFacade.getStoreSales(StoreSales.builder().userId(wxCodeRequest.getInviterId()).build());
            if (storeSales != null) {
                storeCustomerFacade.addStoreCustomer(StoreCustomer.builder().userId(user.getId()).storeId(storeSales.getStoreId()).isVip(YesNoStatus.NO).build());
            } else {
                StoreCustomer storeCustomer = storeCustomerFacade.mustGet(StoreCustomer.builder().userId(wxCodeRequest.getInviterId()).build());
                storeCustomerFacade.addStoreCustomer(StoreCustomer.builder().userId(user.getId()).storeId(storeCustomer.getStoreId()).isVip(YesNoStatus.NO).build());
            }
        }

        // 判断用户可用性
        if (YesNoStatus.YES.equals(user.getIsDelete())) {
            ResCode.userDBError.throwException("用户已被禁用");
        }

        SessionUser sessionUser = SessionUser.from(user);

        //设置会话用户信息
        sessionUser.setSessionKey(mpCodeResponse.getSessionKey());
        setSessionUser(sessionUser);
        return new ResDto<>(Code2SessionResponse.builder().sessionId(request.getSession().getId()).sessionUser(sessionUser).build());
    }

    /**
     * 微信用户手机号解密
     *
     * @param wxDecryptRequest
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/decryptPhoneNumber.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<String> decryptPhoneNumber(@RequestBody WxDecryptRequest wxDecryptRequest) throws PendingException {

        return new ResDto<>(mpSecurityService.decryptPhoneNumber(MpDecryptRequest.builder()
                .sessionKey(getSessionUser().getSessionKey())
                .encryptedData(wxDecryptRequest.getEncryptedData())
                .iv(wxDecryptRequest.getIv())
                .build()));
    }

    /**
     * 模拟登录
     *
     * @param loginRequest
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/simulateLogin.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<SessionUser> simulateLogin(@RequestBody LoginRequest loginRequest) throws PendingException {
        SessionUser sessionUser = SessionUser.from(userFacade.mustGet(User.builder().phone(loginRequest.getLoginName()).build()));
        setSessionUser(sessionUser);
        return new ResDto<>(sessionUser);
    }

}
