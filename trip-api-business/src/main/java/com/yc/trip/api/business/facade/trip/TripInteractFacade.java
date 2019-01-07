package com.yc.trip.api.business.facade.trip;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.trip.TripInteract;
import com.yc.trip.api.business.query.trip.TripInteractQuery;

/**
 * 旅游互动信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:58
 */
public interface TripInteractFacade {

    /**
     * 新增旅游互动信息
     * @throws PendingException 
     */
    TripInteract add(TripInteract tripInteract) throws PendingException;

    /**
     * 修改旅游互动信息
     * @throws PendingException 
     */
    void update(TripInteract tripInteract) throws PendingException;
    
    /**
     * 查询旅游互动信息
     * @throws PendingException 
     */
    TripInteract get(TripInteractQuery tripInteractQuery) throws PendingException;
    
    /**
     * 查询旅游互动信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    TripInteract mustGet(TripInteractQuery tripInteractQuery) throws PendingException;

    /**
     * 查询旅游互动信息列表
     * @throws PendingException 
     */
    List<TripInteract> queryList(TripInteractQuery tripInteractQuery) throws PendingException;

    /**
     * 查询旅游互动信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<TripInteract> queryPage(TripInteractQuery tripInteractQuery) throws PendingException;

}