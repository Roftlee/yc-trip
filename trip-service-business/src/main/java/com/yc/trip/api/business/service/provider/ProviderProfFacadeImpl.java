package com.yc.trip.api.business.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.brand.Brand;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.dto.provider.ProviderBrandRelation;
import com.yc.trip.api.business.dto.user.MerchantAccount;
import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.enums.user.AccountType;
import com.yc.trip.api.business.enums.user.MerchantType;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.brand.BrandFacade;
import com.yc.trip.api.business.facade.provider.ProviderBrandRelationFacade;
import com.yc.trip.api.business.facade.provider.ProviderFacade;
import com.yc.trip.api.business.facade.provider.ProviderProfFacade;
import com.yc.trip.api.business.facade.user.MerchantAccountFacade;
import com.yc.trip.api.business.facade.user.UserFacade;
import com.yc.trip.api.business.item.provider.ProviderItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.provider.ProviderAddRequest;
import com.yc.trip.api.business.request.provider.ProviderPageRequest;
import com.yc.trip.api.business.request.provider.ProviderUpdateRequest;
import com.yc.trip.api.core.constants.R;
import com.yc.trip.api.core.enums.Sex;
import com.yc.trip.api.core.enums.YesNoStatus;
import org.apache.commons.collections.CollectionUtils;
import org.go.api.core.integration.AbstractDubboIntegrationService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.annotation.RpcClass;
import org.go.framework.base.annotation.RpcMethod;
import org.go.framework.base.transaction.GigoldTransactionTemplate;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.DateUtil;
import org.go.framework.util.common.DateUtils;
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

    /**
     * 事务模板
     */
    @Autowired
    private GigoldTransactionTemplate newTransactionTemplate;

    /**
     * 用户服务
     */
    @Autowired
    private UserFacade userFacade;

    /**
     * 商户账号信息
     */
    @Autowired
    private MerchantAccountFacade merchantAccountFacade;

    @Override
    @RpcMethod("查询供应商列表")
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

    @Override
    @RpcMethod("新增供应商")
    public void addProvider(ProviderAddRequest request) throws PendingException {

        newTransactionTemplate.execute(status -> {
            try {
                // 新增供应商信息
                Provider toAdd = BeanMapping.map(request, Provider.class);
                toAdd.setEndTime(DateUtil.addDays(DateUtil.getDate(), request.getPurchaseDates() + 1));
                Provider provider = providerFacade.addProvider(toAdd);

                // 新增供应商主账号用户信息
                User user = userFacade.addUser(User.builder()
                        .name(provider.getLinkMan())
                        .avatar(request.getAvatar())
                        .phone(provider.getLinkNum())
                        .userType(UserType.PROVIDER)
                        .isDelete(YesNoStatus.NO)
                        .build());
                // 新增供应商主账号信息
                merchantAccountFacade.addMerchantAccount(MerchantAccount.builder()
                        .userId(user.getId())
                        .merchantId(provider.getId())
                        .merchantType(MerchantType.PROVIDER)
                        .accountType(AccountType.PRIMARY)
                        .endTime(provider.getEndTime())
                        .build());

                // 处理品牌关联
                for (Long brandId : request.getBrandIds()) {
                    providerBrandRelationFacade.addProviderBrandRelation(ProviderBrandRelation.builder()
                            .providerId(provider.getId())
                            .brandId(brandId)
                            .build());
                }

                // TODO: 处理子账号

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
    @RpcMethod("更新供应商")
    public void updateProvider(ProviderUpdateRequest request) throws PendingException {
        newTransactionTemplate.execute(status -> {
            try {
                // 更新供应商信息
                Provider toUpdate = BeanMapping.map(request, Provider.class);
                Provider provider = providerFacade.updateProvider(toUpdate);

                // 更新供应商主账号用户信息
                User user = userFacade.updateUser(User.builder()
                        .name(request.getLinkMan())
                        .avatar(request.getAvatar())
                        .phone(request.getLinkNum())
                        .userType(UserType.PROVIDER)
                        .isDelete(YesNoStatus.NO)
                        .build());
                // 删除品牌关联
                providerBrandRelationFacade.deleteProviderBrandRelation(IdRequest.builder().id(request.getId()).build());
                // 处理品牌关联
                for (Long brandId : request.getBrandIds()) {
                    providerBrandRelationFacade.addProviderBrandRelation(ProviderBrandRelation.builder()
                            .providerId(provider.getId())
                            .brandId(brandId)
                            .build());
                }

                // TODO: 处理子账号

                return true;
            } catch (Exception ex) {
                // 回滚事务
                status.setRollbackOnly();
                // 处理异常
                throw toRuntime(ex);
            }
        });
    }
}
