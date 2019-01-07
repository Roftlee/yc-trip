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
import com.yc.trip.api.business.dao.provider.ProviderBrandRelationDao;
import com.yc.trip.api.business.dto.provider.ProviderBrandRelation;
import com.yc.trip.api.business.query.provider.ProviderBrandRelationQuery;
import com.yc.trip.api.business.facade.provider.ProviderBrandRelationFacade;

/**
 * 供应商品牌关联信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:25
 */
@Service(version = "1.0.0")
public class ProviderBrandRelationFacadeImpl extends AbstractDubboNativeService implements ProviderBrandRelationFacade {

    @Autowired
    private ProviderBrandRelationDao providerBrandRelationDao;

    /**
     * 新增供应商品牌关联信息
     * 
     * @throws PendingException
     */
    @Override
    public ProviderBrandRelation add(ProviderBrandRelation providerBrandRelation) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            providerBrandRelation.validateInsertFields();
            // 调数据库接口进行新增操作
            providerBrandRelationDao.add(providerBrandRelation);
            // 将新增后回返回（包含自增主键值）
            return providerBrandRelation;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerBrandRelationDBError, "供应商品牌关联信息新增失败");
        }
    }

    /**
     * 修改供应商品牌关联信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(ProviderBrandRelation providerBrandRelation) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (providerBrandRelation.isAllFiledsNull()) {
                ResCode.providerBrandRelationDBParamInvalid.throwException("供应商品牌关联信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            providerBrandRelationDao.update(providerBrandRelation);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerBrandRelationDBError, "供应商品牌关联信息更新失败");
        }
    }

    /**
     * 查询供应商品牌关联信息
     * 
     * @throws PendingException
     */
    @Override
    public ProviderBrandRelation get(ProviderBrandRelationQuery providerBrandRelationQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return providerBrandRelationDao.get(providerBrandRelationQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerBrandRelationDBError, "供应商品牌关联信息查询失败");
        }
    }
    
    /**
     * 查询供应商品牌关联信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public ProviderBrandRelation mustGet(ProviderBrandRelationQuery providerBrandRelationQuery) throws PendingException {
        // 查询单位信息
        ProviderBrandRelation result = get(providerBrandRelationQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.providerBrandRelationDBGetNull.throwException("未查询到供应商品牌关联信息");
        }
        return result;
    }

    /**
     * 查询供应商品牌关联信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<ProviderBrandRelation> queryList(ProviderBrandRelationQuery providerBrandRelationQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(providerBrandRelationQuery.getOrderby())) {
                PageHelper.orderBy(providerBrandRelationQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return providerBrandRelationDao.queryList(providerBrandRelationQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerBrandRelationDBError, "供应商品牌关联信息列表查询失败");
        }
    }

    /**
     * 查询供应商品牌关联信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<ProviderBrandRelation> queryPage(ProviderBrandRelationQuery providerBrandRelationQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (providerBrandRelationQuery.getPageNo() <= 0 || providerBrandRelationQuery.getPageSize() <= 0) {
                ResCode.providerBrandRelationDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(providerBrandRelationQuery.getPageNo(), providerBrandRelationQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(providerBrandRelationQuery.getOrderby())) {
                PageHelper.orderBy(providerBrandRelationQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<ProviderBrandRelation> resultList = providerBrandRelationDao.queryList(providerBrandRelationQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerBrandRelationDBError, "供应商品牌关联信息分页查询失败");
        }
    }

}
