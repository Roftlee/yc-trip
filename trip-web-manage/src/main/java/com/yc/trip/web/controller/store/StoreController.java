package com.yc.trip.web.controller.store;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yc.oss.constants.ResCode;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.store.StoreFacade;
import com.yc.trip.api.business.facade.store.StoreProfFacade;
import com.yc.trip.api.business.facade.user.UserFacade;
import com.yc.trip.api.business.item.provider.ProviderItem;
import com.yc.trip.api.business.item.store.StoreItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.store.StoreAddRequest;
import com.yc.trip.api.business.request.store.StorePageRequest;
import com.yc.trip.api.business.request.store.StoreUpdateRequest;
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
 * StoreController
 *
 * @author: AsiQue
 * @date: 2019.04.23 21:57
 */
@RestController
@RequestMapping("/store")
public class StoreController extends AbstractBaseController {

    /**
     * 门店服务
     */
    @Reference(version = "1.0.0")
    private StoreFacade storeFacade;

    /**
     * 用户服务
     */
    @Reference(version = "1.0.0")
    private UserFacade userFacade;

    /**
     * 门店高级服务
     */
    @Reference(version = "1.0.0")
    private StoreProfFacade storeProfFacade;

    /**
     * 查询门店列表分页
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryStorePage.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<PageInfo<StoreItem>> queryStorePage(@RequestBody StorePageRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        return new ResDto<>(storeProfFacade.queryStorePage(request));
    }

    /**
     * 新增门店
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/addStore.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<?> addStore(@RequestBody StoreAddRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        storeProfFacade.addStore(request);

        return new ResDto<>();
    }

    /**
     * 更新门店
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/updateStore.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<?> updateStore(@RequestBody StoreUpdateRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        storeProfFacade.updateStore(request);

        return new ResDto<>();
    }

    /**
     * 删除门店
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/deleteStore.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<?> deleteStore(@RequestBody IdRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        return new ResDto<>(storeFacade.updateStore(Store.builder().id(request.getId()).isDelete(YesNoStatus.YES).build()));
    }
}
