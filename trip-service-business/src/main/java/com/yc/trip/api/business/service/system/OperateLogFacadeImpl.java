package com.yc.trip.api.business.service.system;

import java.util.List;


import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.business.dao.system.OperateLogDao;
import com.yc.trip.api.business.dto.system.OperateLog;
import com.yc.trip.api.business.query.system.OperateLogQuery;
import com.yc.trip.api.business.facade.system.OperateLogFacade;

/**
 * 操作日志相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:11
 */
@Service(version = "1.0.0")
public class OperateLogFacadeImpl extends AbstractDubboNativeService implements OperateLogFacade {

    @Autowired
    private OperateLogDao operateLogDao;

    /**
     * 新增操作日志
     * 
     * @throws PendingException
     */
    @Override
    public OperateLog add(OperateLog operateLog) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            operateLog.validateInsertFields();
            // 调数据库接口进行新增操作
            operateLogDao.add(operateLog);
            // 将新增后回返回（包含自增主键值）
            return operateLog;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.operateLogDBError, "操作日志新增失败");
        }
    }

    /**
     * 修改操作日志
     * 
     * @throws PendingException
     */
    @Override
    public void update(OperateLog operateLog) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (operateLog.isAllFiledsNull()) {
                ResCode.operateLogDBParamInvalid.throwException("操作日志更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            operateLogDao.update(operateLog);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.operateLogDBError, "操作日志更新失败");
        }
    }

    /**
     * 查询操作日志
     * 
     * @throws PendingException
     */
    @Override
    public OperateLog get(OperateLogQuery operateLogQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return operateLogDao.get(operateLogQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.operateLogDBError, "操作日志查询失败");
        }
    }
    
    /**
     * 查询操作日志信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public OperateLog mustGet(OperateLogQuery operateLogQuery) throws PendingException {
        // 查询单位信息
        OperateLog result = get(operateLogQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.operateLogDBGetNull.throwException("未查询到操作日志");
        }
        return result;
    }

    /**
     * 查询操作日志列表
     * 
     * @throws PendingException
     */
    @Override
    public List<OperateLog> queryList(OperateLogQuery operateLogQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(operateLogQuery.getOrderby())) {
                PageHelper.orderBy(operateLogQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return operateLogDao.queryList(operateLogQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.operateLogDBError, "操作日志列表查询失败");
        }
    }

    /**
     * 查询操作日志列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<OperateLog> queryPage(OperateLogQuery operateLogQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (operateLogQuery.getPageNo() <= 0 || operateLogQuery.getPageSize() <= 0) {
                ResCode.operateLogDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(operateLogQuery.getPageNo(), operateLogQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(operateLogQuery.getOrderby())) {
                PageHelper.orderBy(operateLogQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<OperateLog> resultList = operateLogDao.queryList(operateLogQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.operateLogDBError, "操作日志分页查询失败");
        }
    }

}
