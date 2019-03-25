package com.yc.trip.api.business.service.sales;

import java.util.List;


import com.yc.trip.api.business.request.common.IdRequest;
import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.business.bo.sales.SpecialOfferProductDomain;
import com.yc.trip.api.business.dao.sales.SpecialOfferProductDao;
import com.yc.trip.api.business.dto.sales.SpecialOfferProduct;
import com.yc.trip.api.business.facade.sales.SpecialOfferProductFacade;

/**
 * 优惠活动产品信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:21
 */
@Service(version = "1.0.0")
public class SpecialOfferProductFacadeImpl extends AbstractDubboNativeService implements SpecialOfferProductFacade {

    @Autowired
    private SpecialOfferProductDao specialOfferProductDao;

    /**
     * 新增优惠活动产品信息
     * 
     * @throws PendingException
     */
    @Override
    public SpecialOfferProduct addSpecialOfferProduct(SpecialOfferProduct specialOfferProduct) throws PendingException {
        try {
            // 转换成domain对象
            SpecialOfferProductDomain cond = BeanMapping.map(specialOfferProduct, SpecialOfferProductDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.specialOfferProductDBParamInvalid.throwException("优惠活动产品信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            specialOfferProductDao.addSpecialOfferProduct(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, SpecialOfferProduct.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferProductDBError, "优惠活动产品信息新增失败");
        }
    }

    /**
     * 修改优惠活动产品信息
     * 
     * @throws PendingException
     */
    @Override
    public SpecialOfferProduct updateSpecialOfferProduct(SpecialOfferProduct specialOfferProduct) throws PendingException {
        try {
            // 转换成domain对象
            SpecialOfferProductDomain cond = BeanMapping.map(specialOfferProduct, SpecialOfferProductDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.specialOfferProductDBParamInvalid.throwException("优惠活动产品信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            specialOfferProductDao.updateSpecialOfferProduct(cond);
            return BeanMapping.map(cond, SpecialOfferProduct.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferProductDBError, "优惠活动产品信息更新失败");
        }
    }

    /**
     * 查询优惠活动产品信息
     * 
     * @throws PendingException
     */
    @Override
    public SpecialOfferProduct getSpecialOfferProduct(SpecialOfferProduct specialOfferProduct) throws PendingException {
        try {
            // 转换成Domain对象
            SpecialOfferProductDomain cond = BeanMapping.map(specialOfferProduct, SpecialOfferProductDomain.class);
            // 调数据库接口查询对象
            SpecialOfferProductDomain resultBean = specialOfferProductDao.getSpecialOfferProduct(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, SpecialOfferProduct.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferProductDBError, "优惠活动产品信息查询失败");
        }
    }
    
    /**
     * 查询优惠活动产品信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public SpecialOfferProduct mustGet(SpecialOfferProduct specialOfferProduct) throws PendingException {
        // 查询单位信息
        SpecialOfferProduct result = getSpecialOfferProduct(specialOfferProduct);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.specialOfferProductDBGetNull.throwException("未查询到优惠活动产品信息");
        }
        return result;
    }

    /**
     * 查询优惠活动产品信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<SpecialOfferProduct> querySpecialOfferProductList(SpecialOfferProduct specialOfferProduct) throws PendingException {
        try {
            // 转换成Domain对象
            SpecialOfferProductDomain cond = BeanMapping.map(specialOfferProduct, SpecialOfferProductDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(specialOfferProduct.getOrderby())) {
                PageHelper.orderBy(specialOfferProduct.getOrderby());
            }
            // 调数据库接口查询列表
            List<SpecialOfferProductDomain> resultList = specialOfferProductDao.querySpecialOfferProductList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, SpecialOfferProduct.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferProductDBError, "优惠活动产品信息列表查询失败");
        }
    }

    /**
     * 查询优惠活动产品信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<SpecialOfferProduct> querySpecialOfferProductPage(SpecialOfferProduct specialOfferProduct) throws PendingException {
        try {
            // 对请求参数进行校验
            if (specialOfferProduct.getPageNo() <= 0 || specialOfferProduct.getPageSize() <= 0) {
                ResCode.specialOfferProductDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(specialOfferProduct.getPageNo(), specialOfferProduct.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(specialOfferProduct.getOrderby())) {
                PageHelper.orderBy(specialOfferProduct.getOrderby());
            }
            // 转换成Domain对象
            SpecialOfferProductDomain cond = BeanMapping.map(specialOfferProduct, SpecialOfferProductDomain.class);
            // 调数据库接口查询列表
            List<SpecialOfferProductDomain> resultList = specialOfferProductDao.querySpecialOfferProductList(cond);
            // 生成分页对象
            PageInfo<SpecialOfferProductDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, SpecialOfferProduct.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferProductDBError, "优惠活动产品信息分页查询失败");
        }
    }

    /**
     * 删除优惠活动产品
     *
     * @throws PendingException
     */
    @Override
    public void deleteSpecialOfferProduct(IdRequest idRequest) throws PendingException {
        try {
            // 调数据库接口进行新增操作
            specialOfferProductDao.deleteSpecialOfferProduct(idRequest);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferProductDBError, "删除优惠活动产品失败");
        }
    }
}
