package com.yc.trip.api.business.service.wx;

import java.util.List;


import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.business.bo.wx.WxAppDomain;
import com.yc.trip.api.business.dao.wx.WxAppDao;
import com.yc.trip.api.business.dto.wx.WxApp;
import com.yc.trip.api.business.facade.wx.WxAppFacade;

/**
 * 微信小程序信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:39
 */
@Service(version = "1.0.0")
public class WxAppFacadeImpl extends AbstractDubboNativeService implements WxAppFacade {

    @Autowired
    private WxAppDao wxAppDao;

    /**
     * 新增微信小程序信息
     * 
     * @throws PendingException
     */
    @Override
    public WxApp addWxApp(WxApp wxApp) throws PendingException {
        try {
            // 转换成domain对象
            WxAppDomain cond = BeanMapping.map(wxApp, WxAppDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.wxAppDBParamInvalid.throwException("微信小程序信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            wxAppDao.addWxApp(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, WxApp.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.wxAppDBError, "微信小程序信息新增失败");
        }
    }

    /**
     * 修改微信小程序信息
     * 
     * @throws PendingException
     */
    @Override
    public WxApp updateWxApp(WxApp wxApp) throws PendingException {
        try {
            // 转换成domain对象
            WxAppDomain cond = BeanMapping.map(wxApp, WxAppDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.wxAppDBParamInvalid.throwException("微信小程序信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            wxAppDao.updateWxApp(cond);
            return BeanMapping.map(cond, WxApp.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.wxAppDBError, "微信小程序信息更新失败");
        }
    }

    /**
     * 查询微信小程序信息
     * 
     * @throws PendingException
     */
    @Override
    public WxApp getWxApp(WxApp wxApp) throws PendingException {
        try {
            // 转换成Domain对象
            WxAppDomain cond = BeanMapping.map(wxApp, WxAppDomain.class);
            // 调数据库接口查询对象
            WxAppDomain resultBean = wxAppDao.getWxApp(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, WxApp.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.wxAppDBError, "微信小程序信息查询失败");
        }
    }
    
    /**
     * 查询微信小程序信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public WxApp mustGet(WxApp wxApp) throws PendingException {
        // 查询单位信息
        WxApp result = getWxApp(wxApp);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.wxAppDBGetNull.throwException("未查询到微信小程序信息");
        }
        return result;
    }

    /**
     * 查询微信小程序信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<WxApp> queryWxAppList(WxApp wxApp) throws PendingException {
        try {
            // 转换成Domain对象
            WxAppDomain cond = BeanMapping.map(wxApp, WxAppDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(wxApp.getOrderby())) {
                PageHelper.orderBy(wxApp.getOrderby());
            }
            // 调数据库接口查询列表
            List<WxAppDomain> resultList = wxAppDao.queryWxAppList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, WxApp.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.wxAppDBError, "微信小程序信息列表查询失败");
        }
    }

    /**
     * 查询微信小程序信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<WxApp> queryWxAppPage(WxApp wxApp) throws PendingException {
        try {
            // 对请求参数进行校验
            if (wxApp.getPageNo() <= 0 || wxApp.getPageSize() <= 0) {
                ResCode.wxAppDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(wxApp.getPageNo(), wxApp.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(wxApp.getOrderby())) {
                PageHelper.orderBy(wxApp.getOrderby());
            }
            // 转换成Domain对象
            WxAppDomain cond = BeanMapping.map(wxApp, WxAppDomain.class);
            // 调数据库接口查询列表
            List<WxAppDomain> resultList = wxAppDao.queryWxAppList(cond);
            // 生成分页对象
            PageInfo<WxAppDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, WxApp.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.wxAppDBError, "微信小程序信息分页查询失败");
        }
    }

}
