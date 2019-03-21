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
import com.yc.trip.api.business.bo.motorcade.MotorcadeDomain;
import com.yc.trip.api.business.dao.motorcade.MotorcadeDao;
import com.yc.trip.api.business.dto.motorcade.Motorcade;
import com.yc.trip.api.business.facade.motorcade.MotorcadeFacade;

/**
 * 车队相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:39
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
    public Motorcade addMotorcade(Motorcade motorcade) throws PendingException {
        try {
            // 转换成domain对象
            MotorcadeDomain cond = BeanMapping.map(motorcade, MotorcadeDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.motorcadeDBParamInvalid.throwException("车队新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            motorcadeDao.addMotorcade(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Motorcade.class);
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
    public Motorcade updateMotorcade(Motorcade motorcade) throws PendingException {
        try {
            // 转换成domain对象
            MotorcadeDomain cond = BeanMapping.map(motorcade, MotorcadeDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.motorcadeDBParamInvalid.throwException("车队更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            motorcadeDao.updateMotorcade(cond);
            return BeanMapping.map(cond, Motorcade.class);
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
    public Motorcade getMotorcade(Motorcade motorcade) throws PendingException {
        try {
            // 转换成Domain对象
            MotorcadeDomain cond = BeanMapping.map(motorcade, MotorcadeDomain.class);
            // 调数据库接口查询对象
            MotorcadeDomain resultBean = motorcadeDao.getMotorcade(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Motorcade.class);
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
    public Motorcade mustGet(Motorcade motorcade) throws PendingException {
        // 查询单位信息
        Motorcade result = getMotorcade(motorcade);
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
    public List<Motorcade> queryMotorcadeList(Motorcade motorcade) throws PendingException {
        try {
            // 转换成Domain对象
            MotorcadeDomain cond = BeanMapping.map(motorcade, MotorcadeDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(motorcade.getOrderby())) {
                PageHelper.orderBy(motorcade.getOrderby());
            }
            // 调数据库接口查询列表
            List<MotorcadeDomain> resultList = motorcadeDao.queryMotorcadeList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Motorcade.class);
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
    public PageInfo<Motorcade> queryMotorcadePage(Motorcade motorcade) throws PendingException {
        try {
            // 对请求参数进行校验
            if (motorcade.getPageNo() <= 0 || motorcade.getPageSize() <= 0) {
                ResCode.motorcadeDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(motorcade.getPageNo(), motorcade.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(motorcade.getOrderby())) {
                PageHelper.orderBy(motorcade.getOrderby());
            }
            // 转换成Domain对象
            MotorcadeDomain cond = BeanMapping.map(motorcade, MotorcadeDomain.class);
            // 调数据库接口查询列表
            List<MotorcadeDomain> resultList = motorcadeDao.queryMotorcadeList(cond);
            // 生成分页对象
            PageInfo<MotorcadeDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Motorcade.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.motorcadeDBError, "车队分页查询失败");
        }
    }

}
