package com.yc.trip.api.business.dao.region;

import java.util.List;

import com.yc.trip.api.business.bo.region.RegionSortDomain;

/**
 * 地区分类信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:14
 *
 */
public interface RegionSortDao {

    /**
     * 新增地区分类信息
     */
    void addRegionSort(RegionSortDomain regionSortDomain);

    /**
     * 修改地区分类信息
     */
    void updateRegionSort(RegionSortDomain regionSortDomain);
    
    /**
     * 查询地区分类信息
     */
    RegionSortDomain getRegionSort(RegionSortDomain regionSortDomain);

    /**
     * 查询地区分类信息列表
     */
    List<RegionSortDomain> queryRegionSortList(RegionSortDomain regionSortDomain);

}