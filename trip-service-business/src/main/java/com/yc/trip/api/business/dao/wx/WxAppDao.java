package com.yc.trip.api.business.dao.wx;

import java.util.List;

import com.yc.trip.api.business.dto.wx.WxApp;
import com.yc.trip.api.business.query.wx.WxAppQuery;

/**
 * 微信小程序信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 19:05
 *
 */
public interface WxAppDao {

    /**
     * 微信小程序信息新增
     */
    void add(WxApp wxApp);

    /**
     * 微信小程序信息修改
     */
    void update(WxApp wxApp);
    
    /**
     * 微信小程序信息查询
     */
    WxApp get(WxAppQuery wxAppQuery);

    /**
     * 微信小程序信息列表查询
     */
    List<WxApp> queryList(WxAppQuery wxAppQuery);

}