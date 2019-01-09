package com.yc.trip.api.business.facade.product;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.ProductSort;
import com.yc.trip.api.business.query.product.ProductSortQuery;

/**
 * 产品分类信息相关接口
 * @author My-Toolkits
 * @since 2019-01-09 19:52
 */
public interface ProductSortFacade {

    /**
     * 新增产品分类信息
     * @throws PendingException 
     */
    ProductSort add(ProductSort productSort) throws PendingException;

    /**
     * 修改产品分类信息
     * @throws PendingException 
     */
    void update(ProductSort productSort) throws PendingException;
    
    /**
     * 查询产品分类信息
     * @throws PendingException 
     */
    ProductSort get(ProductSortQuery productSortQuery) throws PendingException;
    
    /**
     * 查询产品分类信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    ProductSort mustGet(ProductSortQuery productSortQuery) throws PendingException;

    /**
     * 查询产品分类信息列表
     * @throws PendingException 
     */
    List<ProductSort> queryList(ProductSortQuery productSortQuery) throws PendingException;

    /**
     * 查询产品分类信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<ProductSort> queryPage(ProductSortQuery productSortQuery) throws PendingException;

}