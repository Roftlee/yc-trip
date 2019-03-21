package com.yc.trip.api.business.facade.product;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.ProductSort;

/**
 * 产品分类信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:07
 */
public interface ProductSortFacade {

    /**
     * 新增产品分类信息
     * @throws PendingException 
     */
    ProductSort addProductSort(ProductSort productSort) throws PendingException;

    /**
     * 修改产品分类信息
     * @throws PendingException 
     */
    ProductSort updateProductSort(ProductSort productSort) throws PendingException;
    
    /**
     * 查询产品分类信息
     * @throws PendingException 
     */
    ProductSort getProductSort(ProductSort productSort) throws PendingException;
    
    /**
     * 查询产品分类信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    ProductSort mustGet(ProductSort productSort) throws PendingException;

    /**
     * 查询产品分类信息列表
     * @throws PendingException 
     */
    List<ProductSort> queryProductSortList(ProductSort productSort) throws PendingException;

    /**
     * 查询产品分类信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<ProductSort> queryProductSortPage(ProductSort productSort) throws PendingException;

}