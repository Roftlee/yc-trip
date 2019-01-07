package com.yc.trip.api.business.service.sales;

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
import com.yc.trip.api.business.dao.sales.SalesCreditDao;
import com.yc.trip.api.business.dto.sales.SalesCredit;
import com.yc.trip.api.business.query.sales.SalesCreditQuery;
import com.yc.trip.api.business.facade.sales.SalesCreditFacade;

/**
 * 销售人员积分信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:43
 */
@Service(version = "1.0.0")
public class SalesCreditFacadeImpl extends AbstractDubboNativeService implements SalesCreditFacade {

    @Autowired
    private SalesCreditDao salesCreditDao;

    /**
     * 新增销售人员积分信息
     * 
     * @throws PendingException
     */
    @Override
    public SalesCredit add(SalesCredit salesCredit) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            salesCredit.validateInsertFields();
            // 调数据库接口进行新增操作
            salesCreditDao.add(salesCredit);
            // 将新增后回返回（包含自增主键值）
            return salesCredit;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.salesCreditDBError, "销售人员积分信息新增失败");
        }
    }

    /**
     * 修改销售人员积分信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(SalesCredit salesCredit) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (salesCredit.isAllFiledsNull()) {
                ResCode.salesCreditDBParamInvalid.throwException("销售人员积分信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            salesCreditDao.update(salesCredit);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.salesCreditDBError, "销售人员积分信息更新失败");
        }
    }

    /**
     * 查询销售人员积分信息
     * 
     * @throws PendingException
     */
    @Override
    public SalesCredit get(SalesCreditQuery salesCreditQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return salesCreditDao.get(salesCreditQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.salesCreditDBError, "销售人员积分信息查询失败");
        }
    }
    
    /**
     * 查询销售人员积分信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public SalesCredit mustGet(SalesCreditQuery salesCreditQuery) throws PendingException {
        // 查询单位信息
        SalesCredit result = get(salesCreditQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.salesCreditDBGetNull.throwException("未查询到销售人员积分信息");
        }
        return result;
    }

    /**
     * 查询销售人员积分信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<SalesCredit> queryList(SalesCreditQuery salesCreditQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(salesCreditQuery.getOrderby())) {
                PageHelper.orderBy(salesCreditQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return salesCreditDao.queryList(salesCreditQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.salesCreditDBError, "销售人员积分信息列表查询失败");
        }
    }

    /**
     * 查询销售人员积分信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<SalesCredit> queryPage(SalesCreditQuery salesCreditQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (salesCreditQuery.getPageNo() <= 0 || salesCreditQuery.getPageSize() <= 0) {
                ResCode.salesCreditDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(salesCreditQuery.getPageNo(), salesCreditQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(salesCreditQuery.getOrderby())) {
                PageHelper.orderBy(salesCreditQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<SalesCredit> resultList = salesCreditDao.queryList(salesCreditQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.salesCreditDBError, "销售人员积分信息分页查询失败");
        }
    }

}
