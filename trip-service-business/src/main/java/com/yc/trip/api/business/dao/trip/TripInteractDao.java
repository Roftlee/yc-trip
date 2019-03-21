package com.yc.trip.api.business.dao.trip;

import java.util.List;

import com.yc.trip.api.business.bo.trip.TripInteractDomain;

/**
 * 旅游互动信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:29
 *
 */
public interface TripInteractDao {

    /**
     * 新增旅游互动信息
     */
    void addTripInteract(TripInteractDomain tripInteractDomain);

    /**
     * 修改旅游互动信息
     */
    void updateTripInteract(TripInteractDomain tripInteractDomain);
    
    /**
     * 查询旅游互动信息
     */
    TripInteractDomain getTripInteract(TripInteractDomain tripInteractDomain);

    /**
     * 查询旅游互动信息列表
     */
    List<TripInteractDomain> queryTripInteractList(TripInteractDomain tripInteractDomain);

}