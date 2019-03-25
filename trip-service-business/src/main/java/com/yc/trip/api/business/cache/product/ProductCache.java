package com.yc.trip.api.business.cache.product;

import com.yc.trip.api.business.cache.AbstractBaseCustomCache;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.facade.product.ProductFacade;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品缓存
 *
 * @author AsiQue
 * @since 2019/1/7 22:58
 */
@Service
public class ProductCache extends AbstractBaseCustomCache<Long, Product> {

    @Autowired
    private ProductFacade productFacade;// 产品服务

    @Override
    protected Integer getExpiredMinutes() {
        return 10;
    }

    @Override
    protected Product query(Long productId) throws PendingException {

        return productFacade.mustGet(Product.builder().id(productId).build());
    }
}
