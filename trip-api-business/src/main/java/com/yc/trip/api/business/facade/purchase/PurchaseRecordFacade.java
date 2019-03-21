package com.yc.trip.api.business.facade.purchase;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.purchase.PurchaseRecord;

/**
 * 服务购买信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:11
 */
public interface PurchaseRecordFacade {

    /**
     * 新增服务购买信息
     * @throws PendingException 
     */
    PurchaseRecord addPurchaseRecord(PurchaseRecord purchaseRecord) throws PendingException;

    /**
     * 修改服务购买信息
     * @throws PendingException 
     */
    PurchaseRecord updatePurchaseRecord(PurchaseRecord purchaseRecord) throws PendingException;
    
    /**
     * 查询服务购买信息
     * @throws PendingException 
     */
    PurchaseRecord getPurchaseRecord(PurchaseRecord purchaseRecord) throws PendingException;
    
    /**
     * 查询服务购买信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    PurchaseRecord mustGet(PurchaseRecord purchaseRecord) throws PendingException;

    /**
     * 查询服务购买信息列表
     * @throws PendingException 
     */
    List<PurchaseRecord> queryPurchaseRecordList(PurchaseRecord purchaseRecord) throws PendingException;

    /**
     * 查询服务购买信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<PurchaseRecord> queryPurchaseRecordPage(PurchaseRecord purchaseRecord) throws PendingException;

}