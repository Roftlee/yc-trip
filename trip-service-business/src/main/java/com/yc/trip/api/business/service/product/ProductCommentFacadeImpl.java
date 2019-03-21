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
import com.yc.trip.api.business.bo.product.ProductCommentDomain;
import com.yc.trip.api.business.dao.product.ProductCommentDao;
import com.yc.trip.api.business.dto.product.ProductComment;
import com.yc.trip.api.business.facade.product.ProductCommentFacade;

/**
 * 产品评论信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:00
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
    public ProductComment addProductComment(ProductComment productComment) throws PendingException {
        try {
            // 转换成domain对象
            ProductCommentDomain cond = BeanMapping.map(productComment, ProductCommentDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.productCommentDBParamInvalid.throwException("产品评论信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            productCommentDao.addProductComment(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, ProductComment.class);
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
    public ProductComment updateProductComment(ProductComment productComment) throws PendingException {
        try {
            // 转换成domain对象
            ProductCommentDomain cond = BeanMapping.map(productComment, ProductCommentDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.productCommentDBParamInvalid.throwException("产品评论信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            productCommentDao.updateProductComment(cond);
            return BeanMapping.map(cond, ProductComment.class);
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
    public ProductComment getProductComment(ProductComment productComment) throws PendingException {
        try {
            // 转换成Domain对象
            ProductCommentDomain cond = BeanMapping.map(productComment, ProductCommentDomain.class);
            // 调数据库接口查询对象
            ProductCommentDomain resultBean = productCommentDao.getProductComment(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, ProductComment.class);
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
    public ProductComment mustGet(ProductComment productComment) throws PendingException {
        // 查询单位信息
        ProductComment result = getProductComment(productComment);
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
    public List<ProductComment> queryProductCommentList(ProductComment productComment) throws PendingException {
        try {
            // 转换成Domain对象
            ProductCommentDomain cond = BeanMapping.map(productComment, ProductCommentDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productComment.getOrderby())) {
                PageHelper.orderBy(productComment.getOrderby());
            }
            // 调数据库接口查询列表
            List<ProductCommentDomain> resultList = productCommentDao.queryProductCommentList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, ProductComment.class);
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
    public PageInfo<ProductComment> queryProductCommentPage(ProductComment productComment) throws PendingException {
        try {
            // 对请求参数进行校验
            if (productComment.getPageNo() <= 0 || productComment.getPageSize() <= 0) {
                ResCode.productCommentDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(productComment.getPageNo(), productComment.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productComment.getOrderby())) {
                PageHelper.orderBy(productComment.getOrderby());
            }
            // 转换成Domain对象
            ProductCommentDomain cond = BeanMapping.map(productComment, ProductCommentDomain.class);
            // 调数据库接口查询列表
            List<ProductCommentDomain> resultList = productCommentDao.queryProductCommentList(cond);
            // 生成分页对象
            PageInfo<ProductCommentDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, ProductComment.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentDBError, "产品评论信息分页查询失败");
        }
    }

}
