package com.yc.trip.web.controller.brand;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yc.oss.constants.ResCode;
import com.yc.trip.api.business.dto.brand.Brand;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.brand.BrandFacade;
import com.yc.trip.api.business.request.brand.BrandAddRequest;
import com.yc.trip.api.business.request.brand.BrandUpdateRequest;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.PageRequest;
import com.yc.trip.api.business.request.store.BrandAddRequest;
import com.yc.trip.api.business.request.store.BrandUpdateRequest;
import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.go.api.core.dto.ResDto;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * BrandController
 *
 * @author: AsiQue
 * @date: 2019.04.23 23:57
 */
@RestController
@RequestMapping("/brand")
public class BrandController extends AbstractBaseController {

    /**
     * 品牌服务
     */
    @Reference(version = "1.0.0")
    private BrandFacade brandFacade;

    /**
     * 查询品牌列表分页
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryBrandPage.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<PageInfo<Brand>> queryBrandPage(@RequestBody PageRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        Brand query = BeanMapping.map(request, Brand.class);
        query.setOrderby("created_time desc");

        return new ResDto<>(brandFacade.queryBrandPage(query));
    }

    /**
     * 新增品牌
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/addBrand.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<?> addBrand(@RequestBody BrandAddRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        Brand toAdd = BeanMapping.map(request, Brand.class);
        toAdd.setCreatedBy(request.getUserId());
        toAdd.setIsDelete(YesNoStatus.NO);

        return new ResDto<>(brandFacade.addBrand(toAdd));
    }

    /**
     * 更新品牌
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/updateBrand.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<?> updateBrand(@RequestBody BrandUpdateRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        return new ResDto<>(brandFacade.updateBrand(BeanMapping.map(request, Brand.class)));
    }

    /**
     * 删除品牌
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/deleteBrand.do", method = RequestMethod.POST)
    @MvcValidate
    ResDto<?> deleteBrand(@RequestBody IdRequest request) throws PendingException {

        // 验证用户权限
        if (UserType.ADMIN.equals(getSessionUser().getUserType())) {
            ResCode.SYS_FAIL.throwException("当前用户没有权限");
        }

        return new ResDto<>(brandFacade.updateBrand(Brand.builder().id(request.getId()).isDelete(YesNoStatus.YES).build()));
    }
}
