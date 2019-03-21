package com.yc.trip.api.business.facade.system;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.system.OperateLog;

/**
 * 操作日志相关接口
 * @author My-Toolkits
 * @since 2019-03-21 21:44
 */
public interface OperateLogFacade {

    /**
     * 新增操作日志
     * @throws PendingException 
     */
    OperateLog addOperateLog(OperateLog operateLog) throws PendingException;

    /**
     * 修改操作日志
     * @throws PendingException 
     */
    OperateLog updateOperateLog(OperateLog operateLog) throws PendingException;
    
    /**
     * 查询操作日志
     * @throws PendingException 
     */
    OperateLog getOperateLog(OperateLog operateLog) throws PendingException;
    
    /**
     * 查询操作日志（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    OperateLog mustGet(OperateLog operateLog) throws PendingException;

    /**
     * 查询操作日志列表
     * @throws PendingException 
     */
    List<OperateLog> queryOperateLogList(OperateLog operateLog) throws PendingException;

    /**
     * 查询操作日志列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<OperateLog> queryOperateLogPage(OperateLog operateLog) throws PendingException;

}