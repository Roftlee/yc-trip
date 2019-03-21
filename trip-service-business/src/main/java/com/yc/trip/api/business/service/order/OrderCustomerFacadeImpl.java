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
import com.yc.trip.api.business.bo.order.OrderCustomerDomain;
import com.yc.trip.api.business.dao.order.OrderCustomerDao;
import com.yc.trip.api.business.dto.order.OrderCustomer;
import com.yc.trip.api.business.facade.order.OrderCustomerFacade;

/**
 * 订单人员信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:47
 */
@Service(version = "1.0.0")
public class OrderCustomerFacadeImpl extends AbstractDubboNativeService implements OrderCustomerFacade {

    @Autowired
    private OrderCustomerDao orderCustomerDao;

    /**
     * 新增订单人员信息
     * 
     * @throws PendingException
     */
    @Override
    public OrderCustomer addOrderCustomer(OrderCustomer orderCustomer) throws PendingException {
        try {
            // 转换成domain对象
            OrderCustomerDomain cond = BeanMapping.map(orderCustomer, OrderCustomerDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.orderCustomerDBParamInvalid.throwException("订单人员信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            orderCustomerDao.addOrderCustomer(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, OrderCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCustomerDBError, "订单人员信息新增失败");
        }
    }

    /**
     * 修改订单人员信息
     * 
     * @throws PendingException
     */
    @Override
    public OrderCustomer updateOrderCustomer(OrderCustomer orderCustomer) throws PendingException {
        try {
            // 转换成domain对象
            OrderCustomerDomain cond = BeanMapping.map(orderCustomer, OrderCustomerDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.orderCustomerDBParamInvalid.throwException("订单人员信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            orderCustomerDao.updateOrderCustomer(cond);
            return BeanMapping.map(cond, OrderCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCustomerDBError, "订单人员信息更新失败");
        }
    }

    /**
     * 查询订单人员信息
     * 
     * @throws PendingException
     */
    @Override
    public OrderCustomer getOrderCustomer(OrderCustomer orderCustomer) throws PendingException {
        try {
            // 转换成Domain对象
            OrderCustomerDomain cond = BeanMapping.map(orderCustomer, OrderCustomerDomain.class);
            // 调数据库接口查询对象
            OrderCustomerDomain resultBean = orderCustomerDao.getOrderCustomer(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, OrderCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCustomerDBError, "订单人员信息查询失败");
        }
    }
    
    /**
     * 查询订单人员信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public OrderCustomer mustGet(OrderCustomer orderCustomer) throws PendingException {
        // 查询单位信息
        OrderCustomer result = getOrderCustomer(orderCustomer);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.orderCustomerDBGetNull.throwException("未查询到订单人员信息");
        }
        return result;
    }

    /**
     * 查询订单人员信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<OrderCustomer> queryOrderCustomerList(OrderCustomer orderCustomer) throws PendingException {
        try {
            // 转换成Domain对象
            OrderCustomerDomain cond = BeanMapping.map(orderCustomer, OrderCustomerDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(orderCustomer.getOrderby())) {
                PageHelper.orderBy(orderCustomer.getOrderby());
            }
            // 调数据库接口查询列表
            List<OrderCustomerDomain> resultList = orderCustomerDao.queryOrderCustomerList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, OrderCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCustomerDBError, "订单人员信息列表查询失败");
        }
    }

    /**
     * 查询订单人员信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<OrderCustomer> queryOrderCustomerPage(OrderCustomer orderCustomer) throws PendingException {
        try {
            // 对请求参数进行校验
            if (orderCustomer.getPageNo() <= 0 || orderCustomer.getPageSize() <= 0) {
                ResCode.orderCustomerDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(orderCustomer.getPageNo(), orderCustomer.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(orderCustomer.getOrderby())) {
                PageHelper.orderBy(orderCustomer.getOrderby());
            }
            // 转换成Domain对象
            OrderCustomerDomain cond = BeanMapping.map(orderCustomer, OrderCustomerDomain.class);
            // 调数据库接口查询列表
            List<OrderCustomerDomain> resultList = orderCustomerDao.queryOrderCustomerList(cond);
            // 生成分页对象
            PageInfo<OrderCustomerDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, OrderCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCustomerDBError, "订单人员信息分页查询失败");
        }
    }

}
