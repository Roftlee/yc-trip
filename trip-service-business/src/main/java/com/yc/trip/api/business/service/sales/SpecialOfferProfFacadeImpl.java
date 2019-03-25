package com.yc.trip.api.business.service.sales;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yc.trip.api.business.cache.product.ProductCache;
import com.yc.trip.api.business.cache.provider.ProviderCache;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.dto.sales.SpecialOffer;
import com.yc.trip.api.business.dto.sales.SpecialOfferProduct;
import com.yc.trip.api.business.facade.sales.SpecialOfferFacade;
import com.yc.trip.api.business.facade.sales.SpecialOfferProductFacade;
import com.yc.trip.api.business.facade.sales.SpecialOfferProfFacade;
import com.yc.trip.api.business.item.sales.SpecialOfferItem;
import com.yc.trip.api.business.item.sales.SpecialOfferProductItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.KeywordsPageRequest;
import com.yc.trip.api.business.request.sales.SpecialOfferAddRequest;
import com.yc.trip.api.business.request.sales.SpecialOfferUpdateRequest;
import com.yc.trip.api.core.constants.R;
import com.yc.trip.api.core.enums.YesNoStatus;
import org.go.api.core.integration.AbstractDubboIntegrationService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.annotation.RpcClass;
import org.go.framework.base.annotation.RpcMethod;
import org.go.framework.base.transaction.GigoldTransactionTemplate;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 优惠活动高级服务
 *
 * @author AsiQue
 * @since 2019/1/9 21:45
 */
@RpcClass(R.specialOfferProfFacade)
@Service(version = "1.0.0")
public class SpecialOfferProfFacadeImpl extends AbstractDubboIntegrationService implements SpecialOfferProfFacade {

    @Autowired
    private SpecialOfferFacade specialOfferFacade;// 优惠活动服务

    @Autowired
    private SpecialOfferProductFacade specialOfferProductFacade;// 优惠活动产品服务

    @Autowired
    private ProductCache productCache;// 产品缓存

    @Autowired
    private ProviderCache providerCache;// 供应商缓存

    @Autowired
    private GigoldTransactionTemplate newTransactionTemplate;// 事务模板

    @Override
    @RpcMethod("查询优惠活动列表")
    public PageInfo<SpecialOfferItem> querySpecialOfferPage(KeywordsPageRequest request) throws PendingException {
        // 查询可用优惠活动
        PageInfo<SpecialOffer> pageInfo = specialOfferFacade.querySpecialOfferPage(SpecialOffer.builder().isDelete(YesNoStatus.NO).build());

        return BeanMapping.mapPage(pageInfo, this::transferToSpecialOfferItem);
    }

    @Override
    @RpcMethod("新增优惠活动")
    public SpecialOffer addSpecialOffer(SpecialOfferAddRequest request) throws PendingException {

        return newTransactionTemplate.execute(status -> {
            try {
                // 新增优惠活动记录
                SpecialOffer newSpecialOffer = specialOfferFacade.addSpecialOffer(BeanMapping.map(request, SpecialOffer.class));

                // 新增优惠活动产品
                for (Long productId : request.getProductIds()) {
                    specialOfferProductFacade.addSpecialOfferProduct(SpecialOfferProduct.builder()
                            .specialOfferId(newSpecialOffer.getId())
                            .productId(productId)
                            .build());
                }

                return newSpecialOffer;

            } catch (Exception ex) {
                // 回滚事务
                status.setRollbackOnly();
                // 处理异常
                throw toRuntime(ex);
            }
        });
    }

    @Override
    @RpcMethod("获取优惠活动详情")
    public SpecialOfferItem getSpecialOffer(IdRequest request) throws PendingException {
        return transferToSpecialOfferItem(specialOfferFacade.mustGet(SpecialOffer.builder().id(request.getId()).build()));
    }

    @Override
    @RpcMethod("更新优惠活动")
    public void updateSpecialOffer(SpecialOfferUpdateRequest request) throws PendingException {
        newTransactionTemplate.execute(status -> {
            try {
                // 更新优惠活动记录
                specialOfferFacade.updateSpecialOffer(BeanMapping.map(request, SpecialOffer.class));

                // 删除关联产品
                specialOfferProductFacade.deleteSpecialOfferProduct(IdRequest.builder().id(request.getId()).build());

                // 新增优惠活动产品
                for (Long productId : request.getProductIds()) {
                    specialOfferProductFacade.addSpecialOfferProduct(SpecialOfferProduct.builder()
                            .specialOfferId(request.getId())
                            .productId(productId)
                            .build());
                }

                return true;

            } catch (Exception ex) {
                // 回滚事务
                status.setRollbackOnly();
                // 处理异常
                throw toRuntime(ex);
            }
        });
    }

    @Override
    @RpcMethod("删除优惠活动")
    public void deleteSpecialOffer(IdRequest request) throws PendingException {
        newTransactionTemplate.execute(status -> {
            try {

                // 设置删除
                specialOfferFacade.updateSpecialOffer(SpecialOffer.builder().id(request.getId()).isDelete(YesNoStatus.YES).build());

                // 删除关联产品
                specialOfferProductFacade.deleteSpecialOfferProduct(request);

                return true;

            } catch (Exception ex) {
                // 回滚事务
                status.setRollbackOnly();
                // 处理异常
                throw toRuntime(ex);
            }
        });
    }

    /**
     * 转换成SpecialOfferItem
     *
     * @param specialOffer
     * @return
     */
    private SpecialOfferItem transferToSpecialOfferItem(SpecialOffer specialOffer) {
        // 查询优惠活动产品列表
        try {
            SpecialOfferItem item = BeanMapping.map(specialOffer, SpecialOfferItem.class);

            // 优惠活动产品列表
            List<SpecialOfferProductItem> productItems = Lists.newArrayList();

            specialOfferProductFacade.querySpecialOfferProductList(SpecialOfferProduct.builder().specialOfferId(specialOffer.getId()).build())
                    .forEach(p -> {
                        // 查询产品信息
                        Product product = productCache.get(p.getProductId());
                        // 优惠活动产品Item
                        SpecialOfferProductItem productItem = BeanMapping.map(product, SpecialOfferProductItem.class);

                        // 产品供应商信息
                        productItem.setProviderName(providerCache.get(product.getProviderId()).getProviderName());

                        productItems.add(productItem);
                    });

            item.setSpecialOfferProductItems(productItems);

            return item;
        } catch (PendingException e) {
            // 转换成运行时异常
            throw toRuntime(e);
        }
    }
}
