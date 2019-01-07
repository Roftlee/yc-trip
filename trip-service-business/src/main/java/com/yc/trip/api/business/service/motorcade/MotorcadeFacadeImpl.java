package com.yc.trip.api.business.service.motorcade;

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
import com.yc.trip.api.business.dao.motorcade.MotorcadeDao;
import com.yc.trip.api.business.dto.motorcade.Motorcade;
import com.yc.trip.api.business.query.motorcade.MotorcadeQuery;
import com.yc.trip.api.business.facade.motorcade.MotorcadeFacade;

/**
 * 车队相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:06
 */
@Service(version = "1.0.0")
public class MotorcadeFacadeImpl extends AbstractDubboNativeService implements MotorcadeFacade {

    @Autowired
    private MotorcadeDao motorcadeDao;

    /**
     * 新增车队
     * 
     * @throws PendingException
     */
    @Override
    public Motorcade add(Motorcade motorcade) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            motorcade.validateInsertFields();
            // 调数据库接口进行新增操作
            motorcadeDao.add(motorcade);
            // 将新增后回返回（包含自增主键值）
            return motorcade;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.motorcadeDBError, "车队新增失败");
        }
    }

    /**
     * 修改车队
     * 
     * @throws PendingException
     */
    @Override
    public void update(Motorcade motorcade) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (motorcade.isAllFiledsNull()) {
                ResCode.motorcadeDBParamInvalid.throwException("车队更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            motorcadeDao.update(motorcade);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.motorcadeDBError, "车队更新失败");
        }
    }

    /**
     * 查询车队
     * 
     * @throws PendingException
     */
    @Override
    public Motorcade get(MotorcadeQuery motorcadeQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return motorcadeDao.get(motorcadeQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.motorcadeDBError, "车队查询失败");
        }
    }
    
    /**
     * 查询车队信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Motorcade mustGet(MotorcadeQuery motorcadeQuery) throws PendingException {
        // 查询单位信息
        Motorcade result = get(motorcadeQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.motorcadeDBGetNull.throwException("未查询到车队");
        }
        return result;
    }

    /**
     * 查询车队列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Motorcade> queryList(MotorcadeQuery motorcadeQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(motorcadeQuery.getOrderby())) {
                PageHelper.orderBy(motorcadeQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return motorcadeDao.queryList(motorcadeQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.motorcadeDBError, "车队列表查询失败");
        }
    }

    /**
     * 查询车队列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Motorcade> queryPage(MotorcadeQuery motorcadeQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (motorcadeQuery.getPageNo() <= 0 || motorcadeQuery.getPageSize() <= 0) {
                ResCode.motorcadeDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(motorcadeQuery.getPageNo(), motorcadeQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(motorcadeQuery.getOrderby())) {
                PageHelper.orderBy(motorcadeQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<Motorcade> resultList = motorcadeDao.queryList(motorcadeQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.motorcadeDBError, "车队分页查询失败");
        }
    }

}
