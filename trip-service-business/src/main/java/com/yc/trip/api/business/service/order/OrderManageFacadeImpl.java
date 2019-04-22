package com.yc.trip.api.business.service.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.yc.trip.api.business.bo.order.OrderConstractDomain;
import com.yc.trip.api.business.bo.order.OrderInfoDomain;
import com.yc.trip.api.business.bo.order.OrderNoticeDomain;
import com.yc.trip.api.business.bo.product.ProductSortDomain;
import com.yc.trip.api.business.bo.provider.ProviderDomain;
import com.yc.trip.api.business.bo.store.StoreDomain;
import com.yc.trip.api.business.dao.order.OrderConstractDao;
import com.yc.trip.api.business.dao.order.OrderInfoDao;
import com.yc.trip.api.business.dao.order.OrderNoticeDao;
import com.yc.trip.api.business.dao.provider.ProviderDao;
import com.yc.trip.api.business.dao.store.StoreDao;
import com.yc.trip.api.business.dto.order.OrderInfo;
import com.yc.trip.api.business.dto.order.OrderManagerInfo;
import com.yc.trip.api.business.dto.product.ProductSort;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.facade.order.OrderManageFacade;
import com.yc.trip.api.core.constants.ResCode;
import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service(version = "1.0.0")
public class OrderManageFacadeImpl extends AbstractDubboNativeService implements OrderManageFacade {

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private ProviderDao providerDao;
    @Autowired
    private OrderConstractDao constractDao;
    @Autowired
    private OrderNoticeDao noticeDao;
    @Autowired
    private OrderInfoDao orderDao;


    @Override
    public List<OrderManagerInfo> queryOrderyList(OrderManagerInfo order)  throws PendingException {
        try {
            // 转换成Domain对象
            OrderInfoDomain cond = BeanMapping.map(order, OrderInfoDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(order.getOrderby())) {
                PageHelper.orderBy(order.getOrderby());
            }
            // 调数据库接口查询列表
            List<OrderInfoDomain> resultList = orderDao.queryOrderInfoList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, OrderManagerInfo.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.orderInfoDBError, "订单信息列表查询失败");
        }
    }

    @Override
    public void addConstract(OrderManagerInfo order)  throws PendingException  {
        for(String pic:order.getPicUrl()){
            OrderConstractDomain ocd=new OrderConstractDomain();
            ocd.setConstrctPic(pic);
            ocd.setOrderid(order.getId());
            ocd.setUserId(order.getUserId());
            constractDao.addOrderConstrct(ocd);
        }
    }

    @Override
    public void addNotice(OrderManagerInfo order)  throws PendingException {
        List<String> fileList= order.getFileName();
        List<String> realFileList=order.getRealFileName();
        for(int i=0;i<fileList.size();i++){
            OrderNoticeDomain ond=new OrderNoticeDomain();
            ond.setOrderid(order.getId());
            ond.setNoticeFileName(fileList.get(i));
            ond.setRealFileName(realFileList.get(i));
            ond.setUserId(order.getUserId());
            noticeDao.addOrderNotice(ond);
        }
    }

    @Override
    public List<Provider> provideList()  throws PendingException {
        List<ProviderDomain> list=providerDao.getAllProvider();
        return BeanMapping.mapList(list, Provider.class);
    }

    @Override
    public List<Store> storeList()  throws PendingException {
        List<StoreDomain> list=storeDao.getAllStore();
         return BeanMapping.mapList(list, Store.class);
    }




}
