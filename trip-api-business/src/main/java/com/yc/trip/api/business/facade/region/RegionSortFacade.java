package com.yc.trip.api.business.facade.region;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.region.RegionSort;
import com.yc.trip.api.business.query.region.RegionSortQuery;

/**
 * 地区分类信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:30
 */
public interface RegionSortFacade {

    /**
     * 新增地区分类信息
     * @throws PendingException 
     */
    RegionSort add(RegionSort regionSort) throws PendingException;

    /**
     * 修改地区分类信息
     * @throws PendingException 
     */
    void update(RegionSort regionSort) throws PendingException;
    
    /**
     * 查询地区分类信息
     * @throws PendingException 
     */
    RegionSort get(RegionSortQuery regionSortQuery) throws PendingException;
    
    /**
     * 查询地区分类信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    RegionSort mustGet(RegionSortQuery regionSortQuery) throws PendingException;

    /**
     * 查询地区分类信息列表
     * @throws PendingException 
     */
    List<RegionSort> queryList(RegionSortQuery regionSortQuery) throws PendingException;

    /**
     * 查询地区分类信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<RegionSort> queryPage(RegionSortQuery regionSortQuery) throws PendingException;

}