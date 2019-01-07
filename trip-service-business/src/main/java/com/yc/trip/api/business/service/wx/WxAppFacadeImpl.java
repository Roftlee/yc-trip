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
import com.yc.trip.api.business.dao.wx.WxAppDao;
import com.yc.trip.api.business.dto.wx.WxApp;
import com.yc.trip.api.business.query.wx.WxAppQuery;
import com.yc.trip.api.business.facade.wx.WxAppFacade;

/**
 * 微信小程序信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 19:05
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
    public WxApp add(WxApp wxApp) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            wxApp.validateInsertFields();
            // 调数据库接口进行新增操作
            wxAppDao.add(wxApp);
            // 将新增后回返回（包含自增主键值）
            return wxApp;
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
    public void update(WxApp wxApp) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (wxApp.isAllFiledsNull()) {
                ResCode.wxAppDBParamInvalid.throwException("微信小程序信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            wxAppDao.update(wxApp);
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
    public WxApp get(WxAppQuery wxAppQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return wxAppDao.get(wxAppQuery);
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
    public WxApp mustGet(WxAppQuery wxAppQuery) throws PendingException {
        // 查询单位信息
        WxApp result = get(wxAppQuery);
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
    public List<WxApp> queryList(WxAppQuery wxAppQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(wxAppQuery.getOrderby())) {
                PageHelper.orderBy(wxAppQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return wxAppDao.queryList(wxAppQuery);
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
    public PageInfo<WxApp> queryPage(WxAppQuery wxAppQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (wxAppQuery.getPageNo() <= 0 || wxAppQuery.getPageSize() <= 0) {
                ResCode.wxAppDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(wxAppQuery.getPageNo(), wxAppQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(wxAppQuery.getOrderby())) {
                PageHelper.orderBy(wxAppQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<WxApp> resultList = wxAppDao.queryList(wxAppQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.wxAppDBError, "微信小程序信息分页查询失败");
        }
    }

}
