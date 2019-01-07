package com.yc.trip.api.business.dao.region;

import java.util.List;

import com.yc.trip.api.business.dto.region.RegionSort;
import com.yc.trip.api.business.query.region.RegionSortQuery;

/**
 * 地区分类信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:30
 *
 */
public interface RegionSortDao {

    /**
     * 地区分类信息新增
     */
    void add(RegionSort regionSort);

    /**
     * 地区分类信息修改
     */
    void update(RegionSort regionSort);
    
    /**
     * 地区分类信息查询
     */
    RegionSort get(RegionSortQuery regionSortQuery);

    /**
     * 地区分类信息列表查询
     */
    List<RegionSort> queryList(RegionSortQuery regionSortQuery);

}