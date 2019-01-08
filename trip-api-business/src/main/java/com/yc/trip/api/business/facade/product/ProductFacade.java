package com.yc.trip.api.business.facade.product;

import java.util.List;

import com.yc.trip.api.business.request.common.PageRequest;
import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.query.product.ProductQuery;

/**
 * 产品信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:16
 */
public interface ProductFacade {

    /**
     * 新增产品信息
     * @throws PendingException 
     */
    Product add(Product product) throws PendingException;

    /**
     * 修改产品信息
     * @throws PendingException 
     */
    void update(Product product) throws PendingException;
    
    /**
     * 查询产品信息
     * @throws PendingException 
     */
    Product get(ProductQuery productQuery) throws PendingException;
    
    /**
     * 查询产品信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Product mustGet(ProductQuery productQuery) throws PendingException;

    /**
     * 查询产品信息列表
     * @throws PendingException 
     */
    List<Product> queryList(ProductQuery productQuery) throws PendingException;

    /**
     * 查询产品信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Product> queryPage(ProductQuery productQuery) throws PendingException;

    /**
     * 随机查询产品列表
     */
    List<Product> queryProductListRandom(PageRequest pageRequest) throws PendingException;

    /**
     * 随机查询产品列表(分页查询)
     */
    PageInfo<Product> queryProductListRandomPage(PageRequest pageRequest) throws PendingException;

}