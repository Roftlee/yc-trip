package com.yc.trip.api.business.facade.region;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.region.Region;
import com.yc.trip.api.business.query.region.RegionQuery;

/**
 * 地区信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:29
 */
public interface RegionFacade {

    /**
     * 新增地区信息
     * @throws PendingException 
     */
    Region add(Region region) throws PendingException;

    /**
     * 修改地区信息
     * @throws PendingException 
     */
    void update(Region region) throws PendingException;
    
    /**
     * 查询地区信息
     * @throws PendingException 
     */
    Region get(RegionQuery regionQuery) throws PendingException;
    
    /**
     * 查询地区信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Region mustGet(RegionQuery regionQuery) throws PendingException;

    /**
     * 查询地区信息列表
     * @throws PendingException 
     */
    List<Region> queryList(RegionQuery regionQuery) throws PendingException;

    /**
     * 查询地区信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Region> queryPage(RegionQuery regionQuery) throws PendingException;

}