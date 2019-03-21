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
import com.yc.trip.api.business.bo.provider.ProviderBrandRelationDomain;
import com.yc.trip.api.business.dao.provider.ProviderBrandRelationDao;
import com.yc.trip.api.business.dto.provider.ProviderBrandRelation;
import com.yc.trip.api.business.facade.provider.ProviderBrandRelationFacade;

/**
 * 供应商品牌关联信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:10
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
    public ProviderBrandRelation addProviderBrandRelation(ProviderBrandRelation providerBrandRelation) throws PendingException {
        try {
            // 转换成domain对象
            ProviderBrandRelationDomain cond = BeanMapping.map(providerBrandRelation, ProviderBrandRelationDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.providerBrandRelationDBParamInvalid.throwException("供应商品牌关联信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            providerBrandRelationDao.addProviderBrandRelation(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, ProviderBrandRelation.class);
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
    public ProviderBrandRelation updateProviderBrandRelation(ProviderBrandRelation providerBrandRelation) throws PendingException {
        try {
            // 转换成domain对象
            ProviderBrandRelationDomain cond = BeanMapping.map(providerBrandRelation, ProviderBrandRelationDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.providerBrandRelationDBParamInvalid.throwException("供应商品牌关联信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            providerBrandRelationDao.updateProviderBrandRelation(cond);
            return BeanMapping.map(cond, ProviderBrandRelation.class);
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
    public ProviderBrandRelation getProviderBrandRelation(ProviderBrandRelation providerBrandRelation) throws PendingException {
        try {
            // 转换成Domain对象
            ProviderBrandRelationDomain cond = BeanMapping.map(providerBrandRelation, ProviderBrandRelationDomain.class);
            // 调数据库接口查询对象
            ProviderBrandRelationDomain resultBean = providerBrandRelationDao.getProviderBrandRelation(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, ProviderBrandRelation.class);
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
    public ProviderBrandRelation mustGet(ProviderBrandRelation providerBrandRelation) throws PendingException {
        // 查询单位信息
        ProviderBrandRelation result = getProviderBrandRelation(providerBrandRelation);
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
    public List<ProviderBrandRelation> queryProviderBrandRelationList(ProviderBrandRelation providerBrandRelation) throws PendingException {
        try {
            // 转换成Domain对象
            ProviderBrandRelationDomain cond = BeanMapping.map(providerBrandRelation, ProviderBrandRelationDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(providerBrandRelation.getOrderby())) {
                PageHelper.orderBy(providerBrandRelation.getOrderby());
            }
            // 调数据库接口查询列表
            List<ProviderBrandRelationDomain> resultList = providerBrandRelationDao.queryProviderBrandRelationList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, ProviderBrandRelation.class);
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
    public PageInfo<ProviderBrandRelation> queryProviderBrandRelationPage(ProviderBrandRelation providerBrandRelation) throws PendingException {
        try {
            // 对请求参数进行校验
            if (providerBrandRelation.getPageNo() <= 0 || providerBrandRelation.getPageSize() <= 0) {
                ResCode.providerBrandRelationDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(providerBrandRelation.getPageNo(), providerBrandRelation.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(providerBrandRelation.getOrderby())) {
                PageHelper.orderBy(providerBrandRelation.getOrderby());
            }
            // 转换成Domain对象
            ProviderBrandRelationDomain cond = BeanMapping.map(providerBrandRelation, ProviderBrandRelationDomain.class);
            // 调数据库接口查询列表
            List<ProviderBrandRelationDomain> resultList = providerBrandRelationDao.queryProviderBrandRelationList(cond);
            // 生成分页对象
            PageInfo<ProviderBrandRelationDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, ProviderBrandRelation.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.providerBrandRelationDBError, "供应商品牌关联信息分页查询失败");
        }
    }

}
