package com.yc.trip.api.business.facade.sales;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.sales.SalesCredit;
import com.yc.trip.api.business.query.sales.SalesCreditQuery;

/**
 * 销售人员积分信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:43
 */
public interface SalesCreditFacade {

    /**
     * 新增销售人员积分信息
     * @throws PendingException 
     */
    SalesCredit add(SalesCredit salesCredit) throws PendingException;

    /**
     * 修改销售人员积分信息
     * @throws PendingException 
     */
    void update(SalesCredit salesCredit) throws PendingException;
    
    /**
     * 查询销售人员积分信息
     * @throws PendingException 
     */
    SalesCredit get(SalesCreditQuery salesCreditQuery) throws PendingException;
    
    /**
     * 查询销售人员积分信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    SalesCredit mustGet(SalesCreditQuery salesCreditQuery) throws PendingException;

    /**
     * 查询销售人员积分信息列表
     * @throws PendingException 
     */
    List<SalesCredit> queryList(SalesCreditQuery salesCreditQuery) throws PendingException;

    /**
     * 查询销售人员积分信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<SalesCredit> queryPage(SalesCreditQuery salesCreditQuery) throws PendingException;

}