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
import com.yc.trip.api.business.bo.provider.ProviderDomain;
import com.yc.trip.api.business.dao.provider.ProviderDao;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.facade.provider.ProviderFacade;

/**
 * 供应商信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:09
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
    public Provider addProvider(Provider provider) throws PendingException {
        try {
            // 转换成domain对象
            ProviderDomain cond = BeanMapping.map(provider, ProviderDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.providerDBParamInvalid.throwException("供应商信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            providerDao.addProvider(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Provider.class);
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
    public Provider updateProvider(Provider provider) throws PendingException {
        try {
            // 转换成domain对象
            ProviderDomain cond = BeanMapping.map(provider, ProviderDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.providerDBParamInvalid.throwException("供应商信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            providerDao.updateProvider(cond);
            return BeanMapping.map(cond, Provider.class);
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
    public Provider getProvider(Provider provider) throws PendingException {
        try {
            // 转换成Domain对象
            ProviderDomain cond = BeanMapping.map(provider, ProviderDomain.class);
            // 调数据库接口查询对象
            ProviderDomain resultBean = providerDao.getProvider(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Provider.class);
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
    public Provider mustGet(Provider provider) throws PendingException {
        // 查询单位信息
        Provider result = getProvider(provider);
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
    public List<Provider> queryProviderList(Provider provider) throws PendingException {
        try {
            // 转换成Domain对象
            ProviderDomain cond = BeanMapping.map(provider, ProviderDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(provider.getOrderby())) {
                PageHelper.orderBy(provider.getOrderby());
            }
            // 调数据库接口查询列表
            List<ProviderDomain> resultList = providerDao.queryProviderList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Provider.class);
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
    public PageInfo<Provider> queryProviderPage(Provider provider) throws PendingException {
        try {
            // 对请求参数进行校验
            if (provider.getPageNo() <= 0 || provider.getPageSize() <= 0) {
                ResCode.providerDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(provider.getPageNo(), provider.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(provider.getOrderby())) {
                PageHelper.orderBy(provider.getOrderby());
            }
            // 转换成Domain对象
            ProviderDomain cond = BeanMapping.map(provider, ProviderDomain.class);
            // 调数据库接口查询列表
            List<ProviderDomain> resultList = providerDao.queryProviderList(cond);
            // 生成分页对象
            PageInfo<ProviderDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Provider.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerDBError, "供应商信息分页查询失败");
        }
    }

}
