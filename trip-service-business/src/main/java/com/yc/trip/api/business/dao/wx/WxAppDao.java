package com.yc.trip.api.business.dao.wx;

import java.util.List;

import com.yc.trip.api.business.bo.wx.WxAppDomain;

/**
 * 微信小程序信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:39
 *
 */
public interface WxAppDao {

    /**
     * 新增微信小程序信息
     */
    void addWxApp(WxAppDomain wxAppDomain);

    /**
     * 修改微信小程序信息
     */
    void updateWxApp(WxAppDomain wxAppDomain);
    
    /**
     * 查询微信小程序信息
     */
    WxAppDomain getWxApp(WxAppDomain wxAppDomain);

    /**
     * 查询微信小程序信息列表
     */
    List<WxAppDomain> queryWxAppList(WxAppDomain wxAppDomain);

}