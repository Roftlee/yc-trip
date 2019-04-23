package com.yc.trip.api.business.facade.store;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.item.store.StoreItem;
import com.yc.trip.api.business.request.store.StoreAddRequest;
import com.yc.trip.api.business.request.store.StorePageRequest;
import com.yc.trip.api.business.request.store.StoreUpdateRequest;
import org.go.framework.core.exception.PendingException;

/**
 * 门店信息相关接口
 *
 * @author My-Toolkits
 * @since 2019-03-21 22:22
 */
public interface StoreProfFacade {

    /**
     * 新增门店信息
     *
     * @throws PendingException
     */
    void addStore(StoreAddRequest request) throws PendingException;

    /**
     * 修改门店信息
     *
     * @throws PendingException
     */
    void updateStore(StoreUpdateRequest request) throws PendingException;

    /**
     * 查询门店信息列表 ,分页查询
     *
     * @throws PendingException
     */
    PageInfo<StoreItem> queryStorePage(StorePageRequest request) throws PendingException;

}