package com.yc.trip.api.business.service.store;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.bo.store.StoreDomain;
import com.yc.trip.api.business.cache.brand.BrandCache;
import com.yc.trip.api.business.dao.store.StoreDao;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.dto.user.MerchantAccount;
import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.enums.user.AccountType;
import com.yc.trip.api.business.enums.user.MerchantType;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.store.StoreFacade;
import com.yc.trip.api.business.facade.store.StoreProfFacade;
import com.yc.trip.api.business.facade.user.MerchantAccountFacade;
import com.yc.trip.api.business.facade.user.UserFacade;
import com.yc.trip.api.business.item.store.StoreItem;
import com.yc.trip.api.business.request.store.StoreAddRequest;
import com.yc.trip.api.business.request.store.StorePageRequest;
import com.yc.trip.api.business.request.store.StoreUpdateRequest;
import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.core.enums.YesNoStatus;
import org.go.api.core.integration.AbstractDubboIntegrationService;
import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.transaction.GigoldTransactionTemplate;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.DateUtil;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 门店信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:22
 */
@Service(version = "1.0.0")
public class StoreProfFacadeImpl extends AbstractDubboIntegrationService implements StoreProfFacade {

    /**
     * 门店服务
     */
    @Autowired
    private StoreFacade storeFacade;

    /**
     * 品牌缓存
     */
    @Autowired
    private BrandCache brandCache;

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

    /**
     * 新增门店信息
     * 
     * @throws PendingException
     */
    @Override
    public void addStore(StoreAddRequest request) throws PendingException {
        newTransactionTemplate.execute(status -> {
            try {
                // 新增门店信息
                Store toAdd = BeanMapping.map(request, Store.class);
                toAdd.setEndTime(DateUtil.addDays(DateUtil.getDate(), request.getPurchaseDates() + 1));
                toAdd.setIsDelete(YesNoStatus.NO);
                Store store = storeFacade.addStore(toAdd);

                // 新增门店主账号
                // 查询手机号是否已有用户
                User user = userFacade.getUser(User.builder().phone(request.getLinkNum()).build());
                // 用户不存在时，新增用户
                if (user == null) {
                    user = userFacade.addUser(User.builder()
                            .avatar(request.getAvatar())
                            .phone(request.getLinkNum())
                            .name(request.getLinkMan())
                            .userType(UserType.STORE_MANAGER)
                            .isDelete(YesNoStatus.NO)
                            .build());
                } else {// 用户存在时，更新用户信息
                    userFacade.updateUser(User.builder().id(user.getId()).userType(UserType.STORE_MANAGER).build());
                }

                // 新增门店主账号
                merchantAccountFacade.addMerchantAccount(MerchantAccount.builder()
                        .merchantType(MerchantType.STORE)
                        .endTime(store.getEndTime())
                        .userId(user.getId())
                        .merchantId(store.getId())
                        .accountType(AccountType.PRIMARY)
                        .isDelete(YesNoStatus.NO)
                        .build());

                return true;
            } catch (Exception ex) {
                // 回滚
                status.setRollbackOnly();
                // 处理异常
                throw toRuntime(ex);
            }
        });
    }

    /**
     * 修改门店信息
     * 
     * @throws PendingException
     */
    @Override
    public void updateStore(StoreUpdateRequest request) throws PendingException {
        newTransactionTemplate.execute(status -> {
            try {
                // 更新门店信息
                storeFacade.updateStore(BeanMapping.map(request, Store.class));

                // 查询门店主账号
                MerchantAccount primaryAccount = merchantAccountFacade.mustGet(MerchantAccount.builder()
                        .merchantId(request.getId())
                        .merchantType(MerchantType.STORE)
                        .accountType(AccountType.PRIMARY)
                        .isDelete(YesNoStatus.NO)
                        .build());
                //更新用户信息
                userFacade.updateUser(User.builder()
                        .id(primaryAccount.getUserId())
                        .name(request.getLinkMan())
                        .avatar(request.getAvatar())
                        .phone(request.getLinkNum())
                        .build());

                return true;
            } catch (Exception ex) {
                // 回滚
                status.setRollbackOnly();
                // 处理异常
                throw toRuntime(ex);
            }
        });
    }

    /**
     * 查询门店信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<StoreItem> queryStorePage(StorePageRequest request) throws PendingException {

        PageInfo<Store> pageInfo = storeFacade.queryStorePage(BeanMapping.map(request, Store.class));

        return BeanMapping.mapPage(pageInfo, e -> {
            StoreItem item = BeanMapping.map(e, StoreItem.class);
            item.setBrandName(brandCache.get(e.getBrandId()).getName());

            return item;
        });
    }

}
