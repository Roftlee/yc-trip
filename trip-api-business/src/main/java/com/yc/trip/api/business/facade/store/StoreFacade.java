package com.yc.trip.api.business.facade.store;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.store.Store;

/**
 * 门店信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:22
 */
public interface StoreFacade {

    /**
     * 新增门店信息
     * @throws PendingException 
     */
    Store addStore(Store store) throws PendingException;

    /**
     * 修改门店信息
     * @throws PendingException 
     */
    Store updateStore(Store store) throws PendingException;
    
    /**
     * 查询门店信息
     * @throws PendingException 
     */
    Store getStore(Store store) throws PendingException;
    
    /**
     * 查询门店信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Store mustGet(Store store) throws PendingException;

    /**
     * 查询门店信息列表
     * @throws PendingException 
     */
    List<Store> queryStoreList(Store store) throws PendingException;

    /**
     * 查询门店信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Store> queryStorePage(Store store) throws PendingException;

}