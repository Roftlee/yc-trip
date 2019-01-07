package com.yc.trip.api.business.dao.product;

import java.util.List;

import com.yc.trip.api.business.dto.product.ProductComment;
import com.yc.trip.api.business.query.product.ProductCommentQuery;

/**
 * 产品评论信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:17
 *
 */
public interface ProductCommentDao {

    /**
     * 产品评论信息新增
     */
    void add(ProductComment productComment);

    /**
     * 产品评论信息修改
     */
    void update(ProductComment productComment);
    
    /**
     * 产品评论信息查询
     */
    ProductComment get(ProductCommentQuery productCommentQuery);

    /**
     * 产品评论信息列表查询
     */
    List<ProductComment> queryList(ProductCommentQuery productCommentQuery);

}