package com.yc.trip.api.business.dao.purchase;

import java.util.List;

import com.yc.trip.api.business.bo.purchase.PurchaseRecordDomain;

/**
 * 服务购买信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:11
 *
 */
public interface PurchaseRecordDao {

    /**
     * 新增服务购买信息
     */
    void addPurchaseRecord(PurchaseRecordDomain purchaseRecordDomain);

    /**
     * 修改服务购买信息
     */
    void updatePurchaseRecord(PurchaseRecordDomain purchaseRecordDomain);
    
    /**
     * 查询服务购买信息
     */
    PurchaseRecordDomain getPurchaseRecord(PurchaseRecordDomain purchaseRecordDomain);

    /**
     * 查询服务购买信息列表
     */
    List<PurchaseRecordDomain> queryPurchaseRecordList(PurchaseRecordDomain purchaseRecordDomain);

}