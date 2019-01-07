package com.yc.trip.api.business.dao.trip;

import java.util.List;

import com.yc.trip.api.business.dto.trip.TripInteract;
import com.yc.trip.api.business.query.trip.TripInteractQuery;

/**
 * 旅游互动信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:58
 *
 */
public interface TripInteractDao {

    /**
     * 旅游互动信息新增
     */
    void add(TripInteract tripInteract);

    /**
     * 旅游互动信息修改
     */
    void update(TripInteract tripInteract);
    
    /**
     * 旅游互动信息查询
     */
    TripInteract get(TripInteractQuery tripInteractQuery);

    /**
     * 旅游互动信息列表查询
     */
    List<TripInteract> queryList(TripInteractQuery tripInteractQuery);

}