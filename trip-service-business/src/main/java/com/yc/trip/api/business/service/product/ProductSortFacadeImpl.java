package com.yc.trip.api.business.service.product;

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
import com.yc.trip.api.business.dao.product.ProductSortDao;
import com.yc.trip.api.business.dto.product.ProductSort;
import com.yc.trip.api.business.query.product.ProductSortQuery;
import com.yc.trip.api.business.facade.product.ProductSortFacade;

/**
 * 产品分类信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:22
 */
@Service(version = "1.0.0")
public class ProductSortFacadeImpl extends AbstractDubboNativeService implements ProductSortFacade {

    @Autowired
    private ProductSortDao productSortDao;

    /**
     * 新增产品分类信息
     * 
     * @throws PendingException
     */
    @Override
    public ProductSort add(ProductSort productSort) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            productSort.validateInsertFields();
            // 调数据库接口进行新增操作
            productSortDao.add(productSort);
            // 将新增后回返回（包含自增主键值）
            return productSort;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productSortDBError, "产品分类信息新增失败");
        }
    }

    /**
     * 修改产品分类信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(ProductSort productSort) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (productSort.isAllFiledsNull()) {
                ResCode.productSortDBParamInvalid.throwException("产品分类信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            productSortDao.update(productSort);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productSortDBError, "产品分类信息更新失败");
        }
    }

    /**
     * 查询产品分类信息
     * 
     * @throws PendingException
     */
    @Override
    public ProductSort get(ProductSortQuery productSortQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return productSortDao.get(productSortQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productSortDBError, "产品分类信息查询失败");
        }
    }
    
    /**
     * 查询产品分类信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public ProductSort mustGet(ProductSortQuery productSortQuery) throws PendingException {
        // 查询单位信息
        ProductSort result = get(productSortQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.productSortDBGetNull.throwException("未查询到产品分类信息");
        }
        return result;
    }

    /**
     * 查询产品分类信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<ProductSort> queryList(ProductSortQuery productSortQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productSortQuery.getOrderby())) {
                PageHelper.orderBy(productSortQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return productSortDao.queryList(productSortQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productSortDBError, "产品分类信息列表查询失败");
        }
    }

    /**
     * 查询产品分类信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<ProductSort> queryPage(ProductSortQuery productSortQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (productSortQuery.getPageNo() <= 0 || productSortQuery.getPageSize() <= 0) {
                ResCode.productSortDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(productSortQuery.getPageNo(), productSortQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productSortQuery.getOrderby())) {
                PageHelper.orderBy(productSortQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<ProductSort> resultList = productSortDao.queryList(productSortQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productSortDBError, "产品分类信息分页查询失败");
        }
    }

}
