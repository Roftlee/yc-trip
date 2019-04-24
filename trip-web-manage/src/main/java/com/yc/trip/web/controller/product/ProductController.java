package com.yc.trip.web.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.product.ProductFacade;
import com.yc.trip.api.business.facade.product.ProductProfFacade;
import com.yc.trip.api.business.facade.user.MerchantAccountFacade;
import com.yc.trip.api.business.item.product.ProductDetailItem;
import com.yc.trip.api.business.item.product.ProductItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.product.ProductAddRequest;
import com.yc.trip.api.business.request.product.ProductPageRequest;
import com.yc.trip.api.business.request.product.ProductUpdateRequest;
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
 * ProductController
 *
 * @author: AsiQue
 * @date: 2019.03.28 22:04
 */
@RestController
@RequestMapping("/product")
public class ProductController extends AbstractBaseController {

    @Reference(version = "1.0.0")
    private ProductFacade productFacade;// 产品服务

    @Reference(version = "1.0.0")
    private ProductProfFacade productProfFacade;// 产品高级服务

    @Reference(version = "1.0.0")
    private MerchantAccountFacade merchantAccountFacade;// 商户账号服务

    /**
     * 产品-查询产品列表分页
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryProductPage.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<PageInfo<ProductItem>> queryProductPage(@RequestBody ProductPageRequest request) throws PendingException {
        // 非超管用户需要设置供应商信息
        if (!UserType.ADMIN.equals(getSessionUser().getUserType())) {
            request.setProviderId(getSessionUser().getMerchantId());
        }

        return new ResDto<>(productProfFacade.queryProductPage(request));
    }

    /**
     * 产品-新增产品
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/addProduct.do", method = RequestMethod.POST)
    public ResDto<?> addProduct(@RequestBody ProductAddRequest request) throws PendingException {
        // 如果没有设置供应商信息，需要设置供应商信息
        if (request.getProviderId() == null || request.getProviderId() == 0) {
            request.setProviderId(getSessionUser().getMerchantId());
        }
        // 校验参数
        validateThrow(request);

        productProfFacade.addProduct(request);
        return new ResDto<>();
    }

    /**
     * 产品-获取产品详情
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/getProductDetail.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<ProductDetailItem> getProductDetail(@RequestBody IdRequest request) throws PendingException {

        productProfFacade.getBackstageProductDetail(request);
        return new ResDto<>();
    }

    /**
     * 产品-更新产品
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/updateProduct.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> updateProduct(@RequestBody ProductUpdateRequest request) throws PendingException {

        productProfFacade.updateProduct(request);
        return new ResDto<>();
    }

    /**
     * 产品-删除产品
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/deleteProduct.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> deleteProduct(@RequestBody IdRequest request) throws PendingException {

        productFacade.updateProduct(Product.builder().id(request.getId()).isDelete(YesNoStatus.YES).build());

        return new ResDto<>();
    }
}
