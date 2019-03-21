package com.yc.trip.api.business.dao.store;

import java.util.List;

import com.yc.trip.api.business.bo.store.StoreCustomerDomain;

/**
 * 门店客户信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:24
 *
 */
public interface StoreCustomerDao {

    /**
     * 新增门店客户信息
     */
    void addStoreCustomer(StoreCustomerDomain storeCustomerDomain);

    /**
     * 修改门店客户信息
     */
    void updateStoreCustomer(StoreCustomerDomain storeCustomerDomain);
    
    /**
     * 查询门店客户信息
     */
    StoreCustomerDomain getStoreCustomer(StoreCustomerDomain storeCustomerDomain);

    /**
     * 查询门店客户信息列表
     */
    List<StoreCustomerDomain> queryStoreCustomerList(StoreCustomerDomain storeCustomerDomain);

}