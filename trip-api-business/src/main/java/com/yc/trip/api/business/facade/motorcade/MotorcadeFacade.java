package com.yc.trip.api.business.facade.motorcade;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.motorcade.Motorcade;
import com.yc.trip.api.business.query.motorcade.MotorcadeQuery;

/**
 * 车队相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:06
 */
public interface MotorcadeFacade {

    /**
     * 新增车队
     * @throws PendingException 
     */
    Motorcade add(Motorcade motorcade) throws PendingException;

    /**
     * 修改车队
     * @throws PendingException 
     */
    void update(Motorcade motorcade) throws PendingException;
    
    /**
     * 查询车队
     * @throws PendingException 
     */
    Motorcade get(MotorcadeQuery motorcadeQuery) throws PendingException;
    
    /**
     * 查询车队（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Motorcade mustGet(MotorcadeQuery motorcadeQuery) throws PendingException;

    /**
     * 查询车队列表
     * @throws PendingException 
     */
    List<Motorcade> queryList(MotorcadeQuery motorcadeQuery) throws PendingException;

    /**
     * 查询车队列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Motorcade> queryPage(MotorcadeQuery motorcadeQuery) throws PendingException;

}