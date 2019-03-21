package com.yc.trip.api.business.dao.product;

import java.util.List;

import com.yc.trip.api.business.bo.product.ProductCommentDomain;

/**
 * 产品评论信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:00
 *
 */
public interface ProductCommentDao {

    /**
     * 新增产品评论信息
     */
    void addProductComment(ProductCommentDomain productCommentDomain);

    /**
     * 修改产品评论信息
     */
    void updateProductComment(ProductCommentDomain productCommentDomain);
    
    /**
     * 查询产品评论信息
     */
    ProductCommentDomain getProductComment(ProductCommentDomain productCommentDomain);

    /**
     * 查询产品评论信息列表
     */
    List<ProductCommentDomain> queryProductCommentList(ProductCommentDomain productCommentDomain);

}