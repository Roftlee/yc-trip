package com.yc.trip.api.business.dao.brand;

import java.util.List;

import com.yc.trip.api.business.dto.brand.Brand;
import com.yc.trip.api.business.query.brand.BrandQuery;

/**
 * 品牌Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:03
 *
 */
public interface BrandDao {

    /**
     * 品牌新增
     */
    void add(Brand brand);

    /**
     * 品牌修改
     */
    void update(Brand brand);
    
    /**
     * 品牌查询
     */
    Brand get(BrandQuery brandQuery);

    /**
     * 品牌列表查询
     */
    List<Brand> queryList(BrandQuery brandQuery);

}