package com.yc.trip.api.business.service.store;

import com.alibaba.dubbo.config.annotation.Service;
import com.yc.trip.api.business.bo.store.CustomerDomain;
import com.yc.trip.api.business.bo.store.StoreDomain;
import com.yc.trip.api.business.bo.user.UserDomain;
import com.yc.trip.api.business.dao.store.CustomerDao;
import com.yc.trip.api.business.dao.user.UserDao;
import com.yc.trip.api.business.dto.store.Customer;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.facade.store.CustomerFacade;
import com.yc.trip.api.business.facade.store.StoreCustomerFacade;
import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.core.enums.YesNoStatus;
import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(version = "1.0.0")
public class CustomerFacadeImpl  extends AbstractDubboNativeService implements CustomerFacade {
    @Autowired
    private CustomerDao dao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Customer> queryCustomer(Customer cust)  throws PendingException {
        try {
            // 转换成domain对象
            CustomerDomain cond = BeanMapping.map(cust, CustomerDomain.class);

           List<CustomerDomain> list=dao.queryCustomer(cond);

            // 将新增后回返回（包含自增主键值）
            return BeanMapping.mapList(list, Customer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "客户信息查询失败");
        }

    }

    @Override
    public Customer getCustomerByUserId(String userId)  throws PendingException {
        try {
            // 转换成domain对象
            UserDomain.builder().id(Long.parseLong(userId)).build();
            UserDomain user=userDao.getUser(UserDomain.builder().id(Long.parseLong(userId)).build());

            // 将新增后回返回（包含自增主键值）
            Customer cust= BeanMapping.map(user, Customer.class);
            cust.setCustomerName(user.getName());
            cust.setUserId(user.getId()+"");
            return cust;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "客户信息查询失败");
        }
    }

    @Override
    public void updateCustomer(Customer customer)  throws PendingException{
        try {
            // 转换成domain对象
            UserDomain cond = BeanMapping.map(customer, UserDomain.class);
            cond.setId(Long.parseLong(customer.getUserId()));
            cond.setName(customer.getCustomerName());
            userDao.updateUser(cond);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "客户信息更新失败");
        }

    }

    @Override
    public void deleteCustomer(String userId)  throws PendingException{
        try {
            // 转换成domain对象
            UserDomain cond = UserDomain.builder().id(Long.parseLong(userId)).isDelete(YesNoStatus.YES).build();
            userDao.updateUser(cond);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "客户信息更新失败");
        }
    }
}
