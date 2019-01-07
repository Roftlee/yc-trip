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
import com.yc.trip.api.business.dao.store.StoreCustomerDao;
import com.yc.trip.api.business.dto.store.StoreCustomer;
import com.yc.trip.api.business.query.store.StoreCustomerQuery;
import com.yc.trip.api.business.facade.store.StoreCustomerFacade;

/**
 * 门店客户信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:53
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
    public StoreCustomer add(StoreCustomer storeCustomer) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            storeCustomer.validateInsertFields();
            // 调数据库接口进行新增操作
            storeCustomerDao.add(storeCustomer);
            // 将新增后回返回（包含自增主键值）
            return storeCustomer;
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
    public void update(StoreCustomer storeCustomer) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (storeCustomer.isAllFiledsNull()) {
                ResCode.storeCustomerDBParamInvalid.throwException("门店客户信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            storeCustomerDao.update(storeCustomer);
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
    public StoreCustomer get(StoreCustomerQuery storeCustomerQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return storeCustomerDao.get(storeCustomerQuery);
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
    public StoreCustomer mustGet(StoreCustomerQuery storeCustomerQuery) throws PendingException {
        // 查询单位信息
        StoreCustomer result = get(storeCustomerQuery);
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
    public List<StoreCustomer> queryList(StoreCustomerQuery storeCustomerQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeCustomerQuery.getOrderby())) {
                PageHelper.orderBy(storeCustomerQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return storeCustomerDao.queryList(storeCustomerQuery);
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
    public PageInfo<StoreCustomer> queryPage(StoreCustomerQuery storeCustomerQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (storeCustomerQuery.getPageNo() <= 0 || storeCustomerQuery.getPageSize() <= 0) {
                ResCode.storeCustomerDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(storeCustomerQuery.getPageNo(), storeCustomerQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeCustomerQuery.getOrderby())) {
                PageHelper.orderBy(storeCustomerQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<StoreCustomer> resultList = storeCustomerDao.queryList(storeCustomerQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeCustomerDBError, "门店客户信息分页查询失败");
        }
    }

}
