package com.yc.trip.api.business.dao.store;

import java.util.List;

import com.yc.trip.api.business.dto.store.StoreSales;
import com.yc.trip.api.business.query.store.StoreSalesQuery;

/**
 * 门店销售信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:54
 *
 */
public interface StoreSalesDao {

    /**
     * 门店销售信息新增
     */
    void add(StoreSales storeSales);

    /**
     * 门店销售信息修改
     */
    void update(StoreSales storeSales);
    
    /**
     * 门店销售信息查询
     */
    StoreSales get(StoreSalesQuery storeSalesQuery);

    /**
     * 门店销售信息列表查询
     */
    List<StoreSales> queryList(StoreSalesQuery storeSalesQuery);

}