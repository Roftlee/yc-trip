package com.yc.trip.api.business.facade.store;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.query.store.StoreQuery;

/**
 * 门店信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:47
 */
public interface StoreFacade {

    /**
     * 新增门店信息
     * @throws PendingException 
     */
    Store add(Store store) throws PendingException;

    /**
     * 修改门店信息
     * @throws PendingException 
     */
    void update(Store store) throws PendingException;
    
    /**
     * 查询门店信息
     * @throws PendingException 
     */
    Store get(StoreQuery storeQuery) throws PendingException;
    
    /**
     * 查询门店信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Store mustGet(StoreQuery storeQuery) throws PendingException;

    /**
     * 查询门店信息列表
     * @throws PendingException 
     */
    List<Store> queryList(StoreQuery storeQuery) throws PendingException;

    /**
     * 查询门店信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Store> queryPage(StoreQuery storeQuery) throws PendingException;

}