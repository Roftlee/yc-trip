package com.yc.trip.web.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.ProductSort;
import com.yc.trip.api.business.facade.product.ProductFacade;
import com.yc.trip.api.business.facade.product.ProductProfFacade;
import com.yc.trip.api.business.facade.product.ProductSortFacade;
import com.yc.trip.api.business.facade.sales.SpecialOfferProfFacade;
import com.yc.trip.api.business.item.product.ProductItem;
import com.yc.trip.api.business.item.sales.SpecialOfferItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.KeywordsPageRequest;
import com.yc.trip.api.business.request.common.PageRequest;
import com.yc.trip.api.business.request.product.ProductPageRequest;
import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.go.api.core.dto.ResDto;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 产品服务
 *
 * @author AsiQue
 * @since 2019/1/7 22:02
 */
@RestController
@RequestMapping("/product")
public class ProductController extends AbstractBaseController {

    @Reference(version = "1.0.0")
    private ProductFacade productFacade;// 产品服务

    @Reference(version = "1.0.0")
    private ProductProfFacade productProfFacade;// 产品高级服务

    @Reference(version = "1.0.0")
    private ProductSortFacade productSortFacade;// 产品分类服务

    @Reference(version = "1.0.0")
    private SpecialOfferProfFacade specialOfferProfFacade;// 优惠活动高级服务

    /**
     * 首页-产品列表分页查询
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryProductPage.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<PageInfo<ProductItem>> querySuggestedProductPage(@RequestBody ProductPageRequest request) throws PendingException {

        return new ResDto<>(productProfFacade.queryProductPage(request));
    }

    /**
     * 首页-低价超值产品列表分页查询
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryOverbalanceProductPage.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<PageInfo<ProductItem>> queryOverbalanceProductPage(@RequestBody PageRequest request) throws PendingException {

        // TODO：低价超值产品查询

        return new ResDto<>();
    }

    /**
     * 首页-猜你喜欢产品列表分页查询
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/querySuggestedProductPage.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<PageInfo<ProductItem>> querySuggestedProductPage(@RequestBody PageRequest request) throws PendingException {

        return new ResDto<>(productProfFacade.querySuggestedProductPage(request));
    }

    /**
     * 首页-热门优惠列表分页查询
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/querySpecialOfferProduct.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<PageInfo<SpecialOfferItem>> querySpecialOfferProduct(@RequestBody KeywordsPageRequest request) throws PendingException {

        return new ResDto<>(specialOfferProfFacade.querySpecialOfferPage(request));
    }

    /**
     * 获取产品详情
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/getProductDetail.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<ProductItem> getProductDetail(@RequestBody IdRequest request) throws PendingException {

        return new ResDto<>(productProfFacade.getProductDetail(request));
    }

    /**
     * 查询产品分类列表
     *
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryProductSortList.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<ProductSort>> queryProductSortList() throws PendingException {

        return new ResDto<>(productSortFacade.queryProductSortList(ProductSort.builder().isDelete(YesNoStatus.NO).build()));
    }
}
