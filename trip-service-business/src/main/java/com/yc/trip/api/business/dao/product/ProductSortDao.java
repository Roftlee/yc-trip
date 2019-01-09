package com.yc.trip.api.business.dao.product;

import java.util.List;

import com.yc.trip.api.business.dto.product.ProductSort;
import com.yc.trip.api.business.query.product.ProductSortQuery;

/**
 * 产品分类信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-09 19:52
 *
 */
public interface ProductSortDao {

    /**
     * 产品分类信息新增
     */
    void add(ProductSort productSort);

    /**
     * 产品分类信息修改
     */
    void update(ProductSort productSort);
    
    /**
     * 产品分类信息查询
     */
    ProductSort get(ProductSortQuery productSortQuery);

    /**
     * 产品分类信息列表查询
     */
    List<ProductSort> queryList(ProductSortQuery productSortQuery);

}