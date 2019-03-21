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
import com.yc.trip.api.business.bo.system.OperateLogDomain;
import com.yc.trip.api.business.dao.system.OperateLogDao;
import com.yc.trip.api.business.dto.system.OperateLog;
import com.yc.trip.api.business.facade.system.OperateLogFacade;

/**
 * 操作日志相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:44
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
    public OperateLog addOperateLog(OperateLog operateLog) throws PendingException {
        try {
            // 转换成domain对象
            OperateLogDomain cond = BeanMapping.map(operateLog, OperateLogDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.operateLogDBParamInvalid.throwException("操作日志新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            operateLogDao.addOperateLog(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, OperateLog.class);
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
    public OperateLog updateOperateLog(OperateLog operateLog) throws PendingException {
        try {
            // 转换成domain对象
            OperateLogDomain cond = BeanMapping.map(operateLog, OperateLogDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.operateLogDBParamInvalid.throwException("操作日志更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            operateLogDao.updateOperateLog(cond);
            return BeanMapping.map(cond, OperateLog.class);
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
    public OperateLog getOperateLog(OperateLog operateLog) throws PendingException {
        try {
            // 转换成Domain对象
            OperateLogDomain cond = BeanMapping.map(operateLog, OperateLogDomain.class);
            // 调数据库接口查询对象
            OperateLogDomain resultBean = operateLogDao.getOperateLog(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, OperateLog.class);
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
    public OperateLog mustGet(OperateLog operateLog) throws PendingException {
        // 查询单位信息
        OperateLog result = getOperateLog(operateLog);
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
    public List<OperateLog> queryOperateLogList(OperateLog operateLog) throws PendingException {
        try {
            // 转换成Domain对象
            OperateLogDomain cond = BeanMapping.map(operateLog, OperateLogDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(operateLog.getOrderby())) {
                PageHelper.orderBy(operateLog.getOrderby());
            }
            // 调数据库接口查询列表
            List<OperateLogDomain> resultList = operateLogDao.queryOperateLogList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, OperateLog.class);
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
    public PageInfo<OperateLog> queryOperateLogPage(OperateLog operateLog) throws PendingException {
        try {
            // 对请求参数进行校验
            if (operateLog.getPageNo() <= 0 || operateLog.getPageSize() <= 0) {
                ResCode.operateLogDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(operateLog.getPageNo(), operateLog.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(operateLog.getOrderby())) {
                PageHelper.orderBy(operateLog.getOrderby());
            }
            // 转换成Domain对象
            OperateLogDomain cond = BeanMapping.map(operateLog, OperateLogDomain.class);
            // 调数据库接口查询列表
            List<OperateLogDomain> resultList = operateLogDao.queryOperateLogList(cond);
            // 生成分页对象
            PageInfo<OperateLogDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, OperateLog.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.operateLogDBError, "操作日志分页查询失败");
        }
    }

}
