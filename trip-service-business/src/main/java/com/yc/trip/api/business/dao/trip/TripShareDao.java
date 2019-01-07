package com.yc.trip.api.business.dao.trip;

import java.util.List;

import com.yc.trip.api.business.dto.trip.TripShare;
import com.yc.trip.api.business.query.trip.TripShareQuery;

/**
 * 旅游分享信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:59
 *
 */
public interface TripShareDao {

    /**
     * 旅游分享信息新增
     */
    void add(TripShare tripShare);

    /**
     * 旅游分享信息修改
     */
    void update(TripShare tripShare);
    
    /**
     * 旅游分享信息查询
     */
    TripShare get(TripShareQuery tripShareQuery);

    /**
     * 旅游分享信息列表查询
     */
    List<TripShare> queryList(TripShareQuery tripShareQuery);

}