package com.yc.trip.api.business.dao.region;

import java.util.List;

import com.yc.trip.api.business.bo.region.RegionDomain;

/**
 * 地区信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:13
 *
 */
public interface RegionDao {

    /**
     * 新增地区信息
     */
    void addRegion(RegionDomain regionDomain);

    /**
     * 修改地区信息
     */
    void updateRegion(RegionDomain regionDomain);
    
    /**
     * 查询地区信息
     */
    RegionDomain getRegion(RegionDomain regionDomain);

    /**
     * 查询地区信息列表
     */
    List<RegionDomain> queryRegionList(RegionDomain regionDomain);

}