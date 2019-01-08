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
import com.yc.trip.api.business.dao.coupons.CouponsDao;
import com.yc.trip.api.business.dto.coupons.Coupons;
import com.yc.trip.api.business.query.coupons.CouponsQuery;
import com.yc.trip.api.business.facade.coupons.CouponsFacade;

/**
 * 优惠券相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-08 23:25
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
    public Coupons add(Coupons coupons) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            coupons.validateInsertFields();
            // 调数据库接口进行新增操作
            couponsDao.add(coupons);
            // 将新增后回返回（包含自增主键值）
            return coupons;
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
    public void update(Coupons coupons) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (coupons.isAllFiledsNull()) {
                ResCode.couponsDBParamInvalid.throwException("优惠券更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            couponsDao.update(coupons);
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
    public Coupons get(CouponsQuery couponsQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return couponsDao.get(couponsQuery);
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
    public Coupons mustGet(CouponsQuery couponsQuery) throws PendingException {
        // 查询单位信息
        Coupons result = get(couponsQuery);
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
    public List<Coupons> queryList(CouponsQuery couponsQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(couponsQuery.getOrderby())) {
                PageHelper.orderBy(couponsQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return couponsDao.queryList(couponsQuery);
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
    public PageInfo<Coupons> queryPage(CouponsQuery couponsQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (couponsQuery.getPageNo() <= 0 || couponsQuery.getPageSize() <= 0) {
                ResCode.couponsDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(couponsQuery.getPageNo(), couponsQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(couponsQuery.getOrderby())) {
                PageHelper.orderBy(couponsQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<Coupons> resultList = couponsDao.queryList(couponsQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.couponsDBError, "优惠券分页查询失败");
        }
    }

}
