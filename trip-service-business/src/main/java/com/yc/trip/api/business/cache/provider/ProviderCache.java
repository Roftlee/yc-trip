package com.yc.trip.api.business.cache.provider;

import com.yc.trip.api.business.cache.AbstractBaseCustomCache;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.facade.provider.ProviderFacade;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 供应商缓存
 *
 * @author AsiQue
 * @since 2019/1/9 21:29
 */
@Service
public class ProviderCache extends AbstractBaseCustomCache<Long, Provider> {

    @Autowired
    private ProviderFacade providerFacade;// 供应商服务

    @Override
    protected Integer getExpiredMinutes() {
        return 15;
    }

    @Override
    protected Provider query(Long providerId) throws PendingException {
        return providerFacade.mustGet(Provider.builder().id(providerId).build());
    }
}
