package com.yc.trip.api.business.facade.store;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.store.StoreSales;

/**
 * 门店销售信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:24
 */
public interface StoreSalesFacade {

    /**
     * 新增门店销售信息
     * @throws PendingException 
     */
    StoreSales addStoreSales(StoreSales storeSales) throws PendingException;

    /**
     * 修改门店销售信息
     * @throws PendingException 
     */
    StoreSales updateStoreSales(StoreSales storeSales) throws PendingException;
    
    /**
     * 查询门店销售信息
     * @throws PendingException 
     */
    StoreSales getStoreSales(StoreSales storeSales) throws PendingException;
    
    /**
     * 查询门店销售信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    StoreSales mustGet(StoreSales storeSales) throws PendingException;

    /**
     * 查询门店销售信息列表
     * @throws PendingException 
     */
    List<StoreSales> queryStoreSalesList(StoreSales storeSales) throws PendingException;

    /**
     * 查询门店销售信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<StoreSales> queryStoreSalesPage(StoreSales storeSales) throws PendingException;

}