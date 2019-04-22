package com.yc.trip.api.business.dao.sales;

import com.yc.trip.api.business.bo.sales.SalesDomain;

import java.util.List;

public interface SaleDao {


    public List<SalesDomain> querySale(SalesDomain s);


    public void updateSale(SalesDomain s);

    public SalesDomain getSaleByUserId(Long userId);

}
