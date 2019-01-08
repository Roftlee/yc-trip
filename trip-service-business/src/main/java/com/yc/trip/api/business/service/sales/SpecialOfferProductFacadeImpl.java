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
import com.yc.trip.api.business.dao.sales.SpecialOfferProductDao;
import com.yc.trip.api.business.dto.sales.SpecialOfferProduct;
import com.yc.trip.api.business.query.sales.SpecialOfferProductQuery;
import com.yc.trip.api.business.facade.sales.SpecialOfferProductFacade;

/**
 * 优惠活动产品信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-08 23:31
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
    public SpecialOfferProduct add(SpecialOfferProduct specialOfferProduct) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            specialOfferProduct.validateInsertFields();
            // 调数据库接口进行新增操作
            specialOfferProductDao.add(specialOfferProduct);
            // 将新增后回返回（包含自增主键值）
            return specialOfferProduct;
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
    public void update(SpecialOfferProduct specialOfferProduct) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (specialOfferProduct.isAllFiledsNull()) {
                ResCode.specialOfferProductDBParamInvalid.throwException("优惠活动产品信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            specialOfferProductDao.update(specialOfferProduct);
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
    public SpecialOfferProduct get(SpecialOfferProductQuery specialOfferProductQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return specialOfferProductDao.get(specialOfferProductQuery);
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
    public SpecialOfferProduct mustGet(SpecialOfferProductQuery specialOfferProductQuery) throws PendingException {
        // 查询单位信息
        SpecialOfferProduct result = get(specialOfferProductQuery);
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
    public List<SpecialOfferProduct> queryList(SpecialOfferProductQuery specialOfferProductQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(specialOfferProductQuery.getOrderby())) {
                PageHelper.orderBy(specialOfferProductQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return specialOfferProductDao.queryList(specialOfferProductQuery);
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
    public PageInfo<SpecialOfferProduct> queryPage(SpecialOfferProductQuery specialOfferProductQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (specialOfferProductQuery.getPageNo() <= 0 || specialOfferProductQuery.getPageSize() <= 0) {
                ResCode.specialOfferProductDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(specialOfferProductQuery.getPageNo(), specialOfferProductQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(specialOfferProductQuery.getOrderby())) {
                PageHelper.orderBy(specialOfferProductQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<SpecialOfferProduct> resultList = specialOfferProductDao.queryList(specialOfferProductQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferProductDBError, "优惠活动产品信息分页查询失败");
        }
    }

}
