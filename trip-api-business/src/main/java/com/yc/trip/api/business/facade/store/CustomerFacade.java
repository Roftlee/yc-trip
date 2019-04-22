package com.yc.trip.api.business.facade.store;

import com.yc.trip.api.business.dto.store.Customer;
import org.go.framework.core.exception.PendingException;

import java.util.List;

public interface CustomerFacade {

    public List<Customer> queryCustomer(Customer cust)  throws PendingException;

    public Customer getCustomerByUserId(String userId)  throws PendingException;

    public void updateCustomer(Customer customer)  throws PendingException;

    public void deleteCustomer(String userId) throws PendingException;

}
