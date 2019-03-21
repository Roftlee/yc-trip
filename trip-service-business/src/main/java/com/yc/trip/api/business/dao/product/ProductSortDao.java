package com.yc.trip.api.business.dao.product;

import java.util.List;

import com.yc.trip.api.business.bo.product.ProductSortDomain;

/**
 * 产品分类信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:07
 *
 */
public interface ProductSortDao {

    /**
     * 新增产品分类信息
     */
    void addProductSort(ProductSortDomain productSortDomain);

    /**
     * 修改产品分类信息
     */
    void updateProductSort(ProductSortDomain productSortDomain);
    
    /**
     * 查询产品分类信息
     */
    ProductSortDomain getProductSort(ProductSortDomain productSortDomain);

    /**
     * 查询产品分类信息列表
     */
    List<ProductSortDomain> queryProductSortList(ProductSortDomain productSortDomain);

}