package com.yc.trip.api.business.facade.guide;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.guide.TripGuide;
import com.yc.trip.api.business.query.guide.TripGuideQuery;

/**
 * 旅游攻略信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:57
 */
public interface TripGuideFacade {

    /**
     * 新增旅游攻略信息
     * @throws PendingException 
     */
    TripGuide add(TripGuide tripGuide) throws PendingException;

    /**
     * 修改旅游攻略信息
     * @throws PendingException 
     */
    void update(TripGuide tripGuide) throws PendingException;
    
    /**
     * 查询旅游攻略信息
     * @throws PendingException 
     */
    TripGuide get(TripGuideQuery tripGuideQuery) throws PendingException;
    
    /**
     * 查询旅游攻略信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    TripGuide mustGet(TripGuideQuery tripGuideQuery) throws PendingException;

    /**
     * 查询旅游攻略信息列表
     * @throws PendingException 
     */
    List<TripGuide> queryList(TripGuideQuery tripGuideQuery) throws PendingException;

    /**
     * 查询旅游攻略信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<TripGuide> queryPage(TripGuideQuery tripGuideQuery) throws PendingException;

}