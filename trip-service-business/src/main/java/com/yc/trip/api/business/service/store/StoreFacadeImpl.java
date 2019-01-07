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
import com.yc.trip.api.business.dao.store.StoreDao;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.query.store.StoreQuery;
import com.yc.trip.api.business.facade.store.StoreFacade;

/**
 * 门店信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:47
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
    public Store add(Store store) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            store.validateInsertFields();
            // 调数据库接口进行新增操作
            storeDao.add(store);
            // 将新增后回返回（包含自增主键值）
            return store;
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
    public void update(Store store) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (store.isAllFiledsNull()) {
                ResCode.storeDBParamInvalid.throwException("门店信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            storeDao.update(store);
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
    public Store get(StoreQuery storeQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return storeDao.get(storeQuery);
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
    public Store mustGet(StoreQuery storeQuery) throws PendingException {
        // 查询单位信息
        Store result = get(storeQuery);
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
    public List<Store> queryList(StoreQuery storeQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeQuery.getOrderby())) {
                PageHelper.orderBy(storeQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return storeDao.queryList(storeQuery);
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
    public PageInfo<Store> queryPage(StoreQuery storeQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (storeQuery.getPageNo() <= 0 || storeQuery.getPageSize() <= 0) {
                ResCode.storeDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(storeQuery.getPageNo(), storeQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeQuery.getOrderby())) {
                PageHelper.orderBy(storeQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<Store> resultList = storeDao.queryList(storeQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "门店信息分页查询失败");
        }
    }

}
