package com.yc.trip.web.controller.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.trip.api.business.dto.user.MerchantAccount;
import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.dto.user.UserPassword;
import com.yc.trip.api.business.enums.user.MerchantType;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.user.MerchantAccountFacade;
import com.yc.trip.api.business.facade.user.UserFacade;
import com.yc.trip.api.business.facade.user.UserPasswordFacade;
import com.yc.trip.api.business.request.auth.LoginRequest;
import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.core.enums.OperTargetType;
import com.yc.trip.api.core.enums.OperType;
import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.api.core.util.PwdUtils;
import com.yc.trip.web.annotation.LogConfiguration;
import com.yc.trip.web.bean.session.SessionUser;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.go.api.core.dto.ResDto;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.cache.CacheService;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.DateUtil;
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

            // 供应商验证服务有效期
            if (UserType.PROVIDER.equals(user.getUserType()) || UserType.PROVIDER_SALES.equals(user.getUserType())) {
                if (merchantAccountFacade.mustGet(MerchantAccount.builder().userId(user.getId()).build()).getEndTime().before(DateUtil.getDate())) {
                    ResCode.SYS_FAIL.throwException("账号已到期");
                }
            }
            // 门店验证服务有效期
            else if (UserType.STORE_MANGER.equals(user.getUserType()) || UserType.STORE_SALES.equals(user.getUserType())) {
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
}
