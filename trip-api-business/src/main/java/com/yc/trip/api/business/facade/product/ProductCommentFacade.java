package com.yc.trip.api.business.facade.product;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.ProductComment;
import com.yc.trip.api.business.query.product.ProductCommentQuery;

/**
 * 产品评论信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:17
 */
public interface ProductCommentFacade {

    /**
     * 新增产品评论信息
     * @throws PendingException 
     */
    ProductComment add(ProductComment productComment) throws PendingException;

    /**
     * 修改产品评论信息
     * @throws PendingException 
     */
    void update(ProductComment productComment) throws PendingException;
    
    /**
     * 查询产品评论信息
     * @throws PendingException 
     */
    ProductComment get(ProductCommentQuery productCommentQuery) throws PendingException;
    
    /**
     * 查询产品评论信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    ProductComment mustGet(ProductCommentQuery productCommentQuery) throws PendingException;

    /**
     * 查询产品评论信息列表
     * @throws PendingException 
     */
    List<ProductComment> queryList(ProductCommentQuery productCommentQuery) throws PendingException;

    /**
     * 查询产品评论信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<ProductComment> queryPage(ProductCommentQuery productCommentQuery) throws PendingException;

}