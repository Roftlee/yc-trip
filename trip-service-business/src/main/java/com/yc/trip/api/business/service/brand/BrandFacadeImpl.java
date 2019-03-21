package com.yc.trip.api.business.service.brand;

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
import com.yc.trip.api.business.bo.brand.BrandDomain;
import com.yc.trip.api.business.dao.brand.BrandDao;
import com.yc.trip.api.business.dto.brand.Brand;
import com.yc.trip.api.business.facade.brand.BrandFacade;

/**
 * 品牌相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:31
 */
@Service(version = "1.0.0")
public class BrandFacadeImpl extends AbstractDubboNativeService implements BrandFacade {

    @Autowired
    private BrandDao brandDao;

    /**
     * 新增品牌
     * 
     * @throws PendingException
     */
    @Override
    public Brand addBrand(Brand brand) throws PendingException {
        try {
            // 转换成domain对象
            BrandDomain cond = BeanMapping.map(brand, BrandDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.brandDBParamInvalid.throwException("品牌新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            brandDao.addBrand(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Brand.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌新增失败");
        }
    }

    /**
     * 修改品牌
     * 
     * @throws PendingException
     */
    @Override
    public Brand updateBrand(Brand brand) throws PendingException {
        try {
            // 转换成domain对象
            BrandDomain cond = BeanMapping.map(brand, BrandDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.brandDBParamInvalid.throwException("品牌更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            brandDao.updateBrand(cond);
            return BeanMapping.map(cond, Brand.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌更新失败");
        }
    }

    /**
     * 查询品牌
     * 
     * @throws PendingException
     */
    @Override
    public Brand getBrand(Brand brand) throws PendingException {
        try {
            // 转换成Domain对象
            BrandDomain cond = BeanMapping.map(brand, BrandDomain.class);
            // 调数据库接口查询对象
            BrandDomain resultBean = brandDao.getBrand(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Brand.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌查询失败");
        }
    }
    
    /**
     * 查询品牌信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Brand mustGet(Brand brand) throws PendingException {
        // 查询单位信息
        Brand result = getBrand(brand);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.brandDBGetNull.throwException("未查询到品牌");
        }
        return result;
    }

    /**
     * 查询品牌列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Brand> queryBrandList(Brand brand) throws PendingException {
        try {
            // 转换成Domain对象
            BrandDomain cond = BeanMapping.map(brand, BrandDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(brand.getOrderby())) {
                PageHelper.orderBy(brand.getOrderby());
            }
            // 调数据库接口查询列表
            List<BrandDomain> resultList = brandDao.queryBrandList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Brand.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌列表查询失败");
        }
    }

    /**
     * 查询品牌列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Brand> queryBrandPage(Brand brand) throws PendingException {
        try {
            // 对请求参数进行校验
            if (brand.getPageNo() <= 0 || brand.getPageSize() <= 0) {
                ResCode.brandDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(brand.getPageNo(), brand.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(brand.getOrderby())) {
                PageHelper.orderBy(brand.getOrderby());
            }
            // 转换成Domain对象
            BrandDomain cond = BeanMapping.map(brand, BrandDomain.class);
            // 调数据库接口查询列表
            List<BrandDomain> resultList = brandDao.queryBrandList(cond);
            // 生成分页对象
            PageInfo<BrandDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Brand.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌分页查询失败");
        }
    }

}
