package com.yc.trip.api.business.dao.store;

import java.util.List;

import com.yc.trip.api.business.dto.store.StoreCustomer;
import com.yc.trip.api.business.query.store.StoreCustomerQuery;

/**
 * 门店客户信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:53
 *
 */
public interface StoreCustomerDao {

    /**
     * 门店客户信息新增
     */
    void add(StoreCustomer storeCustomer);

    /**
     * 门店客户信息修改
     */
    void update(StoreCustomer storeCustomer);
    
    /**
     * 门店客户信息查询
     */
    StoreCustomer get(StoreCustomerQuery storeCustomerQuery);

    /**
     * 门店客户信息列表查询
     */
    List<StoreCustomer> queryList(StoreCustomerQuery storeCustomerQuery);

}