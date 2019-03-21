package com.yc.trip.api.business.service.coupons;

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
import com.yc.trip.api.business.bo.coupons.CouponsDomain;
import com.yc.trip.api.business.dao.coupons.CouponsDao;
import com.yc.trip.api.business.dto.coupons.Coupons;
import com.yc.trip.api.business.facade.coupons.CouponsFacade;

/**
 * 优惠券相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:34
 */
@Service(version = "1.0.0")
public class CouponsFacadeImpl extends AbstractDubboNativeService implements CouponsFacade {

    @Autowired
    private CouponsDao couponsDao;

    /**
     * 新增优惠券
     * 
     * @throws PendingException
     */
    @Override
    public Coupons addCoupons(Coupons coupons) throws PendingException {
        try {
            // 转换成domain对象
            CouponsDomain cond = BeanMapping.map(coupons, CouponsDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.couponsDBParamInvalid.throwException("优惠券新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            couponsDao.addCoupons(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Coupons.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.couponsDBError, "优惠券新增失败");
        }
    }

    /**
     * 修改优惠券
     * 
     * @throws PendingException
     */
    @Override
    public Coupons updateCoupons(Coupons coupons) throws PendingException {
        try {
            // 转换成domain对象
            CouponsDomain cond = BeanMapping.map(coupons, CouponsDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.couponsDBParamInvalid.throwException("优惠券更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            couponsDao.updateCoupons(cond);
            return BeanMapping.map(cond, Coupons.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.couponsDBError, "优惠券更新失败");
        }
    }

    /**
     * 查询优惠券
     * 
     * @throws PendingException
     */
    @Override
    public Coupons getCoupons(Coupons coupons) throws PendingException {
        try {
            // 转换成Domain对象
            CouponsDomain cond = BeanMapping.map(coupons, CouponsDomain.class);
            // 调数据库接口查询对象
            CouponsDomain resultBean = couponsDao.getCoupons(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Coupons.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.couponsDBError, "优惠券查询失败");
        }
    }
    
    /**
     * 查询优惠券信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Coupons mustGet(Coupons coupons) throws PendingException {
        // 查询单位信息
        Coupons result = getCoupons(coupons);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.couponsDBGetNull.throwException("未查询到优惠券");
        }
        return result;
    }

    /**
     * 查询优惠券列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Coupons> queryCouponsList(Coupons coupons) throws PendingException {
        try {
            // 转换成Domain对象
            CouponsDomain cond = BeanMapping.map(coupons, CouponsDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(coupons.getOrderby())) {
                PageHelper.orderBy(coupons.getOrderby());
            }
            // 调数据库接口查询列表
            List<CouponsDomain> resultList = couponsDao.queryCouponsList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Coupons.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.couponsDBError, "优惠券列表查询失败");
        }
    }

    /**
     * 查询优惠券列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Coupons> queryCouponsPage(Coupons coupons) throws PendingException {
        try {
            // 对请求参数进行校验
            if (coupons.getPageNo() <= 0 || coupons.getPageSize() <= 0) {
                ResCode.couponsDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(coupons.getPageNo(), coupons.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(coupons.getOrderby())) {
                PageHelper.orderBy(coupons.getOrderby());
            }
            // 转换成Domain对象
            CouponsDomain cond = BeanMapping.map(coupons, CouponsDomain.class);
            // 调数据库接口查询列表
            List<CouponsDomain> resultList = couponsDao.queryCouponsList(cond);
            // 生成分页对象
            PageInfo<CouponsDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Coupons.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.couponsDBError, "优惠券分页查询失败");
        }
    }

}
