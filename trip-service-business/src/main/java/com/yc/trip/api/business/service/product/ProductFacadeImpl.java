package com.yc.trip.api.business.service.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.bo.product.ProductDomain;
import com.yc.trip.api.business.dao.product.ProductDao;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.facade.product.ProductFacade;
import com.yc.trip.api.business.request.common.PageRequest;
import com.yc.trip.api.core.constants.ResCode;
import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 产品信息相关接口实现
 *
 * @author My-Toolkits
 * @since 2019-03-21 21:50
 */
@Service(version = "1.0.0")
public class ProductFacadeImpl extends AbstractDubboNativeService implements ProductFacade {

    @Autowired
    private ProductDao productDao;

    /**
     * 新增产品信息
     *
     * @throws PendingException
     */
    @Override
    public Product addProduct(Product product) throws PendingException {
        try {
            // 转换成domain对象
            ProductDomain cond = BeanMapping.map(product, ProductDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.productDBParamInvalid.throwException("产品信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            productDao.addProduct(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Product.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productDBError, "产品信息新增失败");
        }
    }

    /**
     * 修改产品信息
     *
     * @throws PendingException
     */
    @Override
    public Product updateProduct(Product product) throws PendingException {
        try {
            // 转换成domain对象
            ProductDomain cond = BeanMapping.map(product, ProductDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.productDBParamInvalid.throwException("产品信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            productDao.updateProduct(cond);
            return BeanMapping.map(cond, Product.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productDBError, "产品信息更新失败");
        }
    }

    /**
     * 查询产品信息
     *
     * @throws PendingException
     */
    @Override
    public Product getProduct(Product product) throws PendingException {
        try {
            // 转换成Domain对象
            ProductDomain cond = BeanMapping.map(product, ProductDomain.class);
            // 调数据库接口查询对象
            ProductDomain resultBean = productDao.getProduct(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Product.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productDBError, "产品信息查询失败");
        }
    }

    /**
     * 查询产品信息信息（若不存在，则抛出异常）
     *
     * @throws PendingException
     */
    @Override
    public Product mustGet(Product product) throws PendingException {
        // 查询单位信息
        Product result = getProduct(product);
        // 若不存在，则抛出异常
        if (result == null) {
            ResCode.productDBGetNull.throwException("未查询到产品信息");
        }
        return result;
    }

    /**
     * 查询产品信息列表
     *
     * @throws PendingException
     */
    @Override
    public List<Product> queryProductList(Product product) throws PendingException {
        try {
            // 转换成Domain对象
            ProductDomain cond = BeanMapping.map(product, ProductDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(product.getOrderby())) {
                PageHelper.orderBy(product.getOrderby());
            }
            // 调数据库接口查询列表
            List<ProductDomain> resultList = productDao.queryProductList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Product.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productDBError, "产品信息列表查询失败");
        }
    }

    /**
     * 查询产品信息列表 ,分页查询
     *
     * @throws PendingException
     */
    @Override
    public PageInfo<Product> queryProductPage(Product product) throws PendingException {
        try {
            // 对请求参数进行校验
            if (product.getPageNo() <= 0 || product.getPageSize() <= 0) {
                ResCode.productDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(product.getPageNo(), product.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(product.getOrderby())) {
                PageHelper.orderBy(product.getOrderby());
            }
            // 转换成Domain对象
            ProductDomain cond = BeanMapping.map(product, ProductDomain.class);
            // 调数据库接口查询列表
            List<ProductDomain> resultList = productDao.queryProductList(cond);
            // 返回分页结果
            return new PageInfo<>(BeanMapping.mapList(resultList, Product.class));
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productDBError, "产品信息分页查询失败");
        }
    }

    /**
     * 随机查询产品列表
     *
     * @throws PendingException
     */
    @Override
    public List<Product> queryProductListRandom(PageRequest pageRequest) throws PendingException {
        try {
            // 调数据库接口查询列表
            return productDao.queryProductListRandom(pageRequest);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productDBError, "随机查询产品列表失败");
        }
    }

    /**
     * 随机查询产品列表(分页查询)
     *
     * @throws PendingException
     */
    @Override
    public PageInfo<Product> queryProductListRandomPage(PageRequest pageRequest) throws PendingException {
        try {
            // 对请求参数进行校验
            if (pageRequest.getPageNo() <= 0 || pageRequest.getPageSize() <= 0) {
                ResCode.productDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(pageRequest.getOrderby())) {
                PageHelper.orderBy(pageRequest.getOrderby());
            }
            // 调数据库接口查询列表
            List<Product> resultList = productDao.queryProductListRandom(pageRequest);
            // 生成分页对象
            return new PageInfo<Product>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productDBError, "随机查询产品列表)失败(分页查询)");
        }
    }

}
