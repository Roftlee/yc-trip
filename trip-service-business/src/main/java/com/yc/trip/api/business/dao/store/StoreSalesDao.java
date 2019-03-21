package com.yc.trip.api.business.dao.store;

import java.util.List;

import com.yc.trip.api.business.bo.store.StoreSalesDomain;

/**
 * 门店销售信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:24
 *
 */
public interface StoreSalesDao {

    /**
     * 新增门店销售信息
     */
    void addStoreSales(StoreSalesDomain storeSalesDomain);

    /**
     * 修改门店销售信息
     */
    void updateStoreSales(StoreSalesDomain storeSalesDomain);
    
    /**
     * 查询门店销售信息
     */
    StoreSalesDomain getStoreSales(StoreSalesDomain storeSalesDomain);

    /**
     * 查询门店销售信息列表
     */
    List<StoreSalesDomain> queryStoreSalesList(StoreSalesDomain storeSalesDomain);

}