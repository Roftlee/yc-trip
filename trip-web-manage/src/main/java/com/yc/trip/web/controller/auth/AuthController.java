package com.yc.trip.web.controller.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.trip.api.business.dto.user.MerchantAccount;
import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.dto.user.UserPassword;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.sms.SmsProfFacade;
import com.yc.trip.api.business.facade.user.MerchantAccountFacade;
import com.yc.trip.api.business.facade.user.UserFacade;
import com.yc.trip.api.business.facade.user.UserPasswordFacade;
import com.yc.trip.api.business.request.auth.LoginRequest;
import com.yc.trip.api.business.request.auth.ModifyPasswordRequest;
import com.yc.trip.api.business.request.auth.ResetPasswordRequest;
import com.yc.trip.api.business.request.auth.SendVerifyCodeRequest;
import com.yc.trip.api.business.request.sms.VerifyCodeSmsSendRequest;
import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.core.enums.EnableStatus;
import com.yc.trip.api.core.enums.OperTargetType;
import com.yc.trip.api.core.enums.OperType;
import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.api.core.util.PwdUtils;
import com.yc.trip.web.annotation.LogConfiguration;
import com.yc.trip.web.bean.session.SessionUser;
import com.yc.trip.web.constants.SessionConstant;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.go.api.core.dto.ResDto;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.cache.CacheService;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.DateUtil;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * 用户登录
 *
 * @author: chenzw
 * @create: 2018/11/26 14:32
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends AbstractBaseController {

    @Autowired
    private CacheService cacheService;// redis缓存服务

    @Reference(version = "1.0.0")
    private UserFacade userFacade;// 用户服务

    @Reference(version = "1.0.0")
    private UserPasswordFacade userPasswordFacade;// 用户密码服务

    @Reference(version = "1.0.0")
    private SmsProfFacade smsProfFacade;// 短信服务

    @Reference(version = "1.0.0")
    private MerchantAccountFacade merchantAccountFacade;// 商户账号服务

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     * @throws PendingException
     */
    @LogConfiguration(operType = OperType.LOGIN, operTargetType = OperTargetType.LOGIN, description = "登录")
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<SessionUser> login(@RequestBody LoginRequest loginRequest) throws PendingException {
        try {

            // 查询用户信息
            User user = userFacade.mustGet(User.builder().phone(loginRequest.getLoginName()).build());

            if (YesNoStatus.YES.equals(user.getIsDelete())) {
                ResCode.userDBError.throwException("用户被禁用");
            }

            if (UserType.CUSTOMER.equals(user.getUserType())) {
                ResCode.SYS_FAIL.throwException("当前账号没有权限");
            }

            // 供应商验证服务有效期
            if (UserType.PROVIDER_MANAGER.equals(user.getUserType()) || UserType.PROVIDER_SALES.equals(user.getUserType())) {
                if (merchantAccountFacade.mustGet(MerchantAccount.builder().userId(user.getId()).build()).getEndTime().before(DateUtil.getDate())) {
                    ResCode.SYS_FAIL.throwException("账号已到期");
                }
            }
            // 门店验证服务有效期
            else if (UserType.STORE_MANAGER.equals(user.getUserType()) || UserType.STORE_SALES.equals(user.getUserType())) {
                if (merchantAccountFacade.mustGet(MerchantAccount.builder().userId(user.getId()).build()).getEndTime().before(DateUtil.getDate())) {
                    ResCode.SYS_FAIL.throwException("账号已到期");
                }
            }

            UserPassword userPassword = userPasswordFacade.mustGet(UserPassword.builder().userId(user.getId()).build());
            String encryptPwd = PwdUtils.encrypt(user.getId() + "", loginRequest.getPassword(), userPassword.getSalt());
            if (!userPassword.getPassword().equals(encryptPwd)) {
                ResCode.userNameOrPswError.throwException();
            }

            SessionUser sessionUser = SessionUser.from(user);

            setSessionUser(sessionUser);

            return new ResDto<>(sessionUser);
        } catch (PendingException e) {
            throw e;
        } catch (Exception e) {
            throw new PendingException(ResCode.SYS_FAIL.getCode(), ResCode.SYS_FAIL.getInfo(), e);
        }
    }

    /**
     * 模拟登录
     *
     * @param loginRequest
     * @return
     * @throws PendingException
     */
    @LogConfiguration(operType = OperType.LOGIN, operTargetType = OperTargetType.LOGIN, description = "模拟登录")
    @RequestMapping(value = "/simulateLogin.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<SessionUser> simulateLogin(@RequestBody LoginRequest loginRequest) throws PendingException {
        try {

            // 查询用户信息
            User user = userFacade.mustGet(User.builder().phone(loginRequest.getLoginName()).build());

            if (YesNoStatus.YES.equals(user.getIsDelete())) {
                ResCode.userDBError.throwException("用户被禁用");
            }

            SessionUser sessionUser = SessionUser.from(user);

            setSessionUser(sessionUser);

            return new ResDto<>(sessionUser);
        } catch (PendingException e) {
            throw e;
        } catch (Exception e) {
            throw new PendingException(ResCode.SYS_FAIL.getCode(), ResCode.SYS_FAIL.getInfo(), e);
        }
    }

    /**
     * 登出
     *
     * @param request request
     * @return 结果
     * @throws PendingException 异常
     */
    @LogConfiguration(operType = OperType.LOGOUT, operTargetType = OperTargetType.OUT, description = "退出登录")
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    public ResDto<?> logout(HttpServletRequest request) throws PendingException {
        HttpSession session = request.getSession();
        Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            String attrName = attributes.nextElement();
            session.removeAttribute(attrName);
        }
        return new ResDto<>(ResCode.success.getCode(), ResCode.success.getInfo());
    }

    /**
     * 发送验证码
     *
     * @param sendVerifyCodeRequest
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/sendVerifyCode.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> sendVerifyCode(@RequestBody SendVerifyCodeRequest sendVerifyCodeRequest, HttpServletRequest request) throws PendingException {

        userFacade.mustGet(User.builder().phone(sendVerifyCodeRequest.getPhone()).isDelete(YesNoStatus.NO).build());

        //发送验证码
        String verifyCode = smsProfFacade.sendVerifyCodeSms(VerifyCodeSmsSendRequest.builder().phone(sendVerifyCodeRequest.getPhone()).build());

        request.getSession().setAttribute(SessionConstant.VERIFY_CODE, verifyCode);

        return new ResDto<>();
    }

    /**
     * 找回密码
     *
     * @param resetPasswordRequest
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest, HttpServletRequest request) throws PendingException {

        try {
            User user = userFacade.mustGet(User.builder().phone(resetPasswordRequest.getPhone()).build());

            String verifyCode = (String) request.getSession().getAttribute(SessionConstant.VERIFY_CODE);
            if (StringUtil.isEmpty(verifyCode)) {
                return new ResDto<>(ResCode.paramNull.getCode(), "请先获取验证码");
            }
            if (!StringUtil.equals(resetPasswordRequest.getVertifyCode(), verifyCode)) {
                return new ResDto<>(ResCode.paramError.getCode(), "验证码不正确");
            }
            if (!StringUtil.equals(resetPasswordRequest.getNewPassword(), resetPasswordRequest.getConfirmPassword())) {
                return new ResDto<>(ResCode.paramError.getCode(), "两次输入的密码不一致");
            }

            UserPassword userPassword = userPasswordFacade.mustGet(UserPassword.builder().userId(user.getId()).build());
            // 对用户明文密码进行加密
            String psw = PwdUtils.encrypt(String.valueOf(user.getId()), resetPasswordRequest.getNewPassword(), userPassword.getSalt());

            userPasswordFacade.updateUserPassword(UserPassword.builder().id(userPassword.getId()).password(psw).build());

            //清除验证码信息
            request.getSession().removeAttribute(SessionConstant.VERIFY_CODE);

            return new ResDto<>();
        } catch (PendingException e) {
            throw e;
        } catch (Exception e) {
            throw new PendingException(ResCode.SYS_FAIL.getCode(), ResCode.SYS_FAIL.getInfo(), e);
        }
    }

    /**
     * 修改密码
     *
     * @param request request
     * @return 结果
     * @throws PendingException 异常
     */
    @RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> modifyPassword(@RequestBody ModifyPasswordRequest request) throws PendingException {
        try {

            // 比较两次输入的密码是否一致
            if (!StringUtil.equals(request.getNewPassword(), request.getConfirmPassword())) {
                return new ResDto<>(ResCode.paramError.getCode(), "两次输入的密码不一致");
            }

            // 校验用户是否存在
            userFacade.mustGet(User.builder().id(request.getUserId()).build());

            // 判断原密码是否正确
            UserPassword userPassword = userPasswordFacade.mustGet(UserPassword.builder().userId(request.getUserId()).build());

            String oldPsw = PwdUtils.encrypt(String.valueOf(request.getUserId()), request.getOldPassword(), userPassword.getSalt());
            if (!StringUtil.equals(userPassword.getPassword(), oldPsw)) {
                return new ResDto<>(ResCode.paramError.getCode(), "原密码错误");
            }

            // 修改密码
            String pwd = PwdUtils.encrypt(String.valueOf(request.getUserId()), request.getNewPassword(), userPassword.getSalt());
            userPasswordFacade.updateUserPassword(UserPassword.builder().id(userPassword.getId()).password(pwd).build());

            return new ResDto<>();
        } catch (PendingException e) {
            throw e;
        } catch (Exception e) {
            throw new PendingException(ResCode.SYS_FAIL.getCode(), ResCode.SYS_FAIL.getInfo(), e);
        }
    }
}
