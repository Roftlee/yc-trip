package com.yc.trip.web.controller.customer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.oss.dto.OssUploadDto;
import com.yc.oss.facade.OssUploadFacade;
import com.yc.trip.api.business.dto.comment.Comment;
import com.yc.trip.api.business.dto.order.OrderManagerInfo;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.dto.store.Customer;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.dto.store.StoreSales;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.order.OrderManageFacade;
import com.yc.trip.api.business.facade.store.CustomerFacade;
import com.yc.trip.api.business.facade.store.StoreSalesFacade;
import com.yc.trip.api.business.request.customer.CustomerRequest;
import com.yc.trip.api.business.request.order.OrderQueryRequest;
import com.yc.trip.api.business.request.product.ProductAddRequest;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.apache.commons.io.IOUtils;
import org.go.api.core.dto.ResDto;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/customer")
public class CustomerController extends AbstractBaseController {


    @Reference(version = "1.0.0")
    private CustomerFacade customerFacade;

    @Reference(version = "1.0.0")
    private OrderManageFacade orderFacade;



    @RequestMapping(value = "/getStore.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<Store>>  getAllStore()  throws PendingException {
        return new ResDto<>(orderFacade.storeList());
    }


    @RequestMapping(value = "/queryCustomer.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<Customer>>  query(@RequestBody CustomerRequest request)  throws PendingException {
        UserType userTyp=getSessionUser().getUserType();
        if(userTyp.getName().equals("门店老板")){//如果登录用户为供应商，查询供应商对应的所有订单
            StoreSales  ss=StoreSales.builder().userId(getSessionUser().getUserId()).build();
//            request.setStoreId(storeSalesFacade.mustGet(ss).getStoreId()+"");
        }
        return new ResDto<>(customerFacade.queryCustomer(Customer.builder().customerName(request.getCustomer()).store(request.getStore()).build()));
    }

    @RequestMapping(value = "/updateCustomer.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> updateCustomer(@RequestBody CustomerRequest request) throws PendingException {

        customerFacade.updateCustomer(Customer.builder().customerName(request.getCustomer()).phone(request.getPhone()).address(request.getAddress()).build());
        return new ResDto<>();
    }

    @RequestMapping(value = "/deleteCustomer.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> deleteCustomer(String userId) throws PendingException {
        customerFacade.deleteCustomer(userId);
        return new ResDto<>();
    }


    @RequestMapping(value = "/getCustomer.do", method = RequestMethod.GET)
    @MvcValidate
    public ResDto<Customer>  getCommentByOrderId(String userId)  throws PendingException {
        return new ResDto<>(customerFacade.getCustomerByUserId(userId));
    }



}
