package com.yc.trip.api.business.cache.region;

import com.yc.trip.api.business.cache.AbstractBaseCustomCache;
import com.yc.trip.api.business.dto.region.Region;
import com.yc.trip.api.business.facade.region.RegionFacade;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RegionCache
 *
 * @author: AsiQue
 * @date: 2019.04.01 21:32
 */
@Service
public class RegionCache extends AbstractBaseCustomCache<Long, Region> {

    @Autowired
    private RegionFacade regionFacade;// 地区服务

    @Override
    protected Integer getExpiredMinutes() {
        return 15;
    }

    @Override
    protected Region query(Long regionId) throws PendingException {
        return regionFacade.mustGet(Region.builder().id(regionId).build());
    }
}
