package com.yc.trip.api.business.facade.sales;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.sales.Sales;
import com.yc.trip.api.business.dto.sales.SalesCredit;
import org.go.framework.core.exception.PendingException;

import java.util.List;

/**
 *
 * @author My-Toolkits
 * @since 2019-03-21 22:18
 */
public interface SalesFacade {

    /**
     * 新增销售人员积分信息
     * @throws PendingException 
     */
    List<Sales> querySales(Sales salesCredit) throws PendingException;

    Sales getSaleById(Long userId) throws PendingException;

    void updateSale(Sales sales) throws  PendingException;

    void deleteSales(Long userId) throws  PendingException;



}