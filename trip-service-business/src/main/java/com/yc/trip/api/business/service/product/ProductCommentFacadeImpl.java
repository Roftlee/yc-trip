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
import com.yc.trip.api.business.dao.product.ProductCommentDao;
import com.yc.trip.api.business.dto.product.ProductComment;
import com.yc.trip.api.business.query.product.ProductCommentQuery;
import com.yc.trip.api.business.facade.product.ProductCommentFacade;

/**
 * 产品评论信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:17
 */
@Service(version = "1.0.0")
public class ProductCommentFacadeImpl extends AbstractDubboNativeService implements ProductCommentFacade {

    @Autowired
    private ProductCommentDao productCommentDao;

    /**
     * 新增产品评论信息
     * 
     * @throws PendingException
     */
    @Override
    public ProductComment add(ProductComment productComment) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            productComment.validateInsertFields();
            // 调数据库接口进行新增操作
            productCommentDao.add(productComment);
            // 将新增后回返回（包含自增主键值）
            return productComment;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentDBError, "产品评论信息新增失败");
        }
    }

    /**
     * 修改产品评论信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(ProductComment productComment) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (productComment.isAllFiledsNull()) {
                ResCode.productCommentDBParamInvalid.throwException("产品评论信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            productCommentDao.update(productComment);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentDBError, "产品评论信息更新失败");
        }
    }

    /**
     * 查询产品评论信息
     * 
     * @throws PendingException
     */
    @Override
    public ProductComment get(ProductCommentQuery productCommentQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return productCommentDao.get(productCommentQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentDBError, "产品评论信息查询失败");
        }
    }
    
    /**
     * 查询产品评论信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public ProductComment mustGet(ProductCommentQuery productCommentQuery) throws PendingException {
        // 查询单位信息
        ProductComment result = get(productCommentQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.productCommentDBGetNull.throwException("未查询到产品评论信息");
        }
        return result;
    }

    /**
     * 查询产品评论信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<ProductComment> queryList(ProductCommentQuery productCommentQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productCommentQuery.getOrderby())) {
                PageHelper.orderBy(productCommentQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return productCommentDao.queryList(productCommentQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentDBError, "产品评论信息列表查询失败");
        }
    }

    /**
     * 查询产品评论信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<ProductComment> queryPage(ProductCommentQuery productCommentQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (productCommentQuery.getPageNo() <= 0 || productCommentQuery.getPageSize() <= 0) {
                ResCode.productCommentDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(productCommentQuery.getPageNo(), productCommentQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productCommentQuery.getOrderby())) {
                PageHelper.orderBy(productCommentQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<ProductComment> resultList = productCommentDao.queryList(productCommentQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentDBError, "产品评论信息分页查询失败");
        }
    }

}
