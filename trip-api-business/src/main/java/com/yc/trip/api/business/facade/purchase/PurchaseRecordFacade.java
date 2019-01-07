package com.yc.trip.api.business.facade.purchase;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.purchase.PurchaseRecord;
import com.yc.trip.api.business.query.purchase.PurchaseRecordQuery;

/**
 * 服务购买信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:27
 */
public interface PurchaseRecordFacade {

    /**
     * 新增服务购买信息
     * @throws PendingException 
     */
    PurchaseRecord add(PurchaseRecord purchaseRecord) throws PendingException;

    /**
     * 修改服务购买信息
     * @throws PendingException 
     */
    void update(PurchaseRecord purchaseRecord) throws PendingException;
    
    /**
     * 查询服务购买信息
     * @throws PendingException 
     */
    PurchaseRecord get(PurchaseRecordQuery purchaseRecordQuery) throws PendingException;
    
    /**
     * 查询服务购买信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    PurchaseRecord mustGet(PurchaseRecordQuery purchaseRecordQuery) throws PendingException;

    /**
     * 查询服务购买信息列表
     * @throws PendingException 
     */
    List<PurchaseRecord> queryList(PurchaseRecordQuery purchaseRecordQuery) throws PendingException;

    /**
     * 查询服务购买信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<PurchaseRecord> queryPage(PurchaseRecordQuery purchaseRecordQuery) throws PendingException;

}