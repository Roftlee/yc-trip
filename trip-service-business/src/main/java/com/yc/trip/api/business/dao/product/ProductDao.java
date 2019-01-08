package com.yc.trip.api.business.dao.product;

import java.util.List;

import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.query.product.ProductQuery;
import com.yc.trip.api.business.request.common.PageRequest;

/**
 * 产品信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:16
 *
 */
public interface ProductDao {

    /**
     * 产品信息新增
     */
    void add(Product product);

    /**
     * 产品信息修改
     */
    void update(Product product);
    
    /**
     * 产品信息查询
     */
    Product get(ProductQuery productQuery);

    /**
     * 产品信息列表查询
     */
    List<Product> queryList(ProductQuery productQuery);

    /**
     * 随机查询产品列表
     */
    List<Product> queryProductListRandom(PageRequest pageRequest);
}