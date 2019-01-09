package com.yc.trip.web.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.oss.constants.ResCode;
import com.yc.trip.api.business.dto.product.ProductSort;
import com.yc.trip.api.business.facade.product.ProductSortFacade;
import com.yc.trip.api.business.query.product.ProductSortQuery;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.KeywordsRequest;
import com.yc.trip.api.business.request.product.ProductSortAddRequest;
import com.yc.trip.api.business.request.product.ProductSortUpdateRequest;
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

import java.util.List;

/**
 * 产品分类服务
 *
 * @author AsiQue
 * @since 2019/1/9 19:45
 */
@RestController
@RequestMapping("/product")
public class ProductSortController extends AbstractBaseController {

    @Reference(version = "1.0.0")
    private ProductSortFacade productSortFacade;// 产品分类服务

    /**
     * 查询产品分类列表
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryProductSortList.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<ProductSort>> queryProductSortList(@RequestBody KeywordsRequest request) throws PendingException {

        return new ResDto<>(productSortFacade.queryList(ProductSortQuery.builder().keywords(request.getKeywords()).isDelete(YesNoStatus.NO).build()));
    }

    /**
     * 新增产品分类
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/addProductSort.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<ProductSort> addProductSort(@RequestBody ProductSortAddRequest request) throws PendingException {

        // 产品分类重名检测
        ProductSort productSort = productSortFacade.get(ProductSortQuery.builder().name(request.getName()).build());
        if (productSort != null) {
            ResCode.paramError.throwException("产品分类已存在");
        }

        return new ResDto<>(productSortFacade.add(BeanMapping.map(request, ProductSort.class)));
    }

    /**
     * 获取产品分类详情
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/getProductSort.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<ProductSort> getProductSort(@RequestBody IdRequest request) throws PendingException {

        return new ResDto<>(productSortFacade.mustGet(ProductSortQuery.builder().id(request.getId()).build()));
    }

    /**
     * 更新产品分类
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/updateProductSort.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> updateProductSort(@RequestBody ProductSortUpdateRequest request) throws PendingException {

        // 产品分类重名检测
        ProductSort productSort = productSortFacade.get(ProductSortQuery.builder().name(request.getName()).build());
        if (productSort != null && !request.getId().equals(productSort.getId())) {
            ResCode.paramError.throwException("产品分类已存在");
        }

        // 更新记录
        productSortFacade.update(BeanMapping.map(request, ProductSort.class));

        return new ResDto<>();
    }

    /**
     * 删除产品分类
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/deleteProductSort.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> deleteProductSort(@RequestBody IdRequest request) throws PendingException {

        // 删除记录
        productSortFacade.update(ProductSort.builder().id(request.getId()).isDelete(YesNoStatus.YES).build());

        return new ResDto<>();
    }
}
