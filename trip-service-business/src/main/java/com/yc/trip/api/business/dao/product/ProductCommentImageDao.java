package com.yc.trip.api.business.dao.product;

import java.util.List;

import com.yc.trip.api.business.bo.product.ProductCommentImageDomain;

/**
 * 产品评论图片信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:06
 *
 */
public interface ProductCommentImageDao {

    /**
     * 新增产品评论图片信息
     */
    void addProductCommentImage(ProductCommentImageDomain productCommentImageDomain);

    /**
     * 修改产品评论图片信息
     */
    void updateProductCommentImage(ProductCommentImageDomain productCommentImageDomain);
    
    /**
     * 查询产品评论图片信息
     */
    ProductCommentImageDomain getProductCommentImage(ProductCommentImageDomain productCommentImageDomain);

    /**
     * 查询产品评论图片信息列表
     */
    List<ProductCommentImageDomain> queryProductCommentImageList(ProductCommentImageDomain productCommentImageDomain);

}