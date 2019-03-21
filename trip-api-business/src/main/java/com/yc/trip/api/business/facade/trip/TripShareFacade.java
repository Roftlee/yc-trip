package com.yc.trip.api.business.facade.trip;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.trip.TripShare;

/**
 * 旅游分享信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:30
 */
public interface TripShareFacade {

    /**
     * 新增旅游分享信息
     * @throws PendingException 
     */
    TripShare addTripShare(TripShare tripShare) throws PendingException;

    /**
     * 修改旅游分享信息
     * @throws PendingException 
     */
    TripShare updateTripShare(TripShare tripShare) throws PendingException;
    
    /**
     * 查询旅游分享信息
     * @throws PendingException 
     */
    TripShare getTripShare(TripShare tripShare) throws PendingException;
    
    /**
     * 查询旅游分享信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    TripShare mustGet(TripShare tripShare) throws PendingException;

    /**
     * 查询旅游分享信息列表
     * @throws PendingException 
     */
    List<TripShare> queryTripShareList(TripShare tripShare) throws PendingException;

    /**
     * 查询旅游分享信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<TripShare> queryTripSharePage(TripShare tripShare) throws PendingException;

}