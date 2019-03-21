package com.yc.trip.api.business.service.order;

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
import com.yc.trip.api.business.bo.order.OrderCreditDomain;
import com.yc.trip.api.business.dao.order.OrderCreditDao;
import com.yc.trip.api.business.dto.order.OrderCredit;
import com.yc.trip.api.business.facade.order.OrderCreditFacade;

/**
 * 订单积分信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:45
 */
@Service(version = "1.0.0")
public class OrderCreditFacadeImpl extends AbstractDubboNativeService implements OrderCreditFacade {

    @Autowired
    private OrderCreditDao orderCreditDao;

    /**
     * 新增订单积分信息
     * 
     * @throws PendingException
     */
    @Override
    public OrderCredit addOrderCredit(OrderCredit orderCredit) throws PendingException {
        try {
            // 转换成domain对象
            OrderCreditDomain cond = BeanMapping.map(orderCredit, OrderCreditDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.orderCreditDBParamInvalid.throwException("订单积分信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            orderCreditDao.addOrderCredit(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, OrderCredit.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCreditDBError, "订单积分信息新增失败");
        }
    }

    /**
     * 修改订单积分信息
     * 
     * @throws PendingException
     */
    @Override
    public OrderCredit updateOrderCredit(OrderCredit orderCredit) throws PendingException {
        try {
            // 转换成domain对象
            OrderCreditDomain cond = BeanMapping.map(orderCredit, OrderCreditDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.orderCreditDBParamInvalid.throwException("订单积分信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            orderCreditDao.updateOrderCredit(cond);
            return BeanMapping.map(cond, OrderCredit.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCreditDBError, "订单积分信息更新失败");
        }
    }

    /**
     * 查询订单积分信息
     * 
     * @throws PendingException
     */
    @Override
    public OrderCredit getOrderCredit(OrderCredit orderCredit) throws PendingException {
        try {
            // 转换成Domain对象
            OrderCreditDomain cond = BeanMapping.map(orderCredit, OrderCreditDomain.class);
            // 调数据库接口查询对象
            OrderCreditDomain resultBean = orderCreditDao.getOrderCredit(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, OrderCredit.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCreditDBError, "订单积分信息查询失败");
        }
    }
    
    /**
     * 查询订单积分信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public OrderCredit mustGet(OrderCredit orderCredit) throws PendingException {
        // 查询单位信息
        OrderCredit result = getOrderCredit(orderCredit);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.orderCreditDBGetNull.throwException("未查询到订单积分信息");
        }
        return result;
    }

    /**
     * 查询订单积分信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<OrderCredit> queryOrderCreditList(OrderCredit orderCredit) throws PendingException {
        try {
            // 转换成Domain对象
            OrderCreditDomain cond = BeanMapping.map(orderCredit, OrderCreditDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(orderCredit.getOrderby())) {
                PageHelper.orderBy(orderCredit.getOrderby());
            }
            // 调数据库接口查询列表
            List<OrderCreditDomain> resultList = orderCreditDao.queryOrderCreditList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, OrderCredit.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCreditDBError, "订单积分信息列表查询失败");
        }
    }

    /**
     * 查询订单积分信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<OrderCredit> queryOrderCreditPage(OrderCredit orderCredit) throws PendingException {
        try {
            // 对请求参数进行校验
            if (orderCredit.getPageNo() <= 0 || orderCredit.getPageSize() <= 0) {
                ResCode.orderCreditDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(orderCredit.getPageNo(), orderCredit.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(orderCredit.getOrderby())) {
                PageHelper.orderBy(orderCredit.getOrderby());
            }
            // 转换成Domain对象
            OrderCreditDomain cond = BeanMapping.map(orderCredit, OrderCreditDomain.class);
            // 调数据库接口查询列表
            List<OrderCreditDomain> resultList = orderCreditDao.queryOrderCreditList(cond);
            // 生成分页对象
            PageInfo<OrderCreditDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, OrderCredit.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCreditDBError, "订单积分信息分页查询失败");
        }
    }

}
