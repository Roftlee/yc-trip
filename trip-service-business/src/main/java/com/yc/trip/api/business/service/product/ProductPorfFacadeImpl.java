package com.yc.trip.api.business.service.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.facade.product.ProductFacade;
import com.yc.trip.api.business.facade.product.ProductProfFacade;
import com.yc.trip.api.business.item.product.ProductItem;
import com.yc.trip.api.business.request.common.PageRequest;
import com.yc.trip.api.core.constants.R;
import org.go.api.core.integration.AbstractDubboIntegrationService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.annotation.RpcClass;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 产品高级服务
 *
 * @author AsiQue
 * @since 2019/1/7 22:46
 */
@RpcClass(R.productPorfFacade)
@Service(version = "1.0.0")
public class ProductPorfFacadeImpl extends AbstractDubboIntegrationService implements ProductProfFacade {

    @Autowired
    private ProductFacade productFacade;// 产品服务

    @Override
    public PageInfo<ProductItem> querySuggestedProductPage(PageRequest request) throws PendingException {

        PageInfo<Product> pageInfo = productFacade.queryProductListRandomPage(request);

        return BeanMapping.mapPage(pageInfo, this::transferToProductItem);
    }

    /**
     * 转换成ProductItem
     *
     * @param product
     * @return
     */
    private ProductItem transferToProductItem(Product product) {

        ProductItem item = BeanMapping.map(product, ProductItem.class);

        return item;
    }
}
