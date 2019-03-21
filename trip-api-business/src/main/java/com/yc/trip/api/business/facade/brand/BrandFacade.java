package com.yc.trip.api.business.facade.brand;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.brand.Brand;

/**
 * 品牌相关接口
 * @author My-Toolkits
 * @since 2019-03-21 21:31
 */
public interface BrandFacade {

    /**
     * 新增品牌
     * @throws PendingException 
     */
    Brand addBrand(Brand brand) throws PendingException;

    /**
     * 修改品牌
     * @throws PendingException 
     */
    Brand updateBrand(Brand brand) throws PendingException;
    
    /**
     * 查询品牌
     * @throws PendingException 
     */
    Brand getBrand(Brand brand) throws PendingException;
    
    /**
     * 查询品牌（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Brand mustGet(Brand brand) throws PendingException;

    /**
     * 查询品牌列表
     * @throws PendingException 
     */
    List<Brand> queryBrandList(Brand brand) throws PendingException;

    /**
     * 查询品牌列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Brand> queryBrandPage(Brand brand) throws PendingException;

}