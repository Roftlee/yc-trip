package com.yc.trip.api.business.cache.brand;

import com.yc.trip.api.business.cache.AbstractBaseCustomCache;
import com.yc.trip.api.business.dto.brand.Brand;
import com.yc.trip.api.business.facade.brand.BrandFacade;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌缓存
 *
 * @author AsiQue
 * @since 2019/1/7 22:58
 */
@Service
public class BrandCache extends AbstractBaseCustomCache<Long, Brand> {

    @Autowired
    private BrandFacade brandFacade;// 产品服务

    @Override
    protected Integer getExpiredMinutes() {
        return 10;
    }

    @Override
    protected Brand query(Long brandId) throws PendingException {

        return brandFacade.mustGet(Brand.builder().id(brandId).build());
    }
}
