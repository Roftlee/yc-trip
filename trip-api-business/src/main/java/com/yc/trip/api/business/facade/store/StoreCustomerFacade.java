package com.yc.trip.api.business.facade.store;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.store.StoreCustomer;
import com.yc.trip.api.business.query.store.StoreCustomerQuery;

/**
 * 门店客户信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:53
 */
public interface StoreCustomerFacade {

    /**
     * 新增门店客户信息
     * @throws PendingException 
     */
    StoreCustomer add(StoreCustomer storeCustomer) throws PendingException;

    /**
     * 修改门店客户信息
     * @throws PendingException 
     */
    void update(StoreCustomer storeCustomer) throws PendingException;
    
    /**
     * 查询门店客户信息
     * @throws PendingException 
     */
    StoreCustomer get(StoreCustomerQuery storeCustomerQuery) throws PendingException;
    
    /**
     * 查询门店客户信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    StoreCustomer mustGet(StoreCustomerQuery storeCustomerQuery) throws PendingException;

    /**
     * 查询门店客户信息列表
     * @throws PendingException 
     */
    List<StoreCustomer> queryList(StoreCustomerQuery storeCustomerQuery) throws PendingException;

    /**
     * 查询门店客户信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<StoreCustomer> queryPage(StoreCustomerQuery storeCustomerQuery) throws PendingException;

}