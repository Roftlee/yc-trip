package com.yc.trip.api.business.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.brand.Brand;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.dto.provider.ProviderBrandRelation;
import com.yc.trip.api.business.facade.brand.BrandFacade;
import com.yc.trip.api.business.facade.provider.ProviderBrandRelationFacade;
import com.yc.trip.api.business.facade.provider.ProviderFacade;
import com.yc.trip.api.business.facade.provider.ProviderProfFacade;
import com.yc.trip.api.business.item.provider.ProviderItem;
import com.yc.trip.api.business.request.provider.ProviderPageRequest;
import com.yc.trip.api.core.constants.R;
import org.go.api.core.integration.AbstractDubboIntegrationService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.annotation.RpcClass;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 供应商信息相关接口实现
 *
 * @author My-Toolkits
 * @since 2019-03-21 22:09
 */
@RpcClass(R.providerProfFacade)
@Service(version = "1.0.0")
public class ProviderProfFacadeImpl extends AbstractDubboIntegrationService implements ProviderProfFacade {

    /**
     * 供应商服务
     */
    @Autowired
    private ProviderFacade providerFacade;

    /**
     * 供应商品牌关联服务
     */
    @Autowired
    private ProviderBrandRelationFacade providerBrandRelationFacade;

    /**
     * 品牌服务
     */
    @Autowired
    private BrandFacade brandFacade;

    @Override
    public PageInfo<ProviderItem> queryProviderPage(ProviderPageRequest request) throws PendingException {

        PageInfo<Provider> pageInfo = providerFacade.queryProviderPage(BeanMapping.map(request, Provider.class));

        return BeanMapping.mapPage(pageInfo, e -> {
            try {
                ProviderItem item = BeanMapping.map(e, ProviderItem.class);

                // 查询供应商品牌列表
                List<Long> brandIds = providerBrandRelationFacade.queryProviderBrandRelationList(ProviderBrandRelation.builder()
                        .providerId(item.getId())
                        .build())
                        .stream().map(ProviderBrandRelation::getBrandId)
                        .collect(Collectors.toList());
                item.setBrandList(brandFacade.queryBrandList(Brand.builder()
                        .ids(brandIds)
                        .build()));

                return item;
            } catch (Exception ex) {
                throw toRuntime(ex);
            }
        });
    }
}
