package com.yc.trip.api.business.facade.sales;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.sales.SalesCredit;

/**
 * 销售人员积分信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:18
 */
public interface SalesCreditFacade {

    /**
     * 新增销售人员积分信息
     * @throws PendingException 
     */
    SalesCredit addSalesCredit(SalesCredit salesCredit) throws PendingException;

    /**
     * 修改销售人员积分信息
     * @throws PendingException 
     */
    SalesCredit updateSalesCredit(SalesCredit salesCredit) throws PendingException;
    
    /**
     * 查询销售人员积分信息
     * @throws PendingException 
     */
    SalesCredit getSalesCredit(SalesCredit salesCredit) throws PendingException;
    
    /**
     * 查询销售人员积分信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    SalesCredit mustGet(SalesCredit salesCredit) throws PendingException;

    /**
     * 查询销售人员积分信息列表
     * @throws PendingException 
     */
    List<SalesCredit> querySalesCreditList(SalesCredit salesCredit) throws PendingException;

    /**
     * 查询销售人员积分信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<SalesCredit> querySalesCreditPage(SalesCredit salesCredit) throws PendingException;

}