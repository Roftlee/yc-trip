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
import com.yc.trip.api.business.bo.product.ProductSortDomain;
import com.yc.trip.api.business.dao.product.ProductSortDao;
import com.yc.trip.api.business.dto.product.ProductSort;
import com.yc.trip.api.business.facade.product.ProductSortFacade;

/**
 * 产品分类信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:07
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
    public ProductSort addProductSort(ProductSort productSort) throws PendingException {
        try {
            // 转换成domain对象
            ProductSortDomain cond = BeanMapping.map(productSort, ProductSortDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.productSortDBParamInvalid.throwException("产品分类信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            productSortDao.addProductSort(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, ProductSort.class);
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
    public ProductSort updateProductSort(ProductSort productSort) throws PendingException {
        try {
            // 转换成domain对象
            ProductSortDomain cond = BeanMapping.map(productSort, ProductSortDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.productSortDBParamInvalid.throwException("产品分类信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            productSortDao.updateProductSort(cond);
            return BeanMapping.map(cond, ProductSort.class);
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
    public ProductSort getProductSort(ProductSort productSort) throws PendingException {
        try {
            // 转换成Domain对象
            ProductSortDomain cond = BeanMapping.map(productSort, ProductSortDomain.class);
            // 调数据库接口查询对象
            ProductSortDomain resultBean = productSortDao.getProductSort(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, ProductSort.class);
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
    public ProductSort mustGet(ProductSort productSort) throws PendingException {
        // 查询单位信息
        ProductSort result = getProductSort(productSort);
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
    public List<ProductSort> queryProductSortList(ProductSort productSort) throws PendingException {
        try {
            // 转换成Domain对象
            ProductSortDomain cond = BeanMapping.map(productSort, ProductSortDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productSort.getOrderby())) {
                PageHelper.orderBy(productSort.getOrderby());
            }
            // 调数据库接口查询列表
            List<ProductSortDomain> resultList = productSortDao.queryProductSortList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, ProductSort.class);
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
    public PageInfo<ProductSort> queryProductSortPage(ProductSort productSort) throws PendingException {
        try {
            // 对请求参数进行校验
            if (productSort.getPageNo() <= 0 || productSort.getPageSize() <= 0) {
                ResCode.productSortDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(productSort.getPageNo(), productSort.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productSort.getOrderby())) {
                PageHelper.orderBy(productSort.getOrderby());
            }
            // 转换成Domain对象
            ProductSortDomain cond = BeanMapping.map(productSort, ProductSortDomain.class);
            // 调数据库接口查询列表
            List<ProductSortDomain> resultList = productSortDao.queryProductSortList(cond);
            // 生成分页对象
            PageInfo<ProductSortDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, ProductSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productSortDBError, "产品分类信息分页查询失败");
        }
    }

}
