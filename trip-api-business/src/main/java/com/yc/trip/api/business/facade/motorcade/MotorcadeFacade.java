package com.yc.trip.api.business.facade.motorcade;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.motorcade.Motorcade;

/**
 * 车队相关接口
 * @author My-Toolkits
 * @since 2019-03-21 21:39
 */
public interface MotorcadeFacade {

    /**
     * 新增车队
     * @throws PendingException 
     */
    Motorcade addMotorcade(Motorcade motorcade) throws PendingException;

    /**
     * 修改车队
     * @throws PendingException 
     */
    Motorcade updateMotorcade(Motorcade motorcade) throws PendingException;
    
    /**
     * 查询车队
     * @throws PendingException 
     */
    Motorcade getMotorcade(Motorcade motorcade) throws PendingException;
    
    /**
     * 查询车队（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Motorcade mustGet(Motorcade motorcade) throws PendingException;

    /**
     * 查询车队列表
     * @throws PendingException 
     */
    List<Motorcade> queryMotorcadeList(Motorcade motorcade) throws PendingException;

    /**
     * 查询车队列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Motorcade> queryMotorcadePage(Motorcade motorcade) throws PendingException;

}