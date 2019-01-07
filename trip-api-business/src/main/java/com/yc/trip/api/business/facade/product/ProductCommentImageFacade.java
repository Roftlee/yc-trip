package com.yc.trip.api.business.facade.product;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.ProductCommentImage;
import com.yc.trip.api.business.query.product.ProductCommentImageQuery;

/**
 * 产品评论图片信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:20
 */
public interface ProductCommentImageFacade {

    /**
     * 新增产品评论图片信息
     * @throws PendingException 
     */
    ProductCommentImage add(ProductCommentImage productCommentImage) throws PendingException;

    /**
     * 修改产品评论图片信息
     * @throws PendingException 
     */
    void update(ProductCommentImage productCommentImage) throws PendingException;
    
    /**
     * 查询产品评论图片信息
     * @throws PendingException 
     */
    ProductCommentImage get(ProductCommentImageQuery productCommentImageQuery) throws PendingException;
    
    /**
     * 查询产品评论图片信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    ProductCommentImage mustGet(ProductCommentImageQuery productCommentImageQuery) throws PendingException;

    /**
     * 查询产品评论图片信息列表
     * @throws PendingException 
     */
    List<ProductCommentImage> queryList(ProductCommentImageQuery productCommentImageQuery) throws PendingException;

    /**
     * 查询产品评论图片信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<ProductCommentImage> queryPage(ProductCommentImageQuery productCommentImageQuery) throws PendingException;

}