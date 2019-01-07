package com.yc.trip.api.business.service.provider;

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
import com.yc.trip.api.business.dao.provider.ProviderDao;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.query.provider.ProviderQuery;
import com.yc.trip.api.business.facade.provider.ProviderFacade;

/**
 * 供应商信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:24
 */
@Service(version = "1.0.0")
public class ProviderFacadeImpl extends AbstractDubboNativeService implements ProviderFacade {

    @Autowired
    private ProviderDao providerDao;

    /**
     * 新增供应商信息
     * 
     * @throws PendingException
     */
    @Override
    public Provider add(Provider provider) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            provider.validateInsertFields();
            // 调数据库接口进行新增操作
            providerDao.add(provider);
            // 将新增后回返回（包含自增主键值）
            return provider;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerDBError, "供应商信息新增失败");
        }
    }

    /**
     * 修改供应商信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(Provider provider) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (provider.isAllFiledsNull()) {
                ResCode.providerDBParamInvalid.throwException("供应商信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            providerDao.update(provider);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerDBError, "供应商信息更新失败");
        }
    }

    /**
     * 查询供应商信息
     * 
     * @throws PendingException
     */
    @Override
    public Provider get(ProviderQuery providerQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return providerDao.get(providerQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerDBError, "供应商信息查询失败");
        }
    }
    
    /**
     * 查询供应商信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Provider mustGet(ProviderQuery providerQuery) throws PendingException {
        // 查询单位信息
        Provider result = get(providerQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.providerDBGetNull.throwException("未查询到供应商信息");
        }
        return result;
    }

    /**
     * 查询供应商信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Provider> queryList(ProviderQuery providerQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(providerQuery.getOrderby())) {
                PageHelper.orderBy(providerQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return providerDao.queryList(providerQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerDBError, "供应商信息列表查询失败");
        }
    }

    /**
     * 查询供应商信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Provider> queryPage(ProviderQuery providerQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (providerQuery.getPageNo() <= 0 || providerQuery.getPageSize() <= 0) {
                ResCode.providerDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(providerQuery.getPageNo(), providerQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(providerQuery.getOrderby())) {
                PageHelper.orderBy(providerQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<Provider> resultList = providerDao.queryList(providerQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerDBError, "供应商信息分页查询失败");
        }
    }

}
