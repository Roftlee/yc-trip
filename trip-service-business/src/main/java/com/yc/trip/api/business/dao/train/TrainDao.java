package com.yc.trip.api.business.dao.train;

import java.util.List;

import com.yc.trip.api.business.dto.train.Train;
import com.yc.trip.api.business.query.train.TrainQuery;

/**
 * 培训信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:55
 *
 */
public interface TrainDao {

    /**
     * 培训信息新增
     */
    void add(Train train);

    /**
     * 培训信息修改
     */
    void update(Train train);
    
    /**
     * 培训信息查询
     */
    Train get(TrainQuery trainQuery);

    /**
     * 培训信息列表查询
     */
    List<Train> queryList(TrainQuery trainQuery);

}