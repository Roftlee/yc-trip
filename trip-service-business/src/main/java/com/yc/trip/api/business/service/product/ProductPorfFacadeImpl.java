package com.yc.trip.api.business.service.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.cache.product.ProductCache;
import com.yc.trip.api.business.cache.region.RegionCache;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.facade.product.ProductFacade;
import com.yc.trip.api.business.facade.product.ProductProfFacade;
import com.yc.trip.api.business.item.product.ProductDetailItem;
import com.yc.trip.api.business.item.product.ProductItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.PageRequest;
import com.yc.trip.api.business.request.product.ProductAddRequest;
import com.yc.trip.api.business.request.product.ProductPageRequest;
import com.yc.trip.api.business.request.product.ProductUpdateRequest;
import com.yc.trip.api.core.constants.R;
import com.yc.trip.api.core.enums.YesNoStatus;
import org.go.api.core.integration.AbstractDubboIntegrationService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.annotation.RpcClass;
import org.go.framework.base.annotation.RpcMethod;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

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

    @Autowired
    private ProductCache productCache;// 产品缓存

    @Autowired
    private RegionCache regionCache;// 地区缓存

    @Override
    @RpcMethod("查询猜你喜欢产品列表分页")
    public PageInfo<ProductItem> querySuggestedProductPage(PageRequest request) throws PendingException {

        PageInfo<Product> pageInfo = productFacade.queryProductListRandomPage(request);

        return BeanMapping.mapPage(pageInfo, this::transferToProductItem);
    }

    @Override
    @RpcMethod("获取产品详情")
    public ProductItem getProductDetail(IdRequest request) throws PendingException {

        return transferToProductItem(productFacade.mustGet(Product.builder().id(request.getId()).build()));
    }

    @Override
    @RpcMethod("查询产品列表分页")
    public PageInfo<ProductItem> queryProductPage(ProductPageRequest request) throws PendingException {

        Product query = BeanMapping.map(request, Product.class);
        query.setIsDelete(YesNoStatus.NO);
        query.setOrderby("created_time desc");
        PageInfo<Product> pageInfo = productFacade.queryProductPage(query);

        return BeanMapping.mapPage(pageInfo, this::transferToProductItem);
    }

    @Override
    @RpcMethod("新增产品")
    public void addProduct(ProductAddRequest request) throws PendingException {

        Product product = BeanMapping.map(request, Product.class);
        product.setRegionSortId(regionCache.get(request.getRegionId()).getRegionSortId());
        product.setDescription(request.getName());
        product.setIsDelete(YesNoStatus.NO);

        productFacade.addProduct(product);
    }

    @Override
    @RpcMethod("获取管理后台产品详情")
    public ProductDetailItem getBackstageProductDetail(IdRequest request) throws PendingException {

        Product product = productFacade.mustGet(Product.builder().id(request.getId()).build());

        return BeanMapping.map(product, ProductDetailItem.class);
    }

    @Override
    @RpcMethod("更新产品")
    public void updateProduct(ProductUpdateRequest request) throws PendingException {

        productFacade.updateProduct(BeanMapping.map(request, Product.class));

        // 更新产品缓存
        productCache.clearCache(request.getId());
    }

    /**
     * 转换成ProductItem
     *
     * @param product
     * @return
     */
    private ProductItem transferToProductItem(Product product) {

        ProductItem item = BeanMapping.map(product, ProductItem.class);
        // 计算差价
        item.setDiffPrice(new BigDecimal(item.getPrice() - item.getStrikePrice()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        return item;
    }

}
