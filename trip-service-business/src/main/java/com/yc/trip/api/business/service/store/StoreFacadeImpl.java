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
import com.yc.trip.api.business.bo.store.StoreDomain;
import com.yc.trip.api.business.dao.store.StoreDao;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.facade.store.StoreFacade;

/**
 * 门店信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:22
 */
@Service(version = "1.0.0")
public class StoreFacadeImpl extends AbstractDubboNativeService implements StoreFacade {

    @Autowired
    private StoreDao storeDao;

    /**
     * 新增门店信息
     * 
     * @throws PendingException
     */
    @Override
    public Store addStore(Store store) throws PendingException {
        try {
            // 转换成domain对象
            StoreDomain cond = BeanMapping.map(store, StoreDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.storeDBParamInvalid.throwException("门店信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            storeDao.addStore(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Store.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "门店信息新增失败");
        }
    }

    /**
     * 修改门店信息
     * 
     * @throws PendingException
     */
    @Override
    public Store updateStore(Store store) throws PendingException {
        try {
            // 转换成domain对象
            StoreDomain cond = BeanMapping.map(store, StoreDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.storeDBParamInvalid.throwException("门店信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            storeDao.updateStore(cond);
            return BeanMapping.map(cond, Store.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "门店信息更新失败");
        }
    }

    /**
     * 查询门店信息
     * 
     * @throws PendingException
     */
    @Override
    public Store getStore(Store store) throws PendingException {
        try {
            // 转换成Domain对象
            StoreDomain cond = BeanMapping.map(store, StoreDomain.class);
            // 调数据库接口查询对象
            StoreDomain resultBean = storeDao.getStore(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Store.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "门店信息查询失败");
        }
    }
    
    /**
     * 查询门店信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Store mustGet(Store store) throws PendingException {
        // 查询单位信息
        Store result = getStore(store);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.storeDBGetNull.throwException("未查询到门店信息");
        }
        return result;
    }

    /**
     * 查询门店信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Store> queryStoreList(Store store) throws PendingException {
        try {
            // 转换成Domain对象
            StoreDomain cond = BeanMapping.map(store, StoreDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(store.getOrderby())) {
                PageHelper.orderBy(store.getOrderby());
            }
            // 调数据库接口查询列表
            List<StoreDomain> resultList = storeDao.queryStoreList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Store.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "门店信息列表查询失败");
        }
    }

    /**
     * 查询门店信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Store> queryStorePage(Store store) throws PendingException {
        try {
            // 对请求参数进行校验
            if (store.getPageNo() <= 0 || store.getPageSize() <= 0) {
                ResCode.storeDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(store.getPageNo(), store.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(store.getOrderby())) {
                PageHelper.orderBy(store.getOrderby());
            }
            // 转换成Domain对象
            StoreDomain cond = BeanMapping.map(store, StoreDomain.class);
            // 调数据库接口查询列表
            List<StoreDomain> resultList = storeDao.queryStoreList(cond);
            // 生成分页对象
            PageInfo<StoreDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Store.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "门店信息分页查询失败");
        }
    }

}
