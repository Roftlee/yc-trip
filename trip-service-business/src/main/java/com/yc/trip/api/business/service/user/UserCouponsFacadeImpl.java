package com.yc.trip.api.business.service.user;

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
import com.yc.trip.api.business.dao.user.UserCouponsDao;
import com.yc.trip.api.business.dto.user.UserCoupons;
import com.yc.trip.api.business.query.user.UserCouponsQuery;
import com.yc.trip.api.business.facade.user.UserCouponsFacade;

/**
 * 用户优惠券相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-08 23:28
 */
@Service(version = "1.0.0")
public class UserCouponsFacadeImpl extends AbstractDubboNativeService implements UserCouponsFacade {

    @Autowired
    private UserCouponsDao userCouponsDao;

    /**
     * 新增用户优惠券
     * 
     * @throws PendingException
     */
    @Override
    public UserCoupons add(UserCoupons userCoupons) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            userCoupons.validateInsertFields();
            // 调数据库接口进行新增操作
            userCouponsDao.add(userCoupons);
            // 将新增后回返回（包含自增主键值）
            return userCoupons;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userCouponsDBError, "用户优惠券新增失败");
        }
    }

    /**
     * 修改用户优惠券
     * 
     * @throws PendingException
     */
    @Override
    public void update(UserCoupons userCoupons) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (userCoupons.isAllFiledsNull()) {
                ResCode.userCouponsDBParamInvalid.throwException("用户优惠券更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            userCouponsDao.update(userCoupons);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userCouponsDBError, "用户优惠券更新失败");
        }
    }

    /**
     * 查询用户优惠券
     * 
     * @throws PendingException
     */
    @Override
    public UserCoupons get(UserCouponsQuery userCouponsQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return userCouponsDao.get(userCouponsQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userCouponsDBError, "用户优惠券查询失败");
        }
    }
    
    /**
     * 查询用户优惠券信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public UserCoupons mustGet(UserCouponsQuery userCouponsQuery) throws PendingException {
        // 查询单位信息
        UserCoupons result = get(userCouponsQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.userCouponsDBGetNull.throwException("未查询到用户优惠券");
        }
        return result;
    }

    /**
     * 查询用户优惠券列表
     * 
     * @throws PendingException
     */
    @Override
    public List<UserCoupons> queryList(UserCouponsQuery userCouponsQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userCouponsQuery.getOrderby())) {
                PageHelper.orderBy(userCouponsQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return userCouponsDao.queryList(userCouponsQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userCouponsDBError, "用户优惠券列表查询失败");
        }
    }

    /**
     * 查询用户优惠券列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<UserCoupons> queryPage(UserCouponsQuery userCouponsQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (userCouponsQuery.getPageNo() <= 0 || userCouponsQuery.getPageSize() <= 0) {
                ResCode.userCouponsDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(userCouponsQuery.getPageNo(), userCouponsQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userCouponsQuery.getOrderby())) {
                PageHelper.orderBy(userCouponsQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<UserCoupons> resultList = userCouponsDao.queryList(userCouponsQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userCouponsDBError, "用户优惠券分页查询失败");
        }
    }

}
