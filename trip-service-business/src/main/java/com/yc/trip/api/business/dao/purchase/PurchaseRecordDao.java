package com.yc.trip.api.business.dao.purchase;

import java.util.List;

import com.yc.trip.api.business.dto.purchase.PurchaseRecord;
import com.yc.trip.api.business.query.purchase.PurchaseRecordQuery;

/**
 * 服务购买信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:27
 *
 */
public interface PurchaseRecordDao {

    /**
     * 服务购买信息新增
     */
    void add(PurchaseRecord purchaseRecord);

    /**
     * 服务购买信息修改
     */
    void update(PurchaseRecord purchaseRecord);
    
    /**
     * 服务购买信息查询
     */
    PurchaseRecord get(PurchaseRecordQuery purchaseRecordQuery);

    /**
     * 服务购买信息列表查询
     */
    List<PurchaseRecord> queryList(PurchaseRecordQuery purchaseRecordQuery);

}