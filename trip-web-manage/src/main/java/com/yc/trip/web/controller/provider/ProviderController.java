package com.yc.trip.web.controller.provider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yc.oss.constants.ResCode;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.provider.ProviderFacade;
import com.yc.trip.api.business.facade.provider.ProviderProfFacade;
import com.yc.trip.api.business.facade.user.UserFacade;
import com.yc.trip.api.business.item.provider.ProviderItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.provider.ProviderAddRequest;
import com.yc.trip.api.business.request.provider.ProviderPageRequest;
import com.yc.trip.api.business.request.provider.ProviderUpdateRequest;
import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.go.api.core.dto.ResDto;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProviderController
 *
 * @author: AsiQue
 * @date: 2019.04.22 17:42
 */
@RestController
@RequestMapping("/provider")
public class ProviderController extends AbstractBaseController {

    /**
     * 供应商服务
     */
    @Reference(version = "1.0.0")
    private ProviderFacade providerFacade;

    /**
     * 用户服务
     */
    @Reference(version = "1.0.0")
    private UserFacade userFacade;

    /**
     * 供应商高级服务
     */
    @Reference(version = "1.0.0")
    private ProviderProfFacade providerProfFacade;

    /**
     * 查询供应商列表分页
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryProviderPage.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<PageInfo<ProviderItem>> queryProviderPage(@RequestBody ProviderPageRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        return new ResDto<>(providerProfFacade.queryProviderPage(request));
    }

    /**
     * 新增供应商
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/addProvider.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<?> addProvider(@RequestBody ProviderAddRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        providerProfFacade.addProvider(request);

        return new ResDto<>();
    }

    /**
     * 更新供应商
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/updateProvider.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<?> updateProvider(@RequestBody ProviderUpdateRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        providerProfFacade.updateProvider(request);

        return new ResDto<>();
    }

    /**
     * 删除供应商
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/deleteProvider.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<?> deleteProvider(@RequestBody IdRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        return new ResDto<>(providerFacade.updateProvider(Provider.builder().id(request.getId()).isDelete(YesNoStatus.YES).build()));
    }
}
