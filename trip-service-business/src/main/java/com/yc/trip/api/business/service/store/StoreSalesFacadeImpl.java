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
import com.yc.trip.api.business.dao.store.StoreSalesDao;
import com.yc.trip.api.business.dto.store.StoreSales;
import com.yc.trip.api.business.query.store.StoreSalesQuery;
import com.yc.trip.api.business.facade.store.StoreSalesFacade;

/**
 * 门店销售信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:54
 */
@Service(version = "1.0.0")
public class StoreSalesFacadeImpl extends AbstractDubboNativeService implements StoreSalesFacade {

    @Autowired
    private StoreSalesDao storeSalesDao;

    /**
     * 新增门店销售信息
     * 
     * @throws PendingException
     */
    @Override
    public StoreSales add(StoreSales storeSales) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            storeSales.validateInsertFields();
            // 调数据库接口进行新增操作
            storeSalesDao.add(storeSales);
            // 将新增后回返回（包含自增主键值）
            return storeSales;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息新增失败");
        }
    }

    /**
     * 修改门店销售信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(StoreSales storeSales) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (storeSales.isAllFiledsNull()) {
                ResCode.storeSalesDBParamInvalid.throwException("门店销售信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            storeSalesDao.update(storeSales);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息更新失败");
        }
    }

    /**
     * 查询门店销售信息
     * 
     * @throws PendingException
     */
    @Override
    public StoreSales get(StoreSalesQuery storeSalesQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return storeSalesDao.get(storeSalesQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息查询失败");
        }
    }
    
    /**
     * 查询门店销售信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public StoreSales mustGet(StoreSalesQuery storeSalesQuery) throws PendingException {
        // 查询单位信息
        StoreSales result = get(storeSalesQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.storeSalesDBGetNull.throwException("未查询到门店销售信息");
        }
        return result;
    }

    /**
     * 查询门店销售信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<StoreSales> queryList(StoreSalesQuery storeSalesQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeSalesQuery.getOrderby())) {
                PageHelper.orderBy(storeSalesQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return storeSalesDao.queryList(storeSalesQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息列表查询失败");
        }
    }

    /**
     * 查询门店销售信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<StoreSales> queryPage(StoreSalesQuery storeSalesQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (storeSalesQuery.getPageNo() <= 0 || storeSalesQuery.getPageSize() <= 0) {
                ResCode.storeSalesDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(storeSalesQuery.getPageNo(), storeSalesQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(storeSalesQuery.getOrderby())) {
                PageHelper.orderBy(storeSalesQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<StoreSales> resultList = storeSalesDao.queryList(storeSalesQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.storeSalesDBError, "门店销售信息分页查询失败");
        }
    }

}
