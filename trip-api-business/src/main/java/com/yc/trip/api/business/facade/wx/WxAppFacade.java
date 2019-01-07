package com.yc.trip.api.business.facade.wx;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.wx.WxApp;
import com.yc.trip.api.business.query.wx.WxAppQuery;

/**
 * 微信小程序信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 19:05
 */
public interface WxAppFacade {

    /**
     * 新增微信小程序信息
     * @throws PendingException 
     */
    WxApp add(WxApp wxApp) throws PendingException;

    /**
     * 修改微信小程序信息
     * @throws PendingException 
     */
    void update(WxApp wxApp) throws PendingException;
    
    /**
     * 查询微信小程序信息
     * @throws PendingException 
     */
    WxApp get(WxAppQuery wxAppQuery) throws PendingException;
    
    /**
     * 查询微信小程序信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    WxApp mustGet(WxAppQuery wxAppQuery) throws PendingException;

    /**
     * 查询微信小程序信息列表
     * @throws PendingException 
     */
    List<WxApp> queryList(WxAppQuery wxAppQuery) throws PendingException;

    /**
     * 查询微信小程序信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<WxApp> queryPage(WxAppQuery wxAppQuery) throws PendingException;

}