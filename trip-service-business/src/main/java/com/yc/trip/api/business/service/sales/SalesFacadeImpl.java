package com.yc.trip.api.business.service.sales;

import com.alibaba.dubbo.config.annotation.Service;
import com.yc.trip.api.business.bo.sales.SalesDomain;
import com.yc.trip.api.business.bo.store.CustomerDomain;
import com.yc.trip.api.business.bo.user.UserDomain;
import com.yc.trip.api.business.dao.sales.SaleDao;
import com.yc.trip.api.business.dao.user.UserDao;
import com.yc.trip.api.business.dto.sales.Sales;
import com.yc.trip.api.business.dto.store.Customer;
import com.yc.trip.api.business.facade.sales.SalesFacade;
import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.core.enums.YesNoStatus;
import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(version = "1.0.0")
public class SalesFacadeImpl extends AbstractDubboNativeService implements SalesFacade {

    @Autowired
    private SaleDao dao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Sales> querySales(Sales sales) throws PendingException {
        try {
            // 转换成domain对象
            SalesDomain cond = BeanMapping.map(sales, SalesDomain.class);

            List<SalesDomain> list=dao.querySale(cond);

            // 将新增后回返回（包含自增主键值）
            return BeanMapping.mapList(list, Sales.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "销售信息查询失败");
        }
    }

    @Override
    public Sales getSaleById(Long userId) throws PendingException {
        try {
            // 转换成domain对象
            SalesDomain cond = SalesDomain.builder().userId(userId).build();
            SalesDomain sales=dao.getSaleByUserId(userId);

            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(sales, Sales.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "销售信息查询失败");
        }
    }

    @Override
    public void updateSale(Sales sales) throws PendingException {
        try {
            // 转换成domain对象
            SalesDomain cond = BeanMapping.map(sales, SalesDomain.class);
            userDao.updateUser(UserDomain.builder().id(sales.getUserId()).address(sales.getAddress()).phone(sales.getPhone()).build());
            dao.updateSale(cond);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "销售信息更新失败");
        }
    }

    @Override
    public void deleteSales(Long userId) throws PendingException {
        try {
            // 转换成domain对象
            SalesDomain cond = SalesDomain.builder().userId(userId).build();
            userDao.updateUser(UserDomain.builder().id(cond.getUserId()).isDelete(YesNoStatus.YES).build());

        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeDBError, "销售信息删除失败");
        }
    }
}
