package com.yc.trip.api.business.facade.wx;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.wx.WxApp;

/**
 * 微信小程序信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:39
 */
public interface WxAppFacade {

    /**
     * 新增微信小程序信息
     * @throws PendingException 
     */
    WxApp addWxApp(WxApp wxApp) throws PendingException;

    /**
     * 修改微信小程序信息
     * @throws PendingException 
     */
    WxApp updateWxApp(WxApp wxApp) throws PendingException;
    
    /**
     * 查询微信小程序信息
     * @throws PendingException 
     */
    WxApp getWxApp(WxApp wxApp) throws PendingException;
    
    /**
     * 查询微信小程序信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    WxApp mustGet(WxApp wxApp) throws PendingException;

    /**
     * 查询微信小程序信息列表
     * @throws PendingException 
     */
    List<WxApp> queryWxAppList(WxApp wxApp) throws PendingException;

    /**
     * 查询微信小程序信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<WxApp> queryWxAppPage(WxApp wxApp) throws PendingException;

}