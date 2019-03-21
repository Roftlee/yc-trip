package com.yc.trip.api.business.facade.train;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.train.Train;

/**
 * 培训信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:26
 */
public interface TrainFacade {

    /**
     * 新增培训信息
     * @throws PendingException 
     */
    Train addTrain(Train train) throws PendingException;

    /**
     * 修改培训信息
     * @throws PendingException 
     */
    Train updateTrain(Train train) throws PendingException;
    
    /**
     * 查询培训信息
     * @throws PendingException 
     */
    Train getTrain(Train train) throws PendingException;
    
    /**
     * 查询培训信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Train mustGet(Train train) throws PendingException;

    /**
     * 查询培训信息列表
     * @throws PendingException 
     */
    List<Train> queryTrainList(Train train) throws PendingException;

    /**
     * 查询培训信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Train> queryTrainPage(Train train) throws PendingException;

}