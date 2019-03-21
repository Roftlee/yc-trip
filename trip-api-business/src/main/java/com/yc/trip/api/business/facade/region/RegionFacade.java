package com.yc.trip.api.business.facade.region;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.region.Region;

/**
 * 地区信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:13
 */
public interface RegionFacade {

    /**
     * 新增地区信息
     * @throws PendingException 
     */
    Region addRegion(Region region) throws PendingException;

    /**
     * 修改地区信息
     * @throws PendingException 
     */
    Region updateRegion(Region region) throws PendingException;
    
    /**
     * 查询地区信息
     * @throws PendingException 
     */
    Region getRegion(Region region) throws PendingException;
    
    /**
     * 查询地区信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Region mustGet(Region region) throws PendingException;

    /**
     * 查询地区信息列表
     * @throws PendingException 
     */
    List<Region> queryRegionList(Region region) throws PendingException;

    /**
     * 查询地区信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Region> queryRegionPage(Region region) throws PendingException;

}