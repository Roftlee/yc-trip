package com.yc.trip.api.business.dao.sales;

import java.util.List;

import com.yc.trip.api.business.bo.sales.SalesCreditDomain;

/**
 * 销售人员积分信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:18
 *
 */
public interface SalesCreditDao {

    /**
     * 新增销售人员积分信息
     */
    void addSalesCredit(SalesCreditDomain salesCreditDomain);

    /**
     * 修改销售人员积分信息
     */
    void updateSalesCredit(SalesCreditDomain salesCreditDomain);
    
    /**
     * 查询销售人员积分信息
     */
    SalesCreditDomain getSalesCredit(SalesCreditDomain salesCreditDomain);

    /**
     * 查询销售人员积分信息列表
     */
    List<SalesCreditDomain> querySalesCreditList(SalesCreditDomain salesCreditDomain);

}