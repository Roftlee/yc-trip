package com.yc.trip.api.business.dao.store;

import com.yc.trip.api.business.bo.store.CustomerDomain;

import java.util.List;

public interface CustomerDao {

    public List<CustomerDomain> queryCustomer(CustomerDomain cust);


}
