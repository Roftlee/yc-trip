package com.yc.trip.api.business.dao.store;

import java.util.List;

import com.yc.trip.api.business.bo.store.StoreDomain;

/**
 * 门店信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:22
 *
 */
public interface StoreDao {

    /**
     * 新增门店信息
     */
    void addStore(StoreDomain storeDomain);

    /**
     * 修改门店信息
     */
    void updateStore(StoreDomain storeDomain);
    
    /**
     * 查询门店信息
     */
    StoreDomain getStore(StoreDomain storeDomain);

    /**
     * 查询门店信息列表
     */
    List<StoreDomain> queryStoreList(StoreDomain storeDomain);

    List<StoreDomain>  getAllStore();

}