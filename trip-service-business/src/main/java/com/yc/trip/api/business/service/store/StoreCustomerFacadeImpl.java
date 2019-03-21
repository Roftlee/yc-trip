package com.yc.trip.api.business.service.store;

import java.util.List;


import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.business.bo.store.StoreCustomerDomain;
import com.yc.trip.api.business.dao.store.StoreCustomerDao;
import com.yc.trip.api.business.dto.store.StoreCustomer;
import com.yc.trip.api.business.facade.store.StoreCustomerFacade;

/**
 * 门店客户信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:24
 */
@Service(version = "1.0.0")
public class StoreCustomerFacadeImpl extends AbstractDubboNativeService implements StoreCustomerFacade {

    @Autowired
    private StoreCustomerDao storeCustomerDao;

    /**
     * 新增门店客户信息
     * 
     * @throws PendingException
     */
    @Override
    public StoreCustomer addStoreCustomer(StoreCustomer storeCustomer) throws PendingException {
        try {
            // 转换成domain对象
            StoreCustomerDomain cond = BeanMapping.map(storeCustomer, StoreCustomerDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.storeCustomerDBParamInvalid.throwException("门店客户信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            storeCustomerDao.addStoreCustomer(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, StoreCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeCustomerDBError, "门店客户信息新增失败");
        }
    }

    /**
     * 修改门店客户信息
     * 
     * @throws PendingException
     */
    @Override
    public StoreCustomer updateStoreCustomer(StoreCustomer storeCustomer) throws PendingException {
        try {
            // 转换成domain对象
            StoreCustomerDomain cond = BeanMapping.map(storeCustomer, StoreCustomerDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.storeCustomerDBParamInvalid.throwException("门店客户信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            storeCustomerDao.updateStoreCustomer(cond);
            return BeanMapping.map(cond, StoreCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeCustomerDBError, "门店客户信息更新失败");
        }
    }

    /**
     * 查询门店客户信息
     * 
     * @throws PendingException
     */
    @Override
    public StoreCustomer getStoreCustomer(StoreCustomer storeCustomer) throws PendingException {
        try {
            // 转换成Domain对象
            StoreCustomerDomain cond = BeanMapping.map(storeCustomer, StoreCustomerDomain.class);
            // 调数据库接口查询对象
            StoreCustomerDomain resultBean = storeCustomerDao.getStoreCustomer(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, StoreCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeCustomerDBError, "门店客户信息查询失败");
        }
    }
    
    /**
     * 查询门店客户信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public StoreCustomer mustGet(StoreCustomer storeCustomer) throws PendingException {
        // 查询单位信息
        StoreCustomer result = getStoreCustomer(storeCustomer);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.storeCustomerDBGetNull.throwException("未查询到门店客户信息");
        }
        return result;
    }

    /**
     * 查询门店客户信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<StoreCustomer> queryStoreCustomerList(StoreCustomer storeCustomer) throws PendingException {
        try {
            // 转换成Domain对象
            StoreCustomerDomain cond = BeanMapping.map(storeCustomer, StoreCustomerDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeCustomer.getOrderby())) {
                PageHelper.orderBy(storeCustomer.getOrderby());
            }
            // 调数据库接口查询列表
            List<StoreCustomerDomain> resultList = storeCustomerDao.queryStoreCustomerList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, StoreCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeCustomerDBError, "门店客户信息列表查询失败");
        }
    }

    /**
     * 查询门店客户信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<StoreCustomer> queryStoreCustomerPage(StoreCustomer storeCustomer) throws PendingException {
        try {
            // 对请求参数进行校验
            if (storeCustomer.getPageNo() <= 0 || storeCustomer.getPageSize() <= 0) {
                ResCode.storeCustomerDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(storeCustomer.getPageNo(), storeCustomer.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeCustomer.getOrderby())) {
                PageHelper.orderBy(storeCustomer.getOrderby());
            }
            // 转换成Domain对象
            StoreCustomerDomain cond = BeanMapping.map(storeCustomer, StoreCustomerDomain.class);
            // 调数据库接口查询列表
            List<StoreCustomerDomain> resultList = storeCustomerDao.queryStoreCustomerList(cond);
            // 生成分页对象
            PageInfo<StoreCustomerDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, StoreCustomer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeCustomerDBError, "门店客户信息分页查询失败");
        }
    }

}
