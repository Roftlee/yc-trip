package com.yc.trip.api.business.facade.product;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.item.product.ProductItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.PageRequest;
import org.go.framework.core.exception.PendingException;

/**
 * 产品高级服务
 *
 * @author AsiQue
 * @since 2019/1/7 22:45
 */
public interface ProductProfFacade {

    /**
     * 查询猜你喜欢产品列表分页
     *
     * @param request
     * @return
     * @throws PendingException
     */
    PageInfo<ProductItem> querySuggestedProductPage(PageRequest request) throws PendingException;

    /**
     * 获取产品详情
     *
     * @param request
     * @return
     * @throws PendingException
     */
    ProductItem getProductDetail(IdRequest request) throws PendingException;
}
