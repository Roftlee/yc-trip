package com.yc.trip.api.business.facade.product;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.request.common.PageRequest;
import org.go.framework.core.exception.PendingException;

import java.util.List;

/**
 * 产品信息相关接口
 *
 * @author My-Toolkits
 * @since 2019-03-21 21:50
 */
public interface ProductFacade {

    /**
     * 新增产品信息
     *
     * @throws PendingException
     */
    Product addProduct(Product product) throws PendingException;

    /**
     * 修改产品信息
     *
     * @throws PendingException
     */
    Product updateProduct(Product product) throws PendingException;

    /**
     * 查询产品信息
     *
     * @throws PendingException
     */
    Product getProduct(Product product) throws PendingException;

    /**
     * 查询产品信息（若不存在，则抛出异常）
     *
     * @throws PendingException
     */
    Product mustGet(Product product) throws PendingException;

    /**
     * 查询产品信息列表
     *
     * @throws PendingException
     */
    List<Product> queryProductList(Product product) throws PendingException;

    /**
     * 查询产品信息列表 ,分页查询
     *
     * @throws PendingException
     */
    PageInfo<Product> queryProductPage(Product product) throws PendingException;

    /**
     * 随机查询产品列表
     */
    List<Product> queryProductListRandom(PageRequest pageRequest) throws PendingException;

    /**
     * 随机查询产品列表，分页查询
     */
    PageInfo<Product> queryProductListRandomPage(PageRequest pageRequest) throws PendingException;

}