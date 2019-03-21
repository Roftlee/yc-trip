package com.yc.trip.api.business.service.store;

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
import com.yc.trip.api.business.bo.store.StoreSalesDomain;
import com.yc.trip.api.business.dao.store.StoreSalesDao;
import com.yc.trip.api.business.dto.store.StoreSales;
import com.yc.trip.api.business.facade.store.StoreSalesFacade;

/**
 * 门店销售信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:24
 */
@Service(version = "1.0.0")
public class StoreSalesFacadeImpl extends AbstractDubboNativeService implements StoreSalesFacade {

    @Autowired
    private StoreSalesDao storeSalesDao;

    /**
     * 新增门店销售信息
     * 
     * @throws PendingException
     */
    @Override
    public StoreSales addStoreSales(StoreSales storeSales) throws PendingException {
        try {
            // 转换成domain对象
            StoreSalesDomain cond = BeanMapping.map(storeSales, StoreSalesDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.storeSalesDBParamInvalid.throwException("门店销售信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            storeSalesDao.addStoreSales(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, StoreSales.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息新增失败");
        }
    }

    /**
     * 修改门店销售信息
     * 
     * @throws PendingException
     */
    @Override
    public StoreSales updateStoreSales(StoreSales storeSales) throws PendingException {
        try {
            // 转换成domain对象
            StoreSalesDomain cond = BeanMapping.map(storeSales, StoreSalesDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.storeSalesDBParamInvalid.throwException("门店销售信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            storeSalesDao.updateStoreSales(cond);
            return BeanMapping.map(cond, StoreSales.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息更新失败");
        }
    }

    /**
     * 查询门店销售信息
     * 
     * @throws PendingException
     */
    @Override
    public StoreSales getStoreSales(StoreSales storeSales) throws PendingException {
        try {
            // 转换成Domain对象
            StoreSalesDomain cond = BeanMapping.map(storeSales, StoreSalesDomain.class);
            // 调数据库接口查询对象
            StoreSalesDomain resultBean = storeSalesDao.getStoreSales(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, StoreSales.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息查询失败");
        }
    }
    
    /**
     * 查询门店销售信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public StoreSales mustGet(StoreSales storeSales) throws PendingException {
        // 查询单位信息
        StoreSales result = getStoreSales(storeSales);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.storeSalesDBGetNull.throwException("未查询到门店销售信息");
        }
        return result;
    }

    /**
     * 查询门店销售信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<StoreSales> queryStoreSalesList(StoreSales storeSales) throws PendingException {
        try {
            // 转换成Domain对象
            StoreSalesDomain cond = BeanMapping.map(storeSales, StoreSalesDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeSales.getOrderby())) {
                PageHelper.orderBy(storeSales.getOrderby());
            }
            // 调数据库接口查询列表
            List<StoreSalesDomain> resultList = storeSalesDao.queryStoreSalesList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, StoreSales.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息列表查询失败");
        }
    }

    /**
     * 查询门店销售信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<StoreSales> queryStoreSalesPage(StoreSales storeSales) throws PendingException {
        try {
            // 对请求参数进行校验
            if (storeSales.getPageNo() <= 0 || storeSales.getPageSize() <= 0) {
                ResCode.storeSalesDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(storeSales.getPageNo(), storeSales.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeSales.getOrderby())) {
                PageHelper.orderBy(storeSales.getOrderby());
            }
            // 转换成Domain对象
            StoreSalesDomain cond = BeanMapping.map(storeSales, StoreSalesDomain.class);
            // 调数据库接口查询列表
            List<StoreSalesDomain> resultList = storeSalesDao.queryStoreSalesList(cond);
            // 生成分页对象
            PageInfo<StoreSalesDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, StoreSales.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息分页查询失败");
        }
    }

}
