package com.yc.trip.api.business.dao.product;

import java.util.List;

import com.yc.trip.api.business.dto.product.ProductCommentImage;
import com.yc.trip.api.business.query.product.ProductCommentImageQuery;

/**
 * 产品评论图片信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:20
 *
 */
public interface ProductCommentImageDao {

    /**
     * 产品评论图片信息新增
     */
    void add(ProductCommentImage productCommentImage);

    /**
     * 产品评论图片信息修改
     */
    void update(ProductCommentImage productCommentImage);
    
    /**
     * 产品评论图片信息查询
     */
    ProductCommentImage get(ProductCommentImageQuery productCommentImageQuery);

    /**
     * 产品评论图片信息列表查询
     */
    List<ProductCommentImage> queryList(ProductCommentImageQuery productCommentImageQuery);

}