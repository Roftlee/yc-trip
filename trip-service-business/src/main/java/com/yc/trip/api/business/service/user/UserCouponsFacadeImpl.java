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
import com.yc.trip.api.business.bo.user.UserCouponsDomain;
import com.yc.trip.api.business.dao.user.UserCouponsDao;
import com.yc.trip.api.business.dto.user.UserCoupons;
import com.yc.trip.api.business.facade.user.UserCouponsFacade;

/**
 * 用户优惠券相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:32
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
    public UserCoupons addUserCoupons(UserCoupons userCoupons) throws PendingException {
        try {
            // 转换成domain对象
            UserCouponsDomain cond = BeanMapping.map(userCoupons, UserCouponsDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.userCouponsDBParamInvalid.throwException("用户优惠券新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            userCouponsDao.addUserCoupons(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, UserCoupons.class);
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
    public UserCoupons updateUserCoupons(UserCoupons userCoupons) throws PendingException {
        try {
            // 转换成domain对象
            UserCouponsDomain cond = BeanMapping.map(userCoupons, UserCouponsDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.userCouponsDBParamInvalid.throwException("用户优惠券更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            userCouponsDao.updateUserCoupons(cond);
            return BeanMapping.map(cond, UserCoupons.class);
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
    public UserCoupons getUserCoupons(UserCoupons userCoupons) throws PendingException {
        try {
            // 转换成Domain对象
            UserCouponsDomain cond = BeanMapping.map(userCoupons, UserCouponsDomain.class);
            // 调数据库接口查询对象
            UserCouponsDomain resultBean = userCouponsDao.getUserCoupons(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, UserCoupons.class);
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
    public UserCoupons mustGet(UserCoupons userCoupons) throws PendingException {
        // 查询单位信息
        UserCoupons result = getUserCoupons(userCoupons);
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
    public List<UserCoupons> queryUserCouponsList(UserCoupons userCoupons) throws PendingException {
        try {
            // 转换成Domain对象
            UserCouponsDomain cond = BeanMapping.map(userCoupons, UserCouponsDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userCoupons.getOrderby())) {
                PageHelper.orderBy(userCoupons.getOrderby());
            }
            // 调数据库接口查询列表
            List<UserCouponsDomain> resultList = userCouponsDao.queryUserCouponsList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, UserCoupons.class);
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
    public PageInfo<UserCoupons> queryUserCouponsPage(UserCoupons userCoupons) throws PendingException {
        try {
            // 对请求参数进行校验
            if (userCoupons.getPageNo() <= 0 || userCoupons.getPageSize() <= 0) {
                ResCode.userCouponsDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(userCoupons.getPageNo(), userCoupons.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userCoupons.getOrderby())) {
                PageHelper.orderBy(userCoupons.getOrderby());
            }
            // 转换成Domain对象
            UserCouponsDomain cond = BeanMapping.map(userCoupons, UserCouponsDomain.class);
            // 调数据库接口查询列表
            List<UserCouponsDomain> resultList = userCouponsDao.queryUserCouponsList(cond);
            // 生成分页对象
            PageInfo<UserCouponsDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, UserCoupons.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userCouponsDBError, "用户优惠券分页查询失败");
        }
    }

}
