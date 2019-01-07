package com.yc.trip.api.business.facade.train;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.train.Train;
import com.yc.trip.api.business.query.train.TrainQuery;

/**
 * 培训信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:55
 */
public interface TrainFacade {

    /**
     * 新增培训信息
     * @throws PendingException 
     */
    Train add(Train train) throws PendingException;

    /**
     * 修改培训信息
     * @throws PendingException 
     */
    void update(Train train) throws PendingException;
    
    /**
     * 查询培训信息
     * @throws PendingException 
     */
    Train get(TrainQuery trainQuery) throws PendingException;
    
    /**
     * 查询培训信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Train mustGet(TrainQuery trainQuery) throws PendingException;

    /**
     * 查询培训信息列表
     * @throws PendingException 
     */
    List<Train> queryList(TrainQuery trainQuery) throws PendingException;

    /**
     * 查询培训信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Train> queryPage(TrainQuery trainQuery) throws PendingException;

}