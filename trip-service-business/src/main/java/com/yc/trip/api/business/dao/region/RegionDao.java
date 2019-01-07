package com.yc.trip.api.business.dao.region;

import java.util.List;

import com.yc.trip.api.business.dto.region.Region;
import com.yc.trip.api.business.query.region.RegionQuery;

/**
 * 地区信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:29
 *
 */
public interface RegionDao {

    /**
     * 地区信息新增
     */
    void add(Region region);

    /**
     * 地区信息修改
     */
    void update(Region region);
    
    /**
     * 地区信息查询
     */
    Region get(RegionQuery regionQuery);

    /**
     * 地区信息列表查询
     */
    List<Region> queryList(RegionQuery regionQuery);

}