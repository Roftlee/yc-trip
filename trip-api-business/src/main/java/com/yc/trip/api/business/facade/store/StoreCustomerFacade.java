package com.yc.trip.api.business.facade.store;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.store.StoreCustomer;

/**
 * 门店客户信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:24
 */
public interface StoreCustomerFacade {

    /**
     * 新增门店客户信息
     * @throws PendingException 
     */
    StoreCustomer addStoreCustomer(StoreCustomer storeCustomer) throws PendingException;

    /**
     * 修改门店客户信息
     * @throws PendingException 
     */
    StoreCustomer updateStoreCustomer(StoreCustomer storeCustomer) throws PendingException;
    
    /**
     * 查询门店客户信息
     * @throws PendingException 
     */
    StoreCustomer getStoreCustomer(StoreCustomer storeCustomer) throws PendingException;
    
    /**
     * 查询门店客户信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    StoreCustomer mustGet(StoreCustomer storeCustomer) throws PendingException;

    /**
     * 查询门店客户信息列表
     * @throws PendingException 
     */
    List<StoreCustomer> queryStoreCustomerList(StoreCustomer storeCustomer) throws PendingException;

    /**
     * 查询门店客户信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<StoreCustomer> queryStoreCustomerPage(StoreCustomer storeCustomer) throws PendingException;

}