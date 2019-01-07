package com.yc.trip.api.business.dao.system;

import java.util.List;

import com.yc.trip.api.business.dto.system.OperateLog;
import com.yc.trip.api.business.query.system.OperateLogQuery;

/**
 * 操作日志Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:11
 *
 */
public interface OperateLogDao {

    /**
     * 操作日志新增
     */
    void add(OperateLog operateLog);

    /**
     * 操作日志修改
     */
    void update(OperateLog operateLog);
    
    /**
     * 操作日志查询
     */
    OperateLog get(OperateLogQuery operateLogQuery);

    /**
     * 操作日志列表查询
     */
    List<OperateLog> queryList(OperateLogQuery operateLogQuery);

}