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
import com.yc.trip.api.business.bo.order.OrderInfoDomain;
import com.yc.trip.api.business.dao.order.OrderInfoDao;
import com.yc.trip.api.business.dto.order.OrderInfo;
import com.yc.trip.api.business.facade.order.OrderInfoFacade;

/**
 * 订单信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:48
 */
@Service(version = "1.0.0")
public class OrderInfoFacadeImpl extends AbstractDubboNativeService implements OrderInfoFacade {

    @Autowired
    private OrderInfoDao orderInfoDao;

    /**
     * 新增订单信息
     * 
     * @throws PendingException
     */
    @Override
    public OrderInfo addOrderInfo(OrderInfo orderInfo) throws PendingException {
        try {
            // 转换成domain对象
            OrderInfoDomain cond = BeanMapping.map(orderInfo, OrderInfoDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.orderInfoDBParamInvalid.throwException("订单信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            orderInfoDao.addOrderInfo(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, OrderInfo.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderInfoDBError, "订单信息新增失败");
        }
    }

    /**
     * 修改订单信息
     * 
     * @throws PendingException
     */
    @Override
    public OrderInfo updateOrderInfo(OrderInfo orderInfo) throws PendingException {
        try {
            // 转换成domain对象
            OrderInfoDomain cond = BeanMapping.map(orderInfo, OrderInfoDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.orderInfoDBParamInvalid.throwException("订单信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            orderInfoDao.updateOrderInfo(cond);
            return BeanMapping.map(cond, OrderInfo.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderInfoDBError, "订单信息更新失败");
        }
    }

    /**
     * 查询订单信息
     * 
     * @throws PendingException
     */
    @Override
    public OrderInfo getOrderInfo(OrderInfo orderInfo) throws PendingException {
        try {
            // 转换成Domain对象
            OrderInfoDomain cond = BeanMapping.map(orderInfo, OrderInfoDomain.class);
            // 调数据库接口查询对象
            OrderInfoDomain resultBean = orderInfoDao.getOrderInfo(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, OrderInfo.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderInfoDBError, "订单信息查询失败");
        }
    }
    
    /**
     * 查询订单信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public OrderInfo mustGet(OrderInfo orderInfo) throws PendingException {
        // 查询单位信息
        OrderInfo result = getOrderInfo(orderInfo);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.orderInfoDBGetNull.throwException("未查询到订单信息");
        }
        return result;
    }

    /**
     * 查询订单信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<OrderInfo> queryOrderInfoList(OrderInfo orderInfo) throws PendingException {
        try {
            // 转换成Domain对象
            OrderInfoDomain cond = BeanMapping.map(orderInfo, OrderInfoDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(orderInfo.getOrderby())) {
                PageHelper.orderBy(orderInfo.getOrderby());
            }
            // 调数据库接口查询列表
            List<OrderInfoDomain> resultList = orderInfoDao.queryOrderInfoList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, OrderInfo.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderInfoDBError, "订单信息列表查询失败");
        }
    }

    /**
     * 查询订单信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<OrderInfo> queryOrderInfoPage(OrderInfo orderInfo) throws PendingException {
        try {
            // 对请求参数进行校验
            if (orderInfo.getPageNo() <= 0 || orderInfo.getPageSize() <= 0) {
                ResCode.orderInfoDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(orderInfo.getPageNo(), orderInfo.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(orderInfo.getOrderby())) {
                PageHelper.orderBy(orderInfo.getOrderby());
            }
            // 转换成Domain对象
            OrderInfoDomain cond = BeanMapping.map(orderInfo, OrderInfoDomain.class);
            // 调数据库接口查询列表
            List<OrderInfoDomain> resultList = orderInfoDao.queryOrderInfoList(cond);
            // 生成分页对象
            PageInfo<OrderInfoDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, OrderInfo.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderInfoDBError, "订单信息分页查询失败");
        }
    }

}
