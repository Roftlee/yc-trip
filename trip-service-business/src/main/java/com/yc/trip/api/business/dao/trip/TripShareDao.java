package com.yc.trip.api.business.dao.trip;

import java.util.List;

import com.yc.trip.api.business.bo.trip.TripShareDomain;

/**
 * 旅游分享信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:30
 *
 */
public interface TripShareDao {

    /**
     * 新增旅游分享信息
     */
    void addTripShare(TripShareDomain tripShareDomain);

    /**
     * 修改旅游分享信息
     */
    void updateTripShare(TripShareDomain tripShareDomain);
    
    /**
     * 查询旅游分享信息
     */
    TripShareDomain getTripShare(TripShareDomain tripShareDomain);

    /**
     * 查询旅游分享信息列表
     */
    List<TripShareDomain> queryTripShareList(TripShareDomain tripShareDomain);

}