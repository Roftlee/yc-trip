package com.yc.trip.api.business.dao.system;

import java.util.List;

import com.yc.trip.api.business.bo.system.OperateLogDomain;

/**
 * 操作日志Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:44
 *
 */
public interface OperateLogDao {

    /**
     * 新增操作日志
     */
    void addOperateLog(OperateLogDomain operateLogDomain);

    /**
     * 修改操作日志
     */
    void updateOperateLog(OperateLogDomain operateLogDomain);
    
    /**
     * 查询操作日志
     */
    OperateLogDomain getOperateLog(OperateLogDomain operateLogDomain);

    /**
     * 查询操作日志列表
     */
    List<OperateLogDomain> queryOperateLogList(OperateLogDomain operateLogDomain);

}