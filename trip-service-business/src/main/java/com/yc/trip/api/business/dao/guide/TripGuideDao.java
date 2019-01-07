package com.yc.trip.api.business.dao.guide;

import java.util.List;

import com.yc.trip.api.business.dto.guide.TripGuide;
import com.yc.trip.api.business.query.guide.TripGuideQuery;

/**
 * 旅游攻略信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:57
 *
 */
public interface TripGuideDao {

    /**
     * 旅游攻略信息新增
     */
    void add(TripGuide tripGuide);

    /**
     * 旅游攻略信息修改
     */
    void update(TripGuide tripGuide);
    
    /**
     * 旅游攻略信息查询
     */
    TripGuide get(TripGuideQuery tripGuideQuery);

    /**
     * 旅游攻略信息列表查询
     */
    List<TripGuide> queryList(TripGuideQuery tripGuideQuery);

}