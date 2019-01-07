package com.yc.trip.api.business.dao.sales;

import java.util.List;

import com.yc.trip.api.business.dto.sales.SalesCredit;
import com.yc.trip.api.business.query.sales.SalesCreditQuery;

/**
 * 销售人员积分信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:43
 *
 */
public interface SalesCreditDao {

    /**
     * 销售人员积分信息新增
     */
    void add(SalesCredit salesCredit);

    /**
     * 销售人员积分信息修改
     */
    void update(SalesCredit salesCredit);
    
    /**
     * 销售人员积分信息查询
     */
    SalesCredit get(SalesCreditQuery salesCreditQuery);

    /**
     * 销售人员积分信息列表查询
     */
    List<SalesCredit> queryList(SalesCreditQuery salesCreditQuery);

}