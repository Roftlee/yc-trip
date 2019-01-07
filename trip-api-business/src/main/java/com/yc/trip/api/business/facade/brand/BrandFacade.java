package com.yc.trip.api.business.facade.brand;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.brand.Brand;
import com.yc.trip.api.business.query.brand.BrandQuery;

/**
 * 品牌相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:03
 */
public interface BrandFacade {

    /**
     * 新增品牌
     * @throws PendingException 
     */
    Brand add(Brand brand) throws PendingException;

    /**
     * 修改品牌
     * @throws PendingException 
     */
    void update(Brand brand) throws PendingException;
    
    /**
     * 查询品牌
     * @throws PendingException 
     */
    Brand get(BrandQuery brandQuery) throws PendingException;
    
    /**
     * 查询品牌（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Brand mustGet(BrandQuery brandQuery) throws PendingException;

    /**
     * 查询品牌列表
     * @throws PendingException 
     */
    List<Brand> queryList(BrandQuery brandQuery) throws PendingException;

    /**
     * 查询品牌列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Brand> queryPage(BrandQuery brandQuery) throws PendingException;

}