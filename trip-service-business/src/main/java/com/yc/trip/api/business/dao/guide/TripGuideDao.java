package com.yc.trip.api.business.dao.guide;

import java.util.List;

import com.yc.trip.api.business.bo.guide.TripGuideDomain;

/**
 * 旅游攻略信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:27
 *
 */
public interface TripGuideDao {

    /**
     * 新增旅游攻略信息
     */
    void addTripGuide(TripGuideDomain tripGuideDomain);

    /**
     * 修改旅游攻略信息
     */
    void updateTripGuide(TripGuideDomain tripGuideDomain);
    
    /**
     * 查询旅游攻略信息
     */
    TripGuideDomain getTripGuide(TripGuideDomain tripGuideDomain);

    /**
     * 查询旅游攻略信息列表
     */
    List<TripGuideDomain> queryTripGuideList(TripGuideDomain tripGuideDomain);

}