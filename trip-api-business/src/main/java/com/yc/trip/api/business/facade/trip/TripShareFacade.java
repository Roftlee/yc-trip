package com.yc.trip.api.business.facade.trip;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.trip.TripShare;
import com.yc.trip.api.business.query.trip.TripShareQuery;

/**
 * 旅游分享信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:59
 */
public interface TripShareFacade {

    /**
     * 新增旅游分享信息
     * @throws PendingException 
     */
    TripShare add(TripShare tripShare) throws PendingException;

    /**
     * 修改旅游分享信息
     * @throws PendingException 
     */
    void update(TripShare tripShare) throws PendingException;
    
    /**
     * 查询旅游分享信息
     * @throws PendingException 
     */
    TripShare get(TripShareQuery tripShareQuery) throws PendingException;
    
    /**
     * 查询旅游分享信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    TripShare mustGet(TripShareQuery tripShareQuery) throws PendingException;

    /**
     * 查询旅游分享信息列表
     * @throws PendingException 
     */
    List<TripShare> queryList(TripShareQuery tripShareQuery) throws PendingException;

    /**
     * 查询旅游分享信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<TripShare> queryPage(TripShareQuery tripShareQuery) throws PendingException;

}