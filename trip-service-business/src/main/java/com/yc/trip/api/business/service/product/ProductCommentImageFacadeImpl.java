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
import com.yc.trip.api.business.bo.product.ProductCommentImageDomain;
import com.yc.trip.api.business.dao.product.ProductCommentImageDao;
import com.yc.trip.api.business.dto.product.ProductCommentImage;
import com.yc.trip.api.business.facade.product.ProductCommentImageFacade;

/**
 * 产品评论图片信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:06
 */
@Service(version = "1.0.0")
public class ProductCommentImageFacadeImpl extends AbstractDubboNativeService implements ProductCommentImageFacade {

    @Autowired
    private ProductCommentImageDao productCommentImageDao;

    /**
     * 新增产品评论图片信息
     * 
     * @throws PendingException
     */
    @Override
    public ProductCommentImage addProductCommentImage(ProductCommentImage productCommentImage) throws PendingException {
        try {
            // 转换成domain对象
            ProductCommentImageDomain cond = BeanMapping.map(productCommentImage, ProductCommentImageDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.productCommentImageDBParamInvalid.throwException("产品评论图片信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            productCommentImageDao.addProductCommentImage(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, ProductCommentImage.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentImageDBError, "产品评论图片信息新增失败");
        }
    }

    /**
     * 修改产品评论图片信息
     * 
     * @throws PendingException
     */
    @Override
    public ProductCommentImage updateProductCommentImage(ProductCommentImage productCommentImage) throws PendingException {
        try {
            // 转换成domain对象
            ProductCommentImageDomain cond = BeanMapping.map(productCommentImage, ProductCommentImageDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.productCommentImageDBParamInvalid.throwException("产品评论图片信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            productCommentImageDao.updateProductCommentImage(cond);
            return BeanMapping.map(cond, ProductCommentImage.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentImageDBError, "产品评论图片信息更新失败");
        }
    }

    /**
     * 查询产品评论图片信息
     * 
     * @throws PendingException
     */
    @Override
    public ProductCommentImage getProductCommentImage(ProductCommentImage productCommentImage) throws PendingException {
        try {
            // 转换成Domain对象
            ProductCommentImageDomain cond = BeanMapping.map(productCommentImage, ProductCommentImageDomain.class);
            // 调数据库接口查询对象
            ProductCommentImageDomain resultBean = productCommentImageDao.getProductCommentImage(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, ProductCommentImage.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentImageDBError, "产品评论图片信息查询失败");
        }
    }
    
    /**
     * 查询产品评论图片信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public ProductCommentImage mustGet(ProductCommentImage productCommentImage) throws PendingException {
        // 查询单位信息
        ProductCommentImage result = getProductCommentImage(productCommentImage);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.productCommentImageDBGetNull.throwException("未查询到产品评论图片信息");
        }
        return result;
    }

    /**
     * 查询产品评论图片信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<ProductCommentImage> queryProductCommentImageList(ProductCommentImage productCommentImage) throws PendingException {
        try {
            // 转换成Domain对象
            ProductCommentImageDomain cond = BeanMapping.map(productCommentImage, ProductCommentImageDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productCommentImage.getOrderby())) {
                PageHelper.orderBy(productCommentImage.getOrderby());
            }
            // 调数据库接口查询列表
            List<ProductCommentImageDomain> resultList = productCommentImageDao.queryProductCommentImageList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, ProductCommentImage.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentImageDBError, "产品评论图片信息列表查询失败");
        }
    }

    /**
     * 查询产品评论图片信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<ProductCommentImage> queryProductCommentImagePage(ProductCommentImage productCommentImage) throws PendingException {
        try {
            // 对请求参数进行校验
            if (productCommentImage.getPageNo() <= 0 || productCommentImage.getPageSize() <= 0) {
                ResCode.productCommentImageDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(productCommentImage.getPageNo(), productCommentImage.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(productCommentImage.getOrderby())) {
                PageHelper.orderBy(productCommentImage.getOrderby());
            }
            // 转换成Domain对象
            ProductCommentImageDomain cond = BeanMapping.map(productCommentImage, ProductCommentImageDomain.class);
            // 调数据库接口查询列表
            List<ProductCommentImageDomain> resultList = productCommentImageDao.queryProductCommentImageList(cond);
            // 生成分页对象
            PageInfo<ProductCommentImageDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, ProductCommentImage.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentImageDBError, "产品评论图片信息分页查询失败");
        }
    }

}
