package com.yc.trip.api.business.service.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.dto.sales.SpecialOffer;
import com.yc.trip.api.business.facade.product.ProductFacade;
import com.yc.trip.api.business.facade.product.ProductProfFacade;
import com.yc.trip.api.business.facade.sales.SpecialOfferFacade;
import com.yc.trip.api.business.facade.sales.SpecialOfferProductFacade;
import com.yc.trip.api.business.item.product.ProductItem;
import com.yc.trip.api.business.item.product.SpecialOfferItem;
import com.yc.trip.api.business.item.product.SpecialOfferProductItem;
import com.yc.trip.api.business.query.product.ProductQuery;
import com.yc.trip.api.business.query.sales.SpecialOfferProductQuery;
import com.yc.trip.api.business.query.sales.SpecialOfferQuery;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.PageRequest;
import com.yc.trip.api.core.constants.R;
import com.yc.trip.api.core.enums.YesNoStatus;
import org.go.api.core.integration.AbstractDubboIntegrationService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.annotation.RpcClass;
import org.go.framework.base.annotation.RpcMethod;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

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
    private SpecialOfferFacade specialOfferFacade;// 优惠活动服务

    @Autowired
    private SpecialOfferProductFacade specialOfferProductFacade;// 优惠活动产品服务

    @Override
    @RpcMethod("查询猜你喜欢产品列表分页")
    public PageInfo<ProductItem> querySuggestedProductPage(PageRequest request) throws PendingException {

        PageInfo<Product> pageInfo = productFacade.queryProductListRandomPage(request);

        return BeanMapping.mapPage(pageInfo, this::transferToProductItem);
    }

    @Override
    @RpcMethod("查询优惠活动产品")
    public PageInfo<SpecialOfferItem> querySpecialOfferProduct(PageRequest request) throws PendingException {

        // 查询可用优惠活动
        PageInfo<SpecialOffer> pageInfo = specialOfferFacade.queryPage(SpecialOfferQuery.builder().isDelete(YesNoStatus.NO).build());

        return BeanMapping.mapPage(pageInfo, specialOffer -> {

            // 查询优惠活动产品列表
            try {
                SpecialOfferItem item = BeanMapping.map(specialOffer, SpecialOfferItem.class);

                // 优惠活动产品列表
                List<SpecialOfferProductItem> productItems = Lists.newArrayList();

                specialOfferProductFacade.queryList(SpecialOfferProductQuery.builder().specialOfferId(specialOffer.getId()).build())
                        .forEach(t -> productItems.add(BeanMapping.map(t, SpecialOfferProductItem.class)));

                item.setSpecialOfferProductItems(productItems);

                return item;
            } catch (PendingException e) {
                // 转换成运行时异常
                throw toRuntime(e);
            }
        });
    }

    @Override
    @RpcMethod("获取产品详情")
    public ProductItem getProductDetail(IdRequest request) throws PendingException {

        return transferToProductItem(productFacade.mustGet(ProductQuery.builder().id(request.getId()).build()));
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
