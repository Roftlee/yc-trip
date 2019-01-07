package com.yc.trip.api.business.facade.system;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.system.OperateLog;
import com.yc.trip.api.business.query.system.OperateLogQuery;

/**
 * 操作日志相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:11
 */
public interface OperateLogFacade {

    /**
     * 新增操作日志
     * @throws PendingException 
     */
    OperateLog add(OperateLog operateLog) throws PendingException;

    /**
     * 修改操作日志
     * @throws PendingException 
     */
    void update(OperateLog operateLog) throws PendingException;
    
    /**
     * 查询操作日志
     * @throws PendingException 
     */
    OperateLog get(OperateLogQuery operateLogQuery) throws PendingException;
    
    /**
     * 查询操作日志（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    OperateLog mustGet(OperateLogQuery operateLogQuery) throws PendingException;

    /**
     * 查询操作日志列表
     * @throws PendingException 
     */
    List<OperateLog> queryList(OperateLogQuery operateLogQuery) throws PendingException;

    /**
     * 查询操作日志列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<OperateLog> queryPage(OperateLogQuery operateLogQuery) throws PendingException;

}