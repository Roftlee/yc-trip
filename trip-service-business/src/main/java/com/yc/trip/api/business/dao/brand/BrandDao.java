package com.yc.trip.api.business.dao.brand;

import java.util.List;

import com.yc.trip.api.business.bo.brand.BrandDomain;

/**
 * 品牌Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:31
 *
 */
public interface BrandDao {

    /**
     * 新增品牌
     */
    void addBrand(BrandDomain brandDomain);

    /**
     * 修改品牌
     */
    void updateBrand(BrandDomain brandDomain);
    
    /**
     * 查询品牌
     */
    BrandDomain getBrand(BrandDomain brandDomain);

    /**
     * 查询品牌列表
     */
    List<BrandDomain> queryBrandList(BrandDomain brandDomain);

}