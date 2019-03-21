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
import com.yc.trip.api.business.bo.sales.SpecialOfferDomain;
import com.yc.trip.api.business.dao.sales.SpecialOfferDao;
import com.yc.trip.api.business.dto.sales.SpecialOffer;
import com.yc.trip.api.business.facade.sales.SpecialOfferFacade;

/**
 * 优惠活动信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:20
 */
@Service(version = "1.0.0")
public class SpecialOfferFacadeImpl extends AbstractDubboNativeService implements SpecialOfferFacade {

    @Autowired
    private SpecialOfferDao specialOfferDao;

    /**
     * 新增优惠活动信息
     * 
     * @throws PendingException
     */
    @Override
    public SpecialOffer addSpecialOffer(SpecialOffer specialOffer) throws PendingException {
        try {
            // 转换成domain对象
            SpecialOfferDomain cond = BeanMapping.map(specialOffer, SpecialOfferDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.specialOfferDBParamInvalid.throwException("优惠活动信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            specialOfferDao.addSpecialOffer(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, SpecialOffer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferDBError, "优惠活动信息新增失败");
        }
    }

    /**
     * 修改优惠活动信息
     * 
     * @throws PendingException
     */
    @Override
    public SpecialOffer updateSpecialOffer(SpecialOffer specialOffer) throws PendingException {
        try {
            // 转换成domain对象
            SpecialOfferDomain cond = BeanMapping.map(specialOffer, SpecialOfferDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.specialOfferDBParamInvalid.throwException("优惠活动信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            specialOfferDao.updateSpecialOffer(cond);
            return BeanMapping.map(cond, SpecialOffer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferDBError, "优惠活动信息更新失败");
        }
    }

    /**
     * 查询优惠活动信息
     * 
     * @throws PendingException
     */
    @Override
    public SpecialOffer getSpecialOffer(SpecialOffer specialOffer) throws PendingException {
        try {
            // 转换成Domain对象
            SpecialOfferDomain cond = BeanMapping.map(specialOffer, SpecialOfferDomain.class);
            // 调数据库接口查询对象
            SpecialOfferDomain resultBean = specialOfferDao.getSpecialOffer(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, SpecialOffer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferDBError, "优惠活动信息查询失败");
        }
    }
    
    /**
     * 查询优惠活动信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public SpecialOffer mustGet(SpecialOffer specialOffer) throws PendingException {
        // 查询单位信息
        SpecialOffer result = getSpecialOffer(specialOffer);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.specialOfferDBGetNull.throwException("未查询到优惠活动信息");
        }
        return result;
    }

    /**
     * 查询优惠活动信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<SpecialOffer> querySpecialOfferList(SpecialOffer specialOffer) throws PendingException {
        try {
            // 转换成Domain对象
            SpecialOfferDomain cond = BeanMapping.map(specialOffer, SpecialOfferDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(specialOffer.getOrderby())) {
                PageHelper.orderBy(specialOffer.getOrderby());
            }
            // 调数据库接口查询列表
            List<SpecialOfferDomain> resultList = specialOfferDao.querySpecialOfferList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, SpecialOffer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferDBError, "优惠活动信息列表查询失败");
        }
    }

    /**
     * 查询优惠活动信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<SpecialOffer> querySpecialOfferPage(SpecialOffer specialOffer) throws PendingException {
        try {
            // 对请求参数进行校验
            if (specialOffer.getPageNo() <= 0 || specialOffer.getPageSize() <= 0) {
                ResCode.specialOfferDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(specialOffer.getPageNo(), specialOffer.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(specialOffer.getOrderby())) {
                PageHelper.orderBy(specialOffer.getOrderby());
            }
            // 转换成Domain对象
            SpecialOfferDomain cond = BeanMapping.map(specialOffer, SpecialOfferDomain.class);
            // 调数据库接口查询列表
            List<SpecialOfferDomain> resultList = specialOfferDao.querySpecialOfferList(cond);
            // 生成分页对象
            PageInfo<SpecialOfferDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, SpecialOffer.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.specialOfferDBError, "优惠活动信息分页查询失败");
        }
    }

}
