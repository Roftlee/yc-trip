package com.yc.trip.api.business.facade.trip;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.trip.TripInteract;

/**
 * 旅游互动信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:29
 */
public interface TripInteractFacade {

    /**
     * 新增旅游互动信息
     * @throws PendingException 
     */
    TripInteract addTripInteract(TripInteract tripInteract) throws PendingException;

    /**
     * 修改旅游互动信息
     * @throws PendingException 
     */
    TripInteract updateTripInteract(TripInteract tripInteract) throws PendingException;
    
    /**
     * 查询旅游互动信息
     * @throws PendingException 
     */
    TripInteract getTripInteract(TripInteract tripInteract) throws PendingException;
    
    /**
     * 查询旅游互动信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    TripInteract mustGet(TripInteract tripInteract) throws PendingException;

    /**
     * 查询旅游互动信息列表
     * @throws PendingException 
     */
    List<TripInteract> queryTripInteractList(TripInteract tripInteract) throws PendingException;

    /**
     * 查询旅游互动信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<TripInteract> queryTripInteractPage(TripInteract tripInteract) throws PendingException;

}