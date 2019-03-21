package com.yc.trip.api.business.dao.train;

import java.util.List;

import com.yc.trip.api.business.bo.train.TrainDomain;

/**
 * 培训信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:26
 *
 */
public interface TrainDao {

    /**
     * 新增培训信息
     */
    void addTrain(TrainDomain trainDomain);

    /**
     * 修改培训信息
     */
    void updateTrain(TrainDomain trainDomain);
    
    /**
     * 查询培训信息
     */
    TrainDomain getTrain(TrainDomain trainDomain);

    /**
     * 查询培训信息列表
     */
    List<TrainDomain> queryTrainList(TrainDomain trainDomain);

}