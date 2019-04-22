package com.yc.trip.api.business.facade.order;


import com.yc.trip.api.business.dto.order.OrderManagerInfo;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.dto.store.Store;
import org.go.framework.core.exception.PendingException;

import java.util.List;

public interface OrderManageFacade {

    public List<OrderManagerInfo> queryOrderyList(OrderManagerInfo  order) throws PendingException;;


    public void addConstract(OrderManagerInfo  order) throws PendingException;;


    public void addNotice(OrderManagerInfo order) throws PendingException;;

    public List<Provider> provideList() throws PendingException;;

    public List<Store> storeList() throws PendingException;;




}
