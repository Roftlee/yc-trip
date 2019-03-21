package com.yc.trip.api.business.facade.guide;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.guide.TripGuide;

/**
 * 旅游攻略信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:27
 */
public interface TripGuideFacade {

    /**
     * 新增旅游攻略信息
     * @throws PendingException 
     */
    TripGuide addTripGuide(TripGuide tripGuide) throws PendingException;

    /**
     * 修改旅游攻略信息
     * @throws PendingException 
     */
    TripGuide updateTripGuide(TripGuide tripGuide) throws PendingException;
    
    /**
     * 查询旅游攻略信息
     * @throws PendingException 
     */
    TripGuide getTripGuide(TripGuide tripGuide) throws PendingException;
    
    /**
     * 查询旅游攻略信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    TripGuide mustGet(TripGuide tripGuide) throws PendingException;

    /**
     * 查询旅游攻略信息列表
     * @throws PendingException 
     */
    List<TripGuide> queryTripGuideList(TripGuide tripGuide) throws PendingException;

    /**
     * 查询旅游攻略信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<TripGuide> queryTripGuidePage(TripGuide tripGuide) throws PendingException;

}