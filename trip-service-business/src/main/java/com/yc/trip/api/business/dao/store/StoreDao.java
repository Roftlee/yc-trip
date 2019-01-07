package com.yc.trip.api.business.dao.store;

import java.util.List;

import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.query.store.StoreQuery;

/**
 * 门店信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:47
 *
 */
public interface StoreDao {

    /**
     * 门店信息新增
     */
    void add(Store store);

    /**
     * 门店信息修改
     */
    void update(Store store);
    
    /**
     * 门店信息查询
     */
    Store get(StoreQuery storeQuery);

    /**
     * 门店信息列表查询
     */
    List<Store> queryList(StoreQuery storeQuery);

}