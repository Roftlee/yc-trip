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
import com.yc.trip.api.business.dao.order.OrderCustomerDao;
import com.yc.trip.api.business.dto.order.OrderCustomer;
import com.yc.trip.api.business.query.order.OrderCustomerQuery;
import com.yc.trip.api.business.facade.order.OrderCustomerFacade;

/**
 * 订单人员信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:14
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
    public OrderCustomer add(OrderCustomer orderCustomer) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            orderCustomer.validateInsertFields();
            // 调数据库接口进行新增操作
            orderCustomerDao.add(orderCustomer);
            // 将新增后回返回（包含自增主键值）
            return orderCustomer;
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
    public void update(OrderCustomer orderCustomer) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (orderCustomer.isAllFiledsNull()) {
                ResCode.orderCustomerDBParamInvalid.throwException("订单人员信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            orderCustomerDao.update(orderCustomer);
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
    public OrderCustomer get(OrderCustomerQuery orderCustomerQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return orderCustomerDao.get(orderCustomerQuery);
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
    public OrderCustomer mustGet(OrderCustomerQuery orderCustomerQuery) throws PendingException {
        // 查询单位信息
        OrderCustomer result = get(orderCustomerQuery);
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
    public List<OrderCustomer> queryList(OrderCustomerQuery orderCustomerQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(orderCustomerQuery.getOrderby())) {
                PageHelper.orderBy(orderCustomerQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return orderCustomerDao.queryList(orderCustomerQuery);
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
    public PageInfo<OrderCustomer> queryPage(OrderCustomerQuery orderCustomerQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (orderCustomerQuery.getPageNo() <= 0 || orderCustomerQuery.getPageSize() <= 0) {
                ResCode.orderCustomerDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(orderCustomerQuery.getPageNo(), orderCustomerQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(orderCustomerQuery.getOrderby())) {
                PageHelper.orderBy(orderCustomerQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<OrderCustomer> resultList = orderCustomerDao.queryList(orderCustomerQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderCustomerDBError, "订单人员信息分页查询失败");
        }
    }

}
