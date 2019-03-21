package com.yc.trip.api.business.dao.product;

import java.util.List;

import com.yc.trip.api.business.bo.product.ProductDomain;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.request.common.PageRequest;

/**
 * 产品信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:50
 *
 */
public interface ProductDao {

    /**
     * 新增产品信息
     */
    void addProduct(ProductDomain productDomain);

    /**
     * 修改产品信息
     */
    void updateProduct(ProductDomain productDomain);
    
    /**
     * 查询产品信息
     */
    ProductDomain getProduct(ProductDomain productDomain);

    /**
     * 查询产品信息列表
     */
    List<ProductDomain> queryProductList(ProductDomain productDomain);

    /**
     * 随机查询产品列表
     */
    List<Product> queryProductListRandom(PageRequest pageRequest);
}