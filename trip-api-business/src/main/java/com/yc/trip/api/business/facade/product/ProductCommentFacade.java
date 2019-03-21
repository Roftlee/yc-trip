package com.yc.trip.api.business.facade.product;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.ProductComment;

/**
 * 产品评论信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:00
 */
public interface ProductCommentFacade {

    /**
     * 新增产品评论信息
     * @throws PendingException 
     */
    ProductComment addProductComment(ProductComment productComment) throws PendingException;

    /**
     * 修改产品评论信息
     * @throws PendingException 
     */
    ProductComment updateProductComment(ProductComment productComment) throws PendingException;
    
    /**
     * 查询产品评论信息
     * @throws PendingException 
     */
    ProductComment getProductComment(ProductComment productComment) throws PendingException;
    
    /**
     * 查询产品评论信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    ProductComment mustGet(ProductComment productComment) throws PendingException;

    /**
     * 查询产品评论信息列表
     * @throws PendingException 
     */
    List<ProductComment> queryProductCommentList(ProductComment productComment) throws PendingException;

    /**
     * 查询产品评论信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<ProductComment> queryProductCommentPage(ProductComment productComment) throws PendingException;

}