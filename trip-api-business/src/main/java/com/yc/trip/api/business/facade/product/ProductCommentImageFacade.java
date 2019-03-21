package com.yc.trip.api.business.facade.product;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.product.ProductCommentImage;

/**
 * 产品评论图片信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:06
 */
public interface ProductCommentImageFacade {

    /**
     * 新增产品评论图片信息
     * @throws PendingException 
     */
    ProductCommentImage addProductCommentImage(ProductCommentImage productCommentImage) throws PendingException;

    /**
     * 修改产品评论图片信息
     * @throws PendingException 
     */
    ProductCommentImage updateProductCommentImage(ProductCommentImage productCommentImage) throws PendingException;
    
    /**
     * 查询产品评论图片信息
     * @throws PendingException 
     */
    ProductCommentImage getProductCommentImage(ProductCommentImage productCommentImage) throws PendingException;
    
    /**
     * 查询产品评论图片信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    ProductCommentImage mustGet(ProductCommentImage productCommentImage) throws PendingException;

    /**
     * 查询产品评论图片信息列表
     * @throws PendingException 
     */
    List<ProductCommentImage> queryProductCommentImageList(ProductCommentImage productCommentImage) throws PendingException;

    /**
     * 查询产品评论图片信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<ProductCommentImage> queryProductCommentImagePage(ProductCommentImage productCommentImage) throws PendingException;

}