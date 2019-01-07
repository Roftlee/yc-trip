package com.yc.trip.api.business.facade.store;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.store.StoreSales;
import com.yc.trip.api.business.query.store.StoreSalesQuery;

/**
 * 门店销售信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:54
 */
public interface StoreSalesFacade {

    /**
     * 新增门店销售信息
     * @throws PendingException 
     */
    StoreSales add(StoreSales storeSales) throws PendingException;

    /**
     * 修改门店销售信息
     * @throws PendingException 
     */
    void update(StoreSales storeSales) throws PendingException;
    
    /**
     * 查询门店销售信息
     * @throws PendingException 
     */
    StoreSales get(StoreSalesQuery storeSalesQuery) throws PendingException;
    
    /**
     * 查询门店销售信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    StoreSales mustGet(StoreSalesQuery storeSalesQuery) throws PendingException;

    /**
     * 查询门店销售信息列表
     * @throws PendingException 
     */
    List<StoreSales> queryList(StoreSalesQuery storeSalesQuery) throws PendingException;

    /**
     * 查询门店销售信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<StoreSales> queryPage(StoreSalesQuery storeSalesQuery) throws PendingException;

}